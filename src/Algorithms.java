import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class that contains all algorithms
 */
public class Algorithms {
    /**
     * (NON-PREEMPTIVE) First Come First Serve Algorithm
     */
    public static void firstComeFirstServe(ProcessesTable processesTable) {
        List<Map.Entry<String, ProcessesTable.TimeValues>> list = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>(
                processesTable.processesMap.entrySet());

        // #1 - sort the processes according to arrival time
        Collections.sort(list, new Comparator<Map.Entry<String, ProcessesTable.TimeValues>>() {
            public int compare(Map.Entry<String, ProcessesTable.TimeValues> o1,
                    Map.Entry<String, ProcessesTable.TimeValues> o2) {

                int temp;
                int a = o1.getValue().getArrival();
                int b = o2.getValue().getArrival();

                if (a > b)
                    temp = +1;
                else if (a < b)
                    temp = -1;
                else
                    temp = 0;

                return temp;
            }
        });

        // CODE BELOW USED TO PRINT SORTED LIST, USED TO DEBUG ONLY
        // for (Entry<String, ProcessesTable.TimeValues> i : list) {
        // System.out.print(i.getKey() + "\t");
        // System.out.print(i.getValue().getArrival() + "\t");
        // System.out.print(i.getValue().getBurst() + "\n");
        // }

        // #2 - determine waiting times
        Utils.findStartingTime(list);
        Utils.findWaitingTime(list);

        // #3 - find turnaround time
        Utils.findTurnAroundTime(list);

        // CREATE THE TABLES
        Utils.createTableSummary(list, false);
        Utils.createGanttChart(list);

        // PRINT AVERAGES
        System.out.println("# Average Waiting Time: " + Utils.findAverageWT(list));
        System.out.println("# Average Turnaround Time: " + Utils.findAverageTaT(list));
    }

    /**
     * (NON-PREEMPTIVE) Priority Algorithm
     */
    public static void priorityScheduling(ProcessesTable processesTable) {
        List<Map.Entry<String, ProcessesTable.TimeValues>> list = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>(
                processesTable.processesMap.entrySet());

        // # 1 - sort according to priority
        Collections.sort(list, new Comparator<Map.Entry<String, ProcessesTable.TimeValues>>() {
            public int compare(Map.Entry<String, ProcessesTable.TimeValues> o1,
                    Map.Entry<String, ProcessesTable.TimeValues> o2) {

                int temp;
                int a = o1.getValue().getPriority();
                int b = o2.getValue().getPriority();

                if (a > b)
                    temp = +1;
                else if (a < b)
                    temp = -1;
                else
                    temp = 0;

                return temp;
            }
        });

        // CODE BELOW USED TO PRINT LIST, USED TO DEBUG ONLY
        // for (Entry<String, ProcessesTable.TimeValues> i : list) {
        // System.out.print(i.getKey() + "\t");
        // System.out.print(i.getValue().getArrival() + "\t");
        // System.out.print(i.getValue().getBurst() + "\t");
        // System.out.print(i.getValue().getPriority() + "\n");
        // }

        // #2 - determine waiting times
        Utils.findWaitingTime(list);

        // #3 - find turnaround time
        Utils.findTurnAroundTime(list);

        // CREATE THE TABLES
        Utils.createTableSummary(list, true);
        Utils.createGanttChart(list);

        // PRINT AVERAGES
        System.out.println("# Average Waiting Time: " + Utils.findAverageWT(list));
        System.out.println("# Average Turnaround Time: " + Utils.findAverageTaT(list));
    }
}
