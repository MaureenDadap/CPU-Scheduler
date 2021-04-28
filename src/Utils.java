import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Class that contains all utility methods
 */
public class Utils {
    /**
     * Checks if the table/hashmap has content if empty = the value returned is
     * FALSE if not empty = the value returned is TRUE
     */
    public static boolean checkProcessesTableExisting(ProcessesTable processesTable) {
        if (processesTable.processesMap.isEmpty())
            return false;
        else
            return true;
    }

    /**
     * Creates a process and saves it into the processes map. It requires user input
     */
    public static void processCreator(ProcessesTable processesTable, boolean isPriority) {
        StringBuilder sBuilder = new StringBuilder();

        String id;
        int arrival;
        int burst;
        int priority = 1;

        System.out.println();
        System.out.println("# CREATE A NEW PROCESS");

        System.out.print("-> Enter the arrival time: ");
        arrival = App.scanner.nextInt();

        System.out.print("-> Enter the burst time: ");
        burst = App.scanner.nextInt();

        if (isPriority == true) {
            System.out.print("-> Enter the priority: ");
            priority = App.scanner.nextInt();
        }

        // Generate an id to be used as the key for the process
        sBuilder.append("P");
        sBuilder.append(processesTable.processesMap.size() + 1);
        id = sBuilder.toString();

        // Use the TimeValues class to save integer primitives (arrival and burst) to an
        // object
        // the object will then be saved to the processes table/map
        ProcessesTable.TimeValues values = processesTable.new TimeValues(arrival, burst, 0, 0, priority, 0);
        processesTable.processesMap.put(id, values);
    }

    /**
     * Creates a process and saves it into the processes map. It does not need user
     * input
     */
    public static void randomProcessCreator(ProcessesTable processesTable, boolean isPriority) {
        StringBuilder sBuilder = new StringBuilder();
        Random random = new Random();

        String id;
        int arrival;
        int burst;
        int priority = 1;
        int range = 10; // range of numbers for the randomizer
        int pRange = 5;

        System.out.println();
        System.out.println("# CREATE A NEW RANDOM PROCESS");

        arrival = random.nextInt(range);
        System.out.println("-> Arrival time: " + arrival);

        do {
            burst = random.nextInt(range);
        } while (burst == 0);

        System.out.println("-> Burst time: " + burst);

        if (isPriority == true) {
            do {
                priority = random.nextInt(pRange);
            } while (burst == 0);

            System.out.println("-> Priority: " + priority);
        }

        // Generate an id to be used as the key for the process
        sBuilder.append("P");
        sBuilder.append(processesTable.processesMap.size() + 1);
        id = sBuilder.toString();

        // Use the TimeValues class to save integer primitives (arrival and burst) to an
        // object
        // the object will then be saved to the processes table/map
        ProcessesTable.TimeValues values = processesTable.new TimeValues(arrival, burst, 0, 0, priority, 0);
        processesTable.processesMap.put(id, values);
    }

    // TODO: method that edits an existing process; low-priority
    public static void processEditor() {

    }

    // TODO: method that deletes an existing process; low-priority
    public static void processDeleter() {

    }

    /**
     * Displays a table of the existing processes created
     */
    public static void displayProcessesTable(ProcessesTable processesTable, boolean isPriorityVisible) {
        Set<Map.Entry<String, ProcessesTable.TimeValues>> entries = processesTable.processesMap.entrySet();
        String leftAlignFormat;

        if (isPriorityVisible == false) {
            leftAlignFormat = "| %-5s | %-9d | %-7d |%n";

            System.out.format("+-------+-----------+---------+%n");
            System.out.format("| ID    | ARRIVAL   | BURST   |%n");
            System.out.format("+-------+-----------+---------+%n");

            for (Map.Entry<String, ProcessesTable.TimeValues> entry : entries) {
                System.out.format(leftAlignFormat, entry.getKey(), entry.getValue().getArrival(),
                        entry.getValue().getBurst());
            }
            System.out.format("+-------+-----------+---------+%n");

        } else {
            leftAlignFormat = "| %-5s | %-9d | %-7d | %-8d |%n";

            System.out.format("+-------+-----------+---------+----------+%n");
            System.out.format("| ID    | ARRIVAL   | BURST   | PRIORITY |%n");
            System.out.format("+-------+-----------+---------+----------+%n");

            for (Map.Entry<String, ProcessesTable.TimeValues> entry : entries) {
                System.out.format(leftAlignFormat, entry.getKey(), entry.getValue().getArrival(),
                        entry.getValue().getBurst(), entry.getValue().getPriority());
            }
            System.out.format("+-------+-----------+---------+----------+%n");
        }

    }

