package de.hackforhumans.refugeetimeline.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.model.Task;
import de.hackforhumans.refugeetimeline.model.TaskTools;
import de.hackforhumans.refugeetimeline.ui.DateEditTextPlugin;

/**
 * Created by jan-niklas on 21.02.16.
 */
public class TaskDetailsFixedDateDoneDialog extends TaskDetailsDoneDialog
        implements DateEditTextPlugin.DateSetCallback, CompoundButton.OnCheckedChangeListener {

    private CheckBox doneCheckBox;
    private EditText doneDateView;
    private DateEditTextPlugin doneDatePlugin;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View layoutView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_taskdetails_done_fixeddate, null, false);
        this.doneCheckBox = (CheckBox) layoutView.findViewById(R.id.dialog_taskdetails_done_fixeddate_donecheck);
        this.doneDateView = (EditText) layoutView.findViewById(R.id.dialog_taskdetails_done_fixeddate_donedate);

        doneCheckBox.setChecked(true);
        Date initial = getAssociatedTask().getStarted();
        if (initial == null) {
            initial = new Date();
            doneCheckBox.setChecked(false);
            doneDateView.setEnabled(false);
        }
        this.doneDatePlugin = new DateEditTextPlugin(doneDateView, initial, DateFormat.getDateInstance(), this);
        doneDatePlugin.reset();
        this.doneCheckBox.setOnCheckedChangeListener(this);



        return new AlertDialog.Builder(getActivity())
                .setTitle("Task completion status")
                .setView(layoutView)
                .setCancelable(true)
                .create();
    }

    @Override
    public void onDateSet(TextView view, Date date) {

        getAssociatedTask().setStarted(date);
    }

    @Override
    public void writeToDatabase() {
        if (!doneCheckBox.isChecked()) {
            getAssociatedTask().setStarted(null);
        }

        super.writeToDatabase();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView != doneCheckBox) return;

        if (!isChecked) {
            doneDateView.setText("Not done yet");
            doneDateView.setEnabled(false);
        } else {
            doneDateView.setEnabled(true);
            doneDatePlugin.reset();
        }
    }
}
