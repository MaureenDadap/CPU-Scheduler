import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper class that will hold the data after computation. Data will include:
 * 1. starting time 2. completion time 3. turn around time (or average turn
 * around time yata) 4. waiting time (or average waiting time yata) I'm still
 * unsure about this part, I might just move the whole class to the
 * ProcessesTable.java file
 */

public class Data {
    private int startingTime;
    private int completionTime;
    private int turnAroundTime;
    private int waitingTime;

    public Map<String, Data> summaryTable = new HashMap<String, Data>();

    public Data(int startingTime, int completionTime, int turnAroundTime, int waitingTime) {
        this.startingTime = startingTime;
        this.completionTime = completionTime;
        this.turnAroundTime = turnAroundTime;
        this.waitingTime = waitingTime;
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
