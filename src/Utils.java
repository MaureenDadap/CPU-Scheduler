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
    public static boolean checkProcessesTableExisting() {
        if (ProcessesTable.processesMap.isEmpty())
            return false;
        else
            return true;
    }

    /**
     * Creates a process and saves it into the processes map. It requires user input
     */
    public static void processCreator() {
        StringBuilder sBuilder = new StringBuilder();
        ProcessesTable processesTable = new ProcessesTable();

        String id;
        int arrival;
        int burst;

        System.out.println();
        System.out.println("# CREATE A NEW PROCESS");

        System.out.print("-> Enter the arrival time: ");
        arrival = App.scanner.nextInt();

        System.out.print("-> Enter the burst time: ");
        burst = App.scanner.nextInt();

        // Generate an id to be used as the key for the process
        sBuilder.append("P");
        sBuilder.append(ProcessesTable.processesMap.size() + 1);
        id = sBuilder.toString();

        // Use the TimeValues class to save integer primitives (arrival and burst) to an
        // object
        // the object will then be saved to the processes table/map
        ProcessesTable.TimeValues values = processesTable.new TimeValues(arrival, burst, 0, 0);
        ProcessesTable.processesMap.put(id, values);
    }

    /**
     * Creates a process and saves it into the processes map. It does not need user
     * input
     */
    public static void randomProcessCreator() {
        StringBuilder sBuilder = new StringBuilder();
        ProcessesTable processesTable = new ProcessesTable();
        Random random = new Random();

        String id;
        int arrival;
        int burst;
        int range = 10; // range of numbers for the randomizer

        System.out.println();
        System.out.println("# CREATE A NEW RANDOM PROCESS");

        arrival = random.nextInt(range);
        System.out.println("-> Arrival time: " + arrival);

        do {
            burst = random.nextInt(range);
        } while (burst == 0);

        System.out.println("-> Burst time: " + burst);

        // Generate an id to be used as the key for the process
        sBuilder.append("P");
        sBuilder.append(ProcessesTable.processesMap.size() + 1);
        id = sBuilder.toString();

        // Use the TimeValues class to save integer primitives (arrival and burst) to an
        // object
        // the object will then be saved to the processes table/map
        ProcessesTable.TimeValues values = processesTable.new TimeValues(arrival, burst, 0, 0);
        ProcessesTable.processesMap.put(id, values);
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
    public static void displayProcessesTable() {
        Set<Map.Entry<String, ProcessesTable.TimeValues>> entries = ProcessesTable.processesMap.entrySet();

        String leftAlignFormat = "| %-5s | %-9d | %-7d |%n";

        System.out.format("+-------+-----------+---------+%n");
        System.out.format("| ID    | ARRIVAL   | BURST   |%n");
        System.out.format("+-------+-----------+---------+%n");

        for (Map.Entry<String, ProcessesTable.TimeValues> entry : entries) {
            System.out.format(leftAlignFormat, entry.getKey(), entry.getValue().getArrival(),
                    entry.getValue().getBurst());
        }

        System.out.format("+-------+-----------+---------+%n");
    }

    /**
     * List sorter by arrival time; returns an ascending list
     * 
     * @param (list) list that contains the process map to be sorted by the arrival
     *               time stored in the TimeValues object
     */
    public static List<Map.Entry<String, ProcessesTable.TimeValues>> sortByArrival(
            List<Map.Entry<String, ProcessesTable.TimeValues>> list) {
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

        return list;
    }

    /**
     * Method that finds the waiting time for all processes
     * 
     * @param (list) list that contains the process map to be sorted by the arrival
     *               time stored in the TimeValues object, it is now a sorted copy
     *               of the original map
     */
    public static void findWaitingTime(List<Map.Entry<String, ProcessesTable.TimeValues>> list) {
        // waiting time for first process will be 0
        list.get(0).getValue().setWaitingTime(0);

        for (int i = 1; i < list.size(); i++) {
            list.get(i).getValue().setWaitingTime(
                    list.get(i - 1).getValue().getBurst() + list.get(i - 1).getValue().getWaitingTime());
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
    public static void createTableSummary(List<Map.Entry<String, ProcessesTable.TimeValues>> entries) {
        // Set<Map.Entry<String, ProcessesTable.TimeValues>> entries =
        // ProcessesTable.processesMap.entrySet();
        String leftAlignFormat = "| %-3s | %-4d | %-4d | %-4d | %-5d |%n";

        System.out.println();
        System.out.format("+----------------------------------+%n");
        System.out.format("|           TABLE SUMMARY          |%n");
        System.out.format("+-----+------+------+------+-------+%n");
        System.out.format("| ID  | AT   | BT   | WT   | tAT   |%n");
        System.out.format("+-----+------+------+------+-------+%n");

        for (Map.Entry<String, ProcessesTable.TimeValues> entry : entries) {
            System.out.format(leftAlignFormat, entry.getKey(), entry.getValue().getArrival(),
                    entry.getValue().getBurst(), entry.getValue().getWaitingTime(),
                    entry.getValue().getTurnAroundTime());
        }

        System.out.format("+-----+------+------+------+-------+%n");
    }

    /**
     * Used to create the Gantt Chart after the computation
     */
    public static void createGanttChart() {
        System.out.println();
        System.out.format("+------------------------------------------------+%n");
        System.out.format("|                  GANTT CHART                   |%n");
        System.out.format("+------------------------------------------------+%n");

        // TODO: create gantt chart after computation

        System.out.println();
    }
}
