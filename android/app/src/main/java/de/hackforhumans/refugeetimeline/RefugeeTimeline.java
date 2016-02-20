package de.hackforhumans.refugeetimeline;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class RefugeeTimeline extends Application {

    private static RefugeeTimeline INSTANCE;

    public final String TIMELINE_DATABASE_PATH;

    public RefugeeTimeline() {
        INSTANCE = this;

        TIMELINE_DATABASE_PATH = Environment.getExternalStorageDirectory() + "/database.db";
    }

    public static RefugeeTimeline getInstance() {
        return INSTANCE;
    }

    public SQLiteDatabase getTimelineDB() {
        return SQLiteDatabase.openDatabase(TIMELINE_DATABASE_PATH, null, 0);
    }
}
