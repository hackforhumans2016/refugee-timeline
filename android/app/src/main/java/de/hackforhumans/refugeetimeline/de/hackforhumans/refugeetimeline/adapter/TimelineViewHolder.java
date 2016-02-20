package de.hackforhumans.refugeetimeline.de.hackforhumans.refugeetimeline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vipul.hp_hp.timelineview.TimelineView;

import org.w3c.dom.Text;

import de.hackforhumans.refugeetimeline.MockTask;
import de.hackforhumans.refugeetimeline.R;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class TimelineViewHolder extends RecyclerView.ViewHolder {

    public TimelineView timelineView;
    public TextView contentNameView;
    public TextView contentWhenView;

    public TimelineViewHolder(View view, int viewType) {
        super(view);
        this.timelineView = (TimelineView) view.findViewById(R.id.timeline_listitem_timelineView);
        this.contentNameView = (TextView) view.findViewById(R.id.timeline_listitem_contentName);
        this.contentWhenView = (TextView) view.findViewById(R.id.timeline_listitem_contentWhen);

        System.out.println(viewType);
        timelineView.initLine(viewType);
    }

    public void show(MockTask task) {
        show(task.getTime(), task.getName());
    }
    public void show(String when, String name) {
        this.contentNameView.setText(name);
        this.contentWhenView.setText(when);
    }
}
