package de.hackforhumans.refugeetimeline.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.de.hackforhumans.refugeetimeline.adapter.GoalAdapter;
import de.hackforhumans.refugeetimeline.model.Goal;

public class GoalChooserActivity extends AppCompatActivity {

    public static final String RES_EXTRA_TASKID = "de.hackforhumans.refugeetimeline.activity.GoalChooser.ResExtra.TaskId";
    public static final String RES_EXTRA_GOALID = "de.hackforhumans.refugeetimeline.activity.GoalChooser.ResExtra.GoalId";

    public static final String EXTRA_TASKID = "de.hackforhumans.refugeetimeline.activity.GoalChooser.Extra.TaskId";

    private Toolbar toolbar;
    private RecyclerView goalsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goalchooser);

        this.toolbar = (Toolbar) findViewById(R.id.goalchooser_toolbar);
        this.goalsRecycler = (RecyclerView) findViewById(R.id.goalchooser_goals);

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("Choose your Goal!");
        toolbar.setTitleTextColor(Color.WHITE);

        int firstSelectedTaskId = getIntent().getIntExtra(EXTRA_TASKID, -1);
        goalsRecycler.setAdapter(new GoalAdapter(firstSelectedTaskId));
        goalsRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public static void fillIntent(Intent intent, int firstSelectedTaskId) {
        intent.putExtra(EXTRA_TASKID, firstSelectedTaskId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.goalchooser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_goalchooser_select) {
            Intent resultIntent = new Intent();
            Goal selected = ((GoalAdapter) goalsRecycler.getAdapter()).getSelectedGoal();
            resultIntent.putExtra(RES_EXTRA_GOALID, selected.getID());
            resultIntent.putExtra(RES_EXTRA_TASKID, selected.getTaskID());
            setResult(Activity.RESULT_OK, resultIntent);

            finish();
            return true;
        }

        return false;
    }
}
