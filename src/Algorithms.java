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

        // CALCULATE THE TIME VALUES
        Utils.findStartingTime(list);
        Utils.findCompletionTime(list);
        Utils.findWaitingTime(list);
        Utils.findTurnAroundTime(list);

        // CREATE THE TABLES
        Utils.createTableSummary(list, false);
        Utils.createGanttChart(list);

        // PRINT AVERAGES
        System.out.println("# Average Waiting Time: " + Utils.findAverageWT(list));
        System.out.println("# Average Turnaround Time: " + Utils.findAverageTaT(list));

        System.out.println("\n/////////////////////////////////////////////");
    }

    /**
     * (NON-PREEMPTIVE) Priority Algorithm. TODO: when using cpu scheduler tools the
     * results are already correct, but it looks like the version from sir's lecture
     * was different. The solution i'm thinking of is by doing another sort based on
     * whether the arrival time of process further down the queue is smaller than
     * the completion time of the ones above. Then check for higher priority or
     * something like that
     * 
     */
    public static void priorityScheduling(ProcessesTable processesTable) {
        List<Map.Entry<String, ProcessesTable.TimeValues>> list = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>(
                processesTable.processesMap.entrySet());

        // #1 - sort the processes according to arrival time and priority
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
                else {
                    a = o1.getValue().getPriority();
                    b = o2.getValue().getPriority();
                    if (a > b)
                        temp = +1;
                    else if (a < b)
                        temp = -1;
                    else
                        temp = 0;
                }
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

        // CALCULATE THE TIME VALUES
        Utils.findStartingTime(list);
        Utils.findCompletionTime(list);
        Utils.findWaitingTime(list);
        Utils.findTurnAroundTime(list);

        // CREATE THE TABLES
        Utils.createTableSummary(list, true);
        Utils.createGanttChart(list);

        // PRINT AVERAGES
        System.out.println("# Average Waiting Time: " + Utils.findAverageWT(list));
        System.out.println("# Average Turnaround Time: " + Utils.findAverageTaT(list));

        System.out.println("\n/////////////////////////////////////////////");
    }
}
