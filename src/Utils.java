import java.util.Map;
import java.util.Random;
import java.util.Set;

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
        ProcessesTable.TimeValues values = processesTable.new TimeValues(arrival, burst);
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
        ProcessesTable.TimeValues values = processesTable.new TimeValues(arrival, burst);
        ProcessesTable.processesMap.put(id, values);
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
            System.out.format(leftAlignFormat, entry.getKey(), entry.getValue().getArrival(), entry.getValue().getBurst());
        }

        System.out.format("+-------+-----------+---------+%n");
    }

    /**
     * Used to create the table summary after the computation
     */
    public static void createTableSummary() {
        // TODO: create table summary after computation
    }

    /**
     * Used to create the Gantt Chart after the computation
     */
    public static void createGanttChart() {
        // TODO: create gantt chart after computation
    }
}
