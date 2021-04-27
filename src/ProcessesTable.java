import java.util.HashMap;
import java.util.Map;

/**
 * Contains the table structure for the processes.
 */

public class ProcessesTable {
    public static Map<String, TimeValues> processesMap = new HashMap<String, TimeValues>();

    /**
     * Wrapper class for the arrival and burst time values
     */
    class TimeValues {
        private int arrival;
        private int burst;
        private int startingTime;
        private int completionTime;
        private int turnAroundTime;
        private int waitingTime;

        public TimeValues(int arrival, int burst, int startingTime, int completionTime, int turnAroundTime,
                int waitingTime) {
            this.arrival = arrival;
            this.burst = burst;
            this.startingTime = startingTime;
            this.completionTime = completionTime;
            this.turnAroundTime = turnAroundTime;
            this.waitingTime = waitingTime;
        }

        public int getArrival() {
            return this.arrival;
        }

        public int getBurst() {
            return this.burst;
        }

        public int getStartingTime() {
            return this.startingTime;
        }

        public int getCompletionTime() {
            return this.completionTime;
        }

        public int getTurnAroundTime() {
            return this.turnAroundTime;
        }

        public int getWaitingTime() {
            return this.waitingTime;
        }

        public void setArrival(int arrival) {
            this.arrival = arrival;
        }

        public void setBurst(int burst) {
            this.burst = burst;
        }

        public void setStartingTime(int startingTime) {
            this.startingTime = startingTime;
        }

        public void setCompletionTime(int completionTime) {
            this.completionTime = completionTime;
        }

        public void setTurnAroundTime(int turnAroundTime) {
            this.turnAroundTime = turnAroundTime;
        }

        public void setWaitingTime(int waitingTime) {
            this.waitingTime = waitingTime;
        }

    }
}
