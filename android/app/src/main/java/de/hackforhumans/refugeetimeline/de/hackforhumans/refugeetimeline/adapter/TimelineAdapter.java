package de.hackforhumans.refugeetimeline.de.hackforhumans.refugeetimeline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import de.hackforhumans.refugeetimeline.R;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineViewHolder> {

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View timelineItem = View.inflate(parent.getContext(), R.layout.timeline_listitem, parent);
        TimelineViewHolder vh = new TimelineViewHolder(timelineItem, viewType);

        return vh;
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {
        // TODO insert data from database
    }

    @Override
    public int getItemCount() {
        // TODO insert data from database
        return 0;
    }
}
