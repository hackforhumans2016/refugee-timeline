package de.hackforhumans.refugeetimeline.de.hackforhumans.refugeetimeline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.model.Goal;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class GoalViewHolder extends RecyclerView.ViewHolder {

    RadioButton radioButton;

    public GoalViewHolder(View view) {
        super(view);

        this.radioButton = (RadioButton) view.findViewById(R.id.goalchooser_listitem_radioButton);
    }

    public void show(Goal goal) {
        this.radioButton.setText(goal.getDisplayName());
    }
}
