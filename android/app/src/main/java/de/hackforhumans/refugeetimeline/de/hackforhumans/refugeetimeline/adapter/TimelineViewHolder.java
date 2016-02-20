package de.hackforhumans.refugeetimeline.de.hackforhumans.refugeetimeline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vipul.hp_hp.timelineview.TimelineView;

import org.w3c.dom.Text;

import de.hackforhumans.refugeetimeline.R;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class TimelineViewHolder extends RecyclerView.ViewHolder {

    private TimelineView tView;
    private TextView contentNameView;
    private TextView contentWhenView;

    public TimelineViewHolder(View view, int viewType) {
        super(view);
        this.tView = (TimelineView) view.findViewById(R.id.timeline_listitem_timelineView);
        this.contentNameView = (TextView) view.findViewById(R.id.timeline_listitem_contentName);
        this.contentWhenView = (TextView) view.findViewById(R.id.timeline_listitem_contentWhen);

        tView.initLine(viewType);
    }

    public TimelineView getTimelineView() {
        return this.tView;
    }

    public TextView getNameView() {
        return contentNameView;
    }

    public TextView getDescriptionView() {
        return contentWhenView;
    }
}
