package de.hackforhumans.refugeetimeline.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import de.hackforhumans.refugeetimeline.RefugeeTimeline;
import de.hackforhumans.refugeetimeline.model.Goal;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class TaskTools {

    public static Task loadFromDB(int taskId) {

        Cursor taskC = RefugeeTimeline.getInstance().getTimelineDB().query(
                TaskGraphContract.Task._NAME,
                null,
                TaskGraphContract.Task._ID + " = ?",
                new String[]{String.valueOf(taskId)},
                null,
                null,
                null,
                null
        );

        taskC.moveToNext();
        Task result = loadFromDBRow(taskC);
        taskC.close();
        return result;
    }

    private static Task loadFromDBRow(Cursor taskC) {

        int id = taskC.getInt(taskC.getColumnIndex(TaskGraphContract.Task._ID));
        String name = taskC.getString(taskC.getColumnIndex(TaskGraphContract.Task.Name));
        String desc = taskC.getString(taskC.getColumnIndex(TaskGraphContract.Task.Description));
        int duration = taskC.getInt(taskC.getColumnIndex(TaskGraphContract.Task.Duration));

        SortedSet<Date> fixedDates = new TreeSet<>();
        Cursor timePointC = RefugeeTimeline.getInstance().getTimelineDB().query(
                TaskGraphContract.TaskDate._NAME,
                null,
                TaskGraphContract.TaskDate.TaskRef + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        while (!timePointC.isLast() && timePointC.getCount() != 0) {
            timePointC.moveToNext();
            try {
                fixedDates.add(format.parse(timePointC.getString(timePointC.getColumnIndex(TaskGraphContract.TaskDate.Date))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int predecessor = taskC.isNull(taskC.getColumnIndex(TaskGraphContract.Task.Predecessor)) ? -1 : taskC.getInt(taskC.getColumnIndex(TaskGraphContract.Task.Predecessor));

        return new Task(id, name, desc, predecessor, duration, fixedDates);
    }

    public static ArrayList<Goal> loadGoalsFromDB() {

        ArrayList<Goal> goals = new ArrayList<Goal>();
        Cursor timePointC = RefugeeTimeline.getInstance().getTimelineDB().query(
                TaskGraphContract.Goal._NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (!timePointC.isLast() && timePointC.getCount() != 0) {
            timePointC.moveToNext();
            try {
                int id = timePointC.getInt(timePointC.getColumnIndex(TaskGraphContract.Goal.ID));
                String name = timePointC.getString(timePointC.getColumnIndex(TaskGraphContract.Goal.Name));
                int refer = timePointC.getInt(timePointC.getColumnIndex(TaskGraphContract.Goal.TaskRef));

                goals.add(new Goal(id,name,refer));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return goals;
    }



}
