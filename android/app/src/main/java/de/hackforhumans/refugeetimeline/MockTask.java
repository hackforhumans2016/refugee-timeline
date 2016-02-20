package de.hackforhumans.refugeetimeline;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class MockTask {

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
}
