package de.hackforhumans.refugeetimeline.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.ui.DateEditTextPlugin;

/**
 * Created by jan-niklas on 21.02.16.
 */
public class TaskDetailsDurationDoneDialog extends TaskDetailsDoneDialog implements DateEditTextPlugin.DateSetCallback {

    private DoneTuple startedTuple;
    private DoneTuple finishedTuple;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View layoutView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_taskdetails_done_duration, null, false);

        initWithLayout(layoutView);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Completion Status")
                .setView(layoutView)
                .create();

    }


    private void initWithLayout(View layoutView) {

        CheckBox doneCheck;
        TextView doneDate;
        Date initial;
        DateEditTextPlugin plugin;

        DateFormat format = DateFormat.getDateInstance();

        doneCheck = (CheckBox) layoutView.findViewById(R.id.dialog_taskdetails_done_duration_finishedCheck);
        doneDate = (TextView) layoutView.findViewById(R.id.dialog_taskdetails_done_duration_finishedDate);
        doneDate.setText("Not done yet");

        doneCheck.setChecked(true);
        initial = getAssociatedTask().getFinished();
        if (initial == null) {
            initial = new Date();
            doneCheck.setChecked(false);
            doneDate.setEnabled(false);
        }
        plugin = new DateEditTextPlugin(doneDate, initial, format, this);
        if (doneDate.isEnabled()) plugin.reset();
        finishedTuple = new DoneTuple(doneCheck, doneDate, plugin, true, null);

        doneCheck = (CheckBox) layoutView.findViewById(R.id.dialog_taskdetails_done_duration_startedCheck);
        doneDate = (TextView) layoutView.findViewById(R.id.dialog_taskdetails_done_duration_startedDate);
        doneDate.setText("Not done yet");

        doneCheck.setChecked(true);
        initial = getAssociatedTask().getStarted();
        if (initial == null) {
            initial = new Date();
            doneCheck.setChecked(false);
            doneDate.setEnabled(false);
        }
        plugin = new DateEditTextPlugin(doneDate, initial, format, this);
        if (doneDate.isEnabled()) plugin.reset();
        startedTuple = new DoneTuple(doneCheck, doneDate, plugin, true, finishedTuple);
    }

    @Override
    public void onDateSet(TextView view, Date date) {
        if (startedTuple != null && view == startedTuple.doneDateView) {
            getAssociatedTask().setStarted(date);
        } else if (finishedTuple != null && view == finishedTuple.doneDateView) {
            getAssociatedTask().setFinished(date);
        }
    }

    @Override
    public void writeToDatabase() {
        if(!startedTuple.doneCheckBox.isChecked()) {
            getAssociatedTask().setStarted(null);
        }
        if(!finishedTuple.doneCheckBox.isChecked()) {
            getAssociatedTask().setFinished(null);
        }

        super.writeToDatabase();
    }

    private class DoneTuple implements CompoundButton.OnCheckedChangeListener {

        public DoneTuple dependencyFor;

        public CheckBox doneCheckBox;
        public TextView doneDateView;
        public DateEditTextPlugin datePlugin;

        private boolean enabled;

        public DoneTuple(CheckBox cb, TextView dateV, DateEditTextPlugin plugin, boolean enabled, DoneTuple dependencyFor) {
            this.doneCheckBox = cb;
            this.doneDateView = dateV;
            this.datePlugin = plugin;
            this.enabled = enabled;
            this.dependencyFor = dependencyFor;

            if(!cb.isChecked() && dependencyFor != null) {
                dependencyFor.setEnabled(false);
            }

            cb.setOnCheckedChangeListener(this);
        }

        private void setEnabled(boolean b) {
            doneCheckBox.setEnabled(b);
            if (!b) {
                doneDateView.setText("");
                doneCheckBox.setChecked(false);
                doneDateView.setEnabled(false);
            } else {
                datePlugin.reset();
            }
            this.enabled = b;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (buttonView != doneCheckBox) return;
            if (!isChecked) {
                doneDateView.setEnabled(false);
                doneDateView.setText("Not done yet");
            } else {
                doneDateView.setEnabled(true);
                datePlugin.reset();
            }

            if (dependencyFor != null && dependencyFor.enabled != isChecked) {
                dependencyFor.setEnabled(isChecked);
            }
        }
    }
}
