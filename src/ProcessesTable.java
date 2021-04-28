import java.util.HashMap;
import java.util.Map;

/**
 * Contains the table structure for the processes.
 */

public class ProcessesTable {
    public Map<String, TimeValues> processesMap = new HashMap<String, TimeValues>();

    /**
     * Wrapper class for the arrival and burst time values, as well as the starting
     * time, completion time, turnaround time, and the waiting time
     */
    class TimeValues {
        private int arrival;
        private int burst;
        private int turnAroundTime;
        private int waitingTime;
        private int priority;
        private int startingTime;

        public TimeValues(int arrival, int burst, int turnAroundTime, int waitingTime, int priority, int startingTime) {
            this.arrival = arrival;
            this.burst = burst;
            this.turnAroundTime = turnAroundTime;
            this.waitingTime = waitingTime;
            this.priority = priority;
            this.startingTime = startingTime;
        }

        public int getArrival() {
            return this.arrival;
        }

        public int getBurst() {
            return this.burst;
        }

        public int getTurnAroundTime() {
            return this.turnAroundTime;
        }

        public int getWaitingTime() {
            return this.waitingTime;
        }

        public int getPriority() {
            return this.priority;
        }

        public void setArrival(int arrival) {
            this.arrival = arrival;
        }

        public void setBurst(int burst) {
            this.burst = burst;
        }

        public void setTurnAroundTime(int turnAroundTime) {
            this.turnAroundTime = turnAroundTime;
        }

        public void setWaitingTime(int waitingTime) {
            this.waitingTime = waitingTime;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }



        public int getStartingTime() {
            return this.startingTime;
        }

        public void setStartingTime(int startingTime) {
            this.startingTime = startingTime;
        }

    }
}
