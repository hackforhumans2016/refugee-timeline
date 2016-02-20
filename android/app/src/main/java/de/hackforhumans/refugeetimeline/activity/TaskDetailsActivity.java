package de.hackforhumans.refugeetimeline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.model.Task;
import de.hackforhumans.refugeetimeline.model.TaskTools;

public class TaskDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TASK = "de.hackforhumans.refugeetimeline.activity.TaskDetails.Extra.Task";

    private Toolbar toolbar;
    private TextView nameView;
    private WebView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetails);

        this.toolbar = (Toolbar) findViewById(R.id.taskdetails_toolbar);
        this.nameView = (TextView) findViewById(R.id.taskdetails_name);
        this.descriptionView = (WebView) findViewById(R.id.taskdetails_description);

        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        show(TaskTools.loadFromDB(getIntent().getIntExtra(EXTRA_TASK, -1)));
    }

    public static void buildIntent(Intent intent, int taskId) {
        intent.putExtra(EXTRA_TASK, taskId);
    }

    public void show(Task task) {
        this.nameView.setText(task.getName());
        this.descriptionView.loadData(task.getDescription(), "text/html", null);
    }
}
