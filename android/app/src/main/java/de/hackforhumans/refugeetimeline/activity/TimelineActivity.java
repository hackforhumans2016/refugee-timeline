package de.hackforhumans.refugeetimeline.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.de.hackforhumans.refugeetimeline.adapter.TimelineAdapter;

public class TimelineActivity extends AppCompatActivity {

    private static final int REQ_CHOOSE_GOAL = 1;

    private int goalTaskId = -1;

    private RecyclerView timelineRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.timeline_activity);

        this.timelineRecyclerView = (RecyclerView) this.findViewById(R.id.timeline_timelineRecyler);

        this.timelineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        refresh(5);
    }

    public void onStart() {
        super.onStart();
        if (goalTaskId > 0) refresh(goalTaskId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_timeline_chooseGoal) {
            Intent chooseIntent = new Intent(this, GoalChooserActivity.class);
            startActivityForResult(chooseIntent,REQ_CHOOSE_GOAL);
            return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQ_CHOOSE_GOAL && resultCode == Activity.RESULT_OK) {
            refresh(data.getIntExtra(GoalChooserActivity.RES_EXTRA_TASKID, 1));
        }
    }

    public void refresh(int goalTaskId) {
        this.goalTaskId = goalTaskId;
        this.timelineRecyclerView.setAdapter(new TimelineAdapter(goalTaskId));
    }
}
