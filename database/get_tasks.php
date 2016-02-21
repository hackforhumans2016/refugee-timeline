<?php
     class RedeemAPI {
        private $db1;
        private $db2;
     
        // Constructor - open DB connection
        function __construct() {
            $this->db1 = mysqli_connect('localhost', 'XXX', 'XXX', 'XXX');
            $this->db2 = mysqli_connect('localhost', 'XXX', 'XXX', 'XXX');
            $this->db1->autocommit(FALSE);
            $this->db2->autocommit(FALSE);
        }
     
        // Destructor - close DB connection
        function __destruct() {
            $this->db1->close();
            $this->db2->close();
        }
     
        function redeem() {
            $stmt1 = mysqli_query($this->db1, "SELECT id, name, description, duration, started, finished, city_id, state_id, task_id_predecessor FROM task");

            $arr = array();

            while ($r1 = mysqli_fetch_assoc($stmt1)) {
                //echo "task: ", json_encode($r1, JSON_UNESCAPED_SLASHES), "<br>";

                $stmt2 = mysqli_query($this->db2, ("SELECT id, task_date FROM task_date WHERE task_id = " . $r1["id"]));
                $arr_dates = array();

                while ($r2 = mysqli_fetch_assoc($stmt2)) {
                    $arr_dates[] = $r2["task_date"]; 
                    //echo "task_dates: ", json_encode($r2, JSON_UNESCAPED_SLASHES), "<br>";
                }

                $tmp_array = $r1;
                $tmp_array["fixed"] = $arr_dates;

                $arr[] = $tmp_array;
            }

            return json_encode($arr, JSON_UNESCAPED_SLASHES);
        }
    }

    $api = new RedeemAPI;
    exit($api->redeem());
?>