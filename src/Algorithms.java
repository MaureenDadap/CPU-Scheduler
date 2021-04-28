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

        // #1 - sort the processes according to arrival time
        list = Utils.sortByArrival(list);

        // #2 - determine waiting times
        Utils.findWaitingTime(list);

        // #3 - find turnaround time
        Utils.findTurnAroundTime(list);

        //CODE BELOW USED TO PRINT SORTED LIST, USED TO DEBUG ONLY
        // for (Entry<String, ProcessesTable.TimeValues> i : list) {
        //     System.out.print(i.getKey() + "\t");
        //     System.out.print(i.getValue().getArrival() + "\t");
        //     System.out.print(i.getValue().getBurst() + "\n");
        // }


        // CREATE THE TABLES
        Utils.createTableSummary(list);
        Utils.createGanttChart();

        // PRINT AVERAGES
        System.out.println("# Average Waiting Time: " + Utils.findAverageWT(list));
        System.out.println("# Average Turnaround Time: " + Utils.findAverageTaT(list));
    }
}
