package de.hackforhumans.refugeetimeline.de.hackforhumans.refugeetimeline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.model.Goal;
import de.hackforhumans.refugeetimeline.model.TaskTools;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class GoalAdapter extends RecyclerView.Adapter<GoalViewHolder> implements CompoundButton.OnCheckedChangeListener {

    private ArrayList<Goal> goals;
    private RecyclerView attachRecycler;
    private int selectedPos;

    public GoalAdapter(int firstSelectedId) {
        this.goals = TaskTools.loadGoalsFromDB(); // TODO
        this.selectedPos = 0;

        for(int i = 0; i < goals.size(); i++) {
            Goal g = goals.get(i);
            if (g.getTaskID() == firstSelectedId) {
                setSelection(i);
                break;
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.attachRecycler = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.attachRecycler = recyclerView;
    }

    @Override
    public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goalchooser_listitem, parent, false);
        GoalViewHolder vh = new GoalViewHolder(view);

        vh.radioButton.setOnCheckedChangeListener(this);
        vh.radioButton.setTag(vh.itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position) {

        holder.show(goals.get(position));
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    public void setSelection(int position) {
        if(attachRecycler != null && selectedPos != position) {

            ((GoalViewHolder) attachRecycler.findViewHolderForAdapterPosition(selectedPos)).radioButton.setChecked(false);
            ((GoalViewHolder) attachRecycler.findViewHolderForAdapterPosition(position)).radioButton.setChecked(true);

            selectedPos = position;
        }
    }

    public Goal getSelectedGoal() {
        return goals.get(selectedPos);
    }

    public int getSelection() {
        return selectedPos;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (attachRecycler != null) {
            setSelection(attachRecycler.getChildAdapterPosition((View) buttonView.getTag()));
        }
    }
}
