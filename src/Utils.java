import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
        ProcessesTable.TimeValues values = processesTable.new TimeValues(arrival, burst, 0, 0, 0, 0);
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
        int range = 20; // range of numbers for the randomizer

        System.out.println();
        System.out.println("# CREATE A NEW RANDOM PROCESS");

        arrival = random.nextInt(range);
        System.out.println("-> Arrival time: " + arrival);

        burst = random.nextInt(range);
        System.out.println("-> Burst time: " + burst);

        // Generate an id to be used as the key for the process
        sBuilder.append("P");
        sBuilder.append(ProcessesTable.processesMap.size() + 1);
        id = sBuilder.toString();

        // Use the TimeValues class to save integer primitives (arrival and burst) to an
        // object
        // the object will then be saved to the processes table/map
        ProcessesTable.TimeValues values = processesTable.new TimeValues(arrival, burst, 0, 0, 0, 0);
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
     * Displays a table of the summary of data after computation
     */
    public static void createTableSummary() {
        Set<Map.Entry<String, ProcessesTable.TimeValues>> entries = ProcessesTable.processesMap.entrySet();
        String leftAlignFormat = "| %-3s | %-4d | %-4d | %-4d | %-4d | %-4d | %-5d |%n";

        System.out.println();
        System.out.format("+------------------------------------------------+%n");
        System.out.format("|                 TABLE SUMMARY                  |%n");
        System.out.format("+-----+------+------+------+------+------+-------+%n");
        System.out.format("| ID  | AT   | BT   | ST   | CT   | WT   | tAT   |%n");
        System.out.format("+-----+------+------+------+------+------+-------+%n");

        for (Map.Entry<String, ProcessesTable.TimeValues> entry : entries) {
            System.out.format(leftAlignFormat, entry.getKey(), entry.getValue().getArrival(),
                    entry.getValue().getBurst(), entry.getValue().getStartingTime(),
                    entry.getValue().getCompletionTime(), entry.getValue().getWaitingTime(),
                    entry.getValue().getTurnAroundTime());
        }

        System.out.format("+-----+------+------+------+------+------+-------+%n");
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
    }
}
