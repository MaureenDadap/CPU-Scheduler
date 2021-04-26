import java.util.HashMap;
import java.util.Map;

/**
 * Contains the table structure for the processes.
 */

public class ProcessesTable {
    /**
     * Wrapper class for the arrival and burst time values
     */
    class TimeValues {
        private int arrival;
        private int burst;

        public TimeValues(int arrival, int burst) {
            this.arrival = arrival;
            this.burst = burst;
        }

        public int getArrival() {
            return this.arrival;
        }

        public int getBurst() {
            return this.burst;
        }

        public void setArrival(int arrival) {
            this.arrival = arrival;
        }

        public void setBurst(int burst) {
            this.burst = burst;
        }

    }

    public static Map<String, TimeValues> processesMap = new HashMap<String, TimeValues>();
}
