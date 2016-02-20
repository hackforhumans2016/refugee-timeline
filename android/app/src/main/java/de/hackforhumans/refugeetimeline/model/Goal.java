package de.hackforhumans.refugeetimeline.model;

/**
 * Created by dennis on 20.02.2016.
 */
public class Goal {

    private int id;
    private String name;
    private int refer;

    public Goal(int p_id, String p_name, int p_refer){
        id = p_id;
        name = p_name;
        refer = p_refer;
    }

    public int getID() {
        return id;
    }

    public String getDisplayName() {
        return name;
    }

    public int getTaskID() {
        return refer;
    }


}


