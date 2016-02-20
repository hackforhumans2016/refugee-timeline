package de.hackforhumans.refugeetimeline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.de.hackforhumans.refugeetimeline.adapter.TimelineAdapter;

public class TimelineActivity extends AppCompatActivity {

    private RecyclerView timelineRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.timeline_activity);

        this.timelineRecyclerView = (RecyclerView) this.findViewById(R.id.timeline_timelineRecyler);
    }

    public void onStart() {
        super.onStart();

        this.timelineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.timelineRecyclerView.setAdapter(new TimelineAdapter(12));
    }
}
