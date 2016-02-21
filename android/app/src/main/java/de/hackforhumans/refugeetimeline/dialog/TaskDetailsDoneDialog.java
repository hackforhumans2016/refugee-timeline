package de.hackforhumans.refugeetimeline.dialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import de.hackforhumans.refugeetimeline.activity.TaskDetailsActivity;
import de.hackforhumans.refugeetimeline.model.Task;
import de.hackforhumans.refugeetimeline.model.TaskTools;

/**
 * Created by jan-niklas on 21.02.16.
 */
public class TaskDetailsDoneDialog extends DialogFragment {

    private static final String EXTRA_TASK = "de.hackforhumans.refugeetimeline.activity.TaskDetails.Extra.Task";

    private Task associatedTask;

    public void setArguments(Bundle args) {
        this.associatedTask = TaskTools.loadFromDB(args.getInt(EXTRA_TASK, 1));
    }

    public static void setArguments(TaskDetailsDoneDialog dialog, int taskId) {
        Bundle args = new Bundle();

        args.putInt(EXTRA_TASK, taskId);

        dialog.setArguments(args);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onCancel(dialog);

        writeToDatabase();
        ((TaskDetailsActivity) getActivity()).loadTask(getAssociatedTask().getID());
    }

    public Task getAssociatedTask() {
        return associatedTask;
    }

    public void writeToDatabase() {
        TaskTools.updateTaskInDB(associatedTask);
    }
}
