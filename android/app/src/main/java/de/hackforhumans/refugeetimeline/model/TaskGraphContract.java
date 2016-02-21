package de.hackforhumans.refugeetimeline.model;

/**
 * Created by jan-niklas on 20.02.16.
 */
public class TaskGraphContract {

    public interface Task {
        public String ID = "id";
        public String Name = "name";
        public String Description = "description";
        public String Duration = "duration";
        public String CityRef = "city_id";
        public String StateRef = "state_id";
        public String Predecessor = "task_id_predecessor";
        public String Started = "started";
        public String Finished = "finished";

        public String _ID = ID;
        public String _NAME = "task";
    }

    public interface State {
        public String ID = "id";
        public String Name = "name";

        public String _ID = ID;
        public String _NAME = "state";
    }

    public interface City {
        public String ID = "id";
        public String Name = "name";
        public String StateRef = "state_id";

        public String _ID = ID;
        public String _NAME = "city";
    }

    public interface TaskDate {
        public String ID = "id";
        public String Name = "name";
        public String Date = "task_date";
        public String TaskRef = "task_id";

        public String _ID = ID;
        public String _NAME = "task_date";
    }

    public interface Goal {
        public String ID = "id";
        public String Name = "name";
        public String TaskRef = "task_id";

        public String _ID = ID;
        public String _NAME = "goal";
    }



    public static String getQN(String tableName, String column) {
        return tableName + "." + column;
    }
}
