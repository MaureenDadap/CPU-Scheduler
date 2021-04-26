import java.util.Hashtable;
import java.util.Map;

/**
 * Contains the table structure for the processes. 
 * TODO: methods for inputs and reads
 */

public class Processes {
    public int arrival;
    public int burst;

    public Processes(int arrival, int burst) {
        this.arrival = arrival;
        this.burst = burst;
    }

    static Map<String, Processes> container = new Hashtable<String, Processes>();
    
}