    /**
     * Method that finds the waiting time for all processes
     * 
     * @param (list) list that contains the process map to be sorted by the arrival
     *               time stored in the TimeValues object, it is now a sorted copy
     *               of the original map
     */
    public static void findStartingTime(List<Map.Entry<String, ProcessesTable.TimeValues>> list) {

        // waiting time for first process will be 0
        list.get(0).getValue().setStartingTime(list.get(0).getValue().getArrival());

        for (int i = 1; i < list.size(); i++) {

            list.get(i).getValue().setStartingTime(
                    list.get(i - 1).getValue().getBurst() + list.get(i - 1).getValue().getStartingTime());
        }
    }

    /**
     * Method that finds the waiting time for all processes
     * 
     * @param (list) list that contains the process map to be sorted by the arrival
     *               time stored in the TimeValues object, it is now a sorted copy
     *               of the original map
     */
    public static void findWaitingTime(List<Map.Entry<String, ProcessesTable.TimeValues>> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getValue().setWaitingTime(
                    list.get(i).getValue().getStartingTime() - list.get(i).getValue().getArrival());
        }
    }

    /**
     * Method that finds the turn around time for all processes
     * 
     * @param (list) list that contains the process map to be sorted by the arrival
     *               time stored in the TimeValues object, it is now a sorted copy
     *               of the original map
     */
    public static void findTurnAroundTime(List<Map.Entry<String, ProcessesTable.TimeValues>> list) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getValue()
                    .setTurnAroundTime(list.get(i).getValue().getBurst() + list.get(i).getValue().getWaitingTime());
        }
    }

    /**
     * Method that finds the average waiting time
     * 
     * @param (list) list that contains the process map to be sorted by the arrival
     *               time stored in the TimeValues object, it is now a sorted copy
     *               of the original map
     */
    public static float findAverageWT(List<Map.Entry<String, ProcessesTable.TimeValues>> list) {
        int totalWt = 0;
        for (int i = 0; i < list.size(); i++) {
            totalWt += list.get(i).getValue().getWaitingTime();
        }

        return (float) totalWt / list.size();
    }

    /**
     * Method that finds the average turn around time
     * 
     * @param (list) list that contains the process map to be sorted by the arrival
     *               time stored in the TimeValues object, it is now a sorted copy
     *               of the original map
     */
    public static float findAverageTaT(List<Map.Entry<String, ProcessesTable.TimeValues>> list) {
        int totalTaT = 0;
        for (int i = 0; i < list.size(); i++) {
            totalTaT += list.get(i).getValue().getTurnAroundTime();
        }

        return (float) totalTaT / list.size();
    }

    /**
     * Displays a table of the summary of data after computation
     * 
     * @param (list) list that contains the process map to be sorted by the arrival
     *               time stored in the TimeValues object, it is now a computed copy
     *               of the original map
     */
    public static void createTableSummary(List<Map.Entry<String, ProcessesTable.TimeValues>> list, boolean isPriority) {
        String leftAlignFormat;

        if (isPriority == false) {
            leftAlignFormat = "| %-3s | %-4d | %-4d | %-4d | %-4d | %-4d |%n";

            System.out.println();
            System.out.format("+----------------------------------------+%n");
            System.out.format("|              TABLE SUMMARY             |%n");
            System.out.format("+-----+------+------+------+------+------+%n");
            System.out.format("| ID  | AT   | BT   | ST   | WT   | tAT  |%n");
            System.out.format("+-----+------+------+------+------+------+%n");

            for (Map.Entry<String, ProcessesTable.TimeValues> i : list) {
                System.out.format(leftAlignFormat, i.getKey(), i.getValue().getArrival(), i.getValue().getBurst(),
                        i.getValue().getStartingTime(), i.getValue().getWaitingTime(),
                        i.getValue().getTurnAroundTime());
            }

            System.out.format("+-----+------+------+------+------+-------+%n");
        } else {
            leftAlignFormat = "| %-3s | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d |%n";

            System.out.println();
            System.out.format("+-----------------------------------------------+%n");
            System.out.format("|                  TABLE SUMMARY                |%n");
            System.out.format("+-----+------+------+------+------+------+------+%n");
            System.out.format("| ID  | AT   | BT   | WT   | WT   | tAT  | P    |%n");
            System.out.format("+-----+------+------+------+------+------+------+%n");

            for (Map.Entry<String, ProcessesTable.TimeValues> i : list) {
                System.out.format(leftAlignFormat, i.getKey(), i.getValue().getArrival(), i.getValue().getBurst(),
                        i.getValue().getStartingTime(), i.getValue().getWaitingTime(), i.getValue().getTurnAroundTime(),
                        i.getValue().getPriority());
            }

            System.out.format("+-----+------+------+------+------+------+------+%n");
        }

    }

    /**
     * Used to create the Gantt Chart after the computation
     */
    public static void createGanttChart(List<Map.Entry<String, ProcessesTable.TimeValues>> list) {
        System.out.println();
        System.out.format("+------------------------------------------------+%n");
        System.out.format("|                  GANTT CHART                   |%n");
        System.out.format("+------------------------------------------------+%n");

        // TODO: create gantt chart after computation

        System.out.println();
    }
}
