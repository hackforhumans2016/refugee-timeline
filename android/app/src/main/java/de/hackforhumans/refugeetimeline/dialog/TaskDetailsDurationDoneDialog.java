package de.hackforhumans.refugeetimeline.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Date;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.ui.DateEditTextPlugin;

/**
 * Created by jan-niklas on 21.02.16.
 */
public class TaskDetailsDurationDoneDialog extends TaskDetailsDoneDialog {

    private

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View layoutView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_taskdetails_done_duration, null, false);

        CheckBox doneCheck;
        TextView doneDate;
        DateEditTextPlugin plugin;

        doneCheck = (CheckBox) layoutView.findViewById(R.id.dialog_taskdetails_done_duration_startedCheck);
        doneDate = (TextView) layoutView.findViewById(R.id.dialog_taskdetails_done_duration_startedDate);
        plugin = new DateEditTextPlugin(doneDate, );

    }

    private class DoneTuple implements CompoundButton.OnCheckedChangeListener {

        public DoneTuple dependencyFor;

        private CheckBox doneCheckBox;
        private TextView doneDateView;
        private DateEditTextPlugin datePlugin;

        private boolean enabled;

        public DoneTuple(CheckBox cb, TextView dateV, DateEditTextPlugin plugin, boolean enabled, DoneTuple dependencyFor) {
            this.doneCheckBox = cb;
            this.doneDateView = dateV;
            this.datePlugin = plugin;
            this.enabled = enabled;
            this.dependencyFor = dependencyFor;

            if(!cb.isChecked()) {
                dependencyFor.setEnabled(false);
            }
        }

        private void setEnabled(boolean b) {
            doneCheckBox.setEnabled(b);
            doneDateView.setEnabled(b);
            if (!b) {
                doneDateView.setText("");
                doneDateView.setClickable(false);
                doneCheckBox.setChecked(false);
            } else {
                datePlugin.reset();
                doneDateView.setClickable(true);
            }
            this.enabled = b;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (buttonView != doneCheckBox) return;
            if (!isChecked) {
                doneDateView.setClickable(false);
                doneDateView.setText("Not done yet");
            } else {
                doneDateView.setClickable(true);
                datePlugin.reset();
            }

            if (dependencyFor != null && dependencyFor.enabled != isChecked) {
                dependencyFor.setEnabled(isChecked);
            }
        }
    }
}
