package de.hackforhumans.refugeetimeline;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class MockTask implements Parcelable {

    private String name;
    private String desc;
    private String time;

    public MockTask(String name, String desc, String time) {
        this.name = name;
        this.desc = desc;
        this.time = time;
    }

    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public String getTime() {
        return time;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(time);
    }

    public static final Parcelable.Creator<MockTask> CREATOR = new Parcelable.Creator<MockTask>() {
        @Override
        public MockTask createFromParcel(Parcel source) {
            MockTask task = new MockTask(source.readString(), source.readString(), source.readString());
            return task;
        }

        @Override
        public MockTask[] newArray(int size) {
            return new MockTask[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
