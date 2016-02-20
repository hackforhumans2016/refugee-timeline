package de.hackforhumans.refugeetimeline.de.hackforhumans.refugeetimeline.adapter;

import android.app.Service;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vipul.hp_hp.timelineview.TimelineView;

import de.hackforhumans.refugeetimeline.MockTask;
import de.hackforhumans.refugeetimeline.R;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineViewHolder> {

    private MockTask[] tasks = new MockTask[] {
            new MockTask("Task 1", "This is an example task", "now"),
            new MockTask("Task 2", "This is a dependend task", "tomorrow"),
            new MockTask("Goal", "The goal we want to reach", "sometime")
    };

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View timelineItem = ((LayoutInflater) parent.getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.timeline_listitem, parent, false);
        TimelineViewHolder vh = new TimelineViewHolder(timelineItem, viewType);

        return vh;
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {
        // TODO insert data from database
        holder.show(tasks[position]);
    }

    @Override
    public int getItemCount() {
        // TODO insert data from database
        return tasks.length;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }
}
