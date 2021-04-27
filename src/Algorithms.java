import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class that contains all algorithms
 */
public class Algorithms {
    /**
     * First Come First Serve Algorithm
     */
    public static void firstComeFirstServe() {
        List<Map.Entry<String, ProcessesTable.TimeValues>> list = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>(
                ProcessesTable.processesMap.entrySet());
        int startingTime;
        int completionTime;
        int turnAroundTime;
        int waitingTime;

        // #1 - sort the processes according to arrival time
        // Sort the list
        list = Utils.sortByArrival(list);

        for (Entry<String, ProcessesTable.TimeValues> i : list) {
            System.out.print(i.getKey() + "\t");
            System.out.print(i.getValue().getArrival() + "\t");
            System.out.print(i.getValue().getBurst() + "\n");
        }

        Utils.createTableSummary();
        Utils.createGanttChart();

        System.out.println("Average Waiting Time: ");
        System.out.println("Average Turnaround Time: ");
    }

    /**
     * Shortest Job First Algorithm
     */
    public static void shortestJobFirst() {

    }
}
