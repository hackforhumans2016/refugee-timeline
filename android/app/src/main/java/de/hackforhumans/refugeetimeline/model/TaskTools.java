package de.hackforhumans.refugeetimeline.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Ref;
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

    private  static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Task loadFromDB(int taskId) {

        SQLiteDatabase db = RefugeeTimeline.getInstance().getTimelineDB();
        Cursor taskC = db.query(
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
        Task result = loadFromDBRow(db, taskC);
        taskC.close();
        db.close();
        return result;
    }

    private static Task loadFromDBRow(SQLiteDatabase db, Cursor taskC) {

        int id = taskC.getInt(taskC.getColumnIndex(TaskGraphContract.Task._ID));
        String name = taskC.getString(taskC.getColumnIndex(TaskGraphContract.Task.Name));
        String desc = taskC.getString(taskC.getColumnIndex(TaskGraphContract.Task.Description));
        int duration = taskC.getInt(taskC.getColumnIndex(TaskGraphContract.Task.Duration));

        Date started = null;
        Date finished = null;

        try{
            if (!taskC.isNull(taskC.getColumnIndex(TaskGraphContract.Task.Started))) {
                started = DB_DATE_FORMAT.parse(taskC.getString(taskC.getColumnIndex(TaskGraphContract.Task.Started)));
            }
            if (!taskC.isNull(taskC.getColumnIndex((TaskGraphContract.Task.Finished)))) {
                finished = DB_DATE_FORMAT.parse(taskC.getString(taskC.getColumnIndex(TaskGraphContract.Task.Finished)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SortedSet<Date> fixedDates = new TreeSet<>();
        Cursor timePointC = db.query(
                TaskGraphContract.TaskDate._NAME,
                null,
                TaskGraphContract.TaskDate.TaskRef + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        while (!timePointC.isLast() && timePointC.getCount() != 0) {
            timePointC.moveToNext();
            try {
                fixedDates.add(DB_DATE_FORMAT.parse(timePointC.getString(timePointC.getColumnIndex(TaskGraphContract.TaskDate.Date))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        timePointC.close();

        int predecessor = taskC.isNull(taskC.getColumnIndex(TaskGraphContract.Task.Predecessor)) ? -1 : taskC.getInt(taskC.getColumnIndex(TaskGraphContract.Task.Predecessor));

        return new Task(id, name, desc, predecessor, duration, fixedDates, started, finished);
    }

    public static ArrayList<Goal> loadGoalsFromDB() {

        ArrayList<Goal> goals = new ArrayList<Goal>();
        Cursor goalC = RefugeeTimeline.getInstance().getTimelineDB().query(
                TaskGraphContract.Goal._NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (!goalC.isLast() && goalC.getCount() != 0) {
            goalC.moveToNext();
            try {
                int id = goalC.getInt(goalC.getColumnIndex(TaskGraphContract.Goal.ID));
                String name = goalC.getString(goalC.getColumnIndex(TaskGraphContract.Goal.Name));
                int refer = goalC.getInt(goalC.getColumnIndex(TaskGraphContract.Goal.TaskRef));

                goals.add(new Goal(id,name,refer));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        goalC.close();
        return goals;
    }

    public static Boolean updateTaskInDB(Task task) {

        ContentValues values = new ContentValues();
        if (task.getStarted() != null) {
            values.put(TaskGraphContract.Task.Started, DB_DATE_FORMAT.format(task.getStarted()));
        } else {
            values.putNull(TaskGraphContract.Task.Started);
        }
        if (task.getFinished() != null) {
            values.put(TaskGraphContract.Task.Finished, DB_DATE_FORMAT.format(task.getFinished()));
        } else {
            values.putNull(TaskGraphContract.Task.Finished);
        }

        SQLiteDatabase db = RefugeeTimeline.getInstance().getTimelineDB();
        db.update(TaskGraphContract.Task._NAME, values, TaskGraphContract.Task.ID + " = ?", new String[]{String.valueOf(task.getID())});
        db.close();

        return true;
    }





}
