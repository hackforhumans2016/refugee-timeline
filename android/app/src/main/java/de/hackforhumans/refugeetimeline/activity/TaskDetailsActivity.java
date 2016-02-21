package de.hackforhumans.refugeetimeline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.dialog.TaskDetailsDoneDialog;
import de.hackforhumans.refugeetimeline.dialog.TaskDetailsDurationDoneDialog;
import de.hackforhumans.refugeetimeline.dialog.TaskDetailsFixedDateDoneDialog;
import de.hackforhumans.refugeetimeline.model.Task;
import de.hackforhumans.refugeetimeline.model.TaskTools;

public class TaskDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TASK = "de.hackforhumans.refugeetimeline.activity.TaskDetails.Extra.Task";

    private Toolbar toolbar;
    private TextView nameView;
    private WebView descriptionView;

    private TaskDetailsDoneDialog doneDialog;
    private Task shownTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetails);

        this.toolbar = (Toolbar) findViewById(R.id.taskdetails_toolbar);
        this.nameView = (TextView) findViewById(R.id.taskdetails_name);
        this.descriptionView = (WebView) findViewById(R.id.taskdetails_description);

        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadTask(getIntent().getIntExtra(EXTRA_TASK, 1));
    }

    public static void buildIntent(Intent intent, int taskId) {
        intent.putExtra(EXTRA_TASK, taskId);
    }

    public void show(Task task) {

        this.nameView.setText(task.getName());
        this.descriptionView.loadData(task.getDescription(), "text/html", null);

        if(task.getFixed().size() != 0) {
            doneDialog = new TaskDetailsFixedDateDoneDialog();
        } else {
            doneDialog = new TaskDetailsDurationDoneDialog();
        }
        TaskDetailsDoneDialog.setArguments(doneDialog, task.getID());
        this.shownTask = task;

        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_taskdetails_done) {

            doneDialog.show(getFragmentManager(), null);

            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskdetails, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        Task predec = null;
        boolean predecCompleted = shownTask.getPredecessorID() < 0 ||
                (predec = TaskTools.loadFromDB(shownTask.getPredecessorID())).isCompleted();

        menu.findItem(R.id.menu_taskdetails_done).setVisible(predecCompleted);

        return true;
    }

    public void loadTask(int taskId) {
        Task task = TaskTools.loadFromDB(taskId);
        show(task);
        invalidateOptionsMenu();
    }
}
