package de.hackforhumans.refugeetimeline.de.hackforhumans.refugeetimeline.adapter;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vipul.hp_hp.timelineview.TimelineView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hackforhumans.refugeetimeline.MockTask;
import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.Task;
import de.hackforhumans.refugeetimeline.TaskGraphContract;
import de.hackforhumans.refugeetimeline.TaskTools;
import de.hackforhumans.refugeetimeline.activity.TaskDetailsActivity;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineViewHolder> {

    private ArrayList<Task> timeline;
    private SimpleDateFormat dateFormat;

    public TimelineAdapter(Task goal) {
        timeline = new ArrayList<Task>();

        Task task = goal;
        timeline.add(goal);
        while (task.getPredecessorID() != -1) {
            task = TaskTools.loadFromDB(task.getPredecessorID());
            timeline.add(0, task);
        }
        Task predec = null;
        for (Task t : timeline) {
            if (predec != null) {
                t.calcDate(predec.getDate());
            } else {
                t.setDate(new Date());
            }
            predec = t;
        }

        dateFormat = new SimpleDateFormat("EEE, dd.MM.yyyy");
    }
    public TimelineAdapter(int goalId) {
        this(TaskTools.loadFromDB(goalId));
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View timelineItem = ((LayoutInflater) parent.getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.timeline_listitem, parent, false);
        TimelineViewHolder vh = new TimelineViewHolder(timelineItem, viewType);

        return vh;
    }

    @Override
    public void onBindViewHolder(final TimelineViewHolder holder, int position) {
        final Task task = timeline.get(position);
        holder.show(dateFormat.format(task.getDate()), task.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsIntent = new Intent(holder.itemView.getContext(), TaskDetailsActivity.class);
                TaskDetailsActivity.buildIntent(detailsIntent, task.getID());

                holder.itemView.getContext().startActivity(detailsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeline.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }
}
