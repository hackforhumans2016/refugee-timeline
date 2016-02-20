package de.hackforhumans.refugeetimeline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import de.hackforhumans.refugeetimeline.MockTask;
import de.hackforhumans.refugeetimeline.R;

public class TaskDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TASK = "de.hackforhumans.refugeetimeline.activity.TaskDetails.Extra.Task";

    private Toolbar toolbar;
    private TextView nameView;
    private TextView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetails);

        this.toolbar = (Toolbar) findViewById(R.id.taskdetails_toolbar);
        this.nameView = (TextView) findViewById(R.id.taskdetails_name);
        this.descriptionView = (TextView) findViewById(R.id.taskdetails_description);

        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public static void buildIntent(Intent intent, MockTask task) {
        intent.putExtra(EXTRA_TASK, task);
    }

    public void show(MockTask task) {
        this.nameView.setText(task.getName());
        this.descriptionView.setText(task.getDesc());
    }
}
