import java.util.ArrayList;
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
        ProcessesTable.TimeValues values = processesTable.new TimeValues(arrival, burst, 0, 0, priority, 0, 0);
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
                priority = random.nextInt(range);
            } while (priority == 0);

            System.out.println("-> Priority: " + priority);
        }

        // Generate an id to be used as the key for the process
        sBuilder.append("P");
        sBuilder.append(processesTable.processesMap.size() + 1);
        id = sBuilder.toString();

        // Use the TimeValues class to save integer primitives (arrival and burst) to an
        // object
        // the object will then be saved to the processes table/map
        ProcessesTable.TimeValues values = processesTable.new TimeValues(arrival, burst, 0, 0, priority, 0, 0);
        processesTable.processesMap.put(id, values);
    }

    /**
     * Displays a table of the existing processes created
     */
    public static void displayProcessesTable(ProcessesTable processesTable, boolean isPriority) {
        Set<Map.Entry<String, ProcessesTable.TimeValues>> entries = processesTable.processesMap.entrySet();
        String leftAlignFormat;

        if (isPriority == false) {
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
            leftAlignFormat = "| %-3s | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d |%n";

            System.out.println();
            System.out.format("+-----------------------------------------------+%n");
            System.out.format("|                  TABLE SUMMARY                |%n");
            System.out.format("+-----+------+------+------+------+------+------+%n");
            System.out.format("| ID  | AT   | BT   | ST   | CT   | WT   | tAT  |%n");
            System.out.format("+-----+------+------+------+------+------+------+%n");

            for (Map.Entry<String, ProcessesTable.TimeValues> i : list) {
                System.out.format(leftAlignFormat, i.getKey(), i.getValue().getArrival(), i.getValue().getBurst(),
                        i.getValue().getStartingTime(), i.getValue().getCompletionTime(), i.getValue().getWaitingTime(),
                        i.getValue().getTurnAroundTime());
            }

            System.out.format("+-----+------+------+------+------+------+------+%n");
        } else {
            leftAlignFormat = "| %-3s | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d |%n";

            System.out.println();
            System.out.format("+------------------------------------------------------+%n");
            System.out.format("|                      TABLE SUMMARY                   |%n");
            System.out.format("+-----+------+------+------+------+------+------+------+%n");
            System.out.format("| ID  | AT   | BT   | ST   | CT   | WT   | tAT  | P    |%n");
            System.out.format("+-----+------+------+------+------+------+------+------+%n");

            for (Map.Entry<String, ProcessesTable.TimeValues> i : list) {
                System.out.format(leftAlignFormat, i.getKey(), i.getValue().getArrival(), i.getValue().getBurst(),
                        i.getValue().getStartingTime(), i.getValue().getCompletionTime(), i.getValue().getWaitingTime(),
                        i.getValue().getTurnAroundTime(), i.getValue().getPriority());
            }

            System.out.format("+-----+------+------+------+------+------+------+------+%n");
        }
    }

    /**
     * NOT YET USED Displays a table of the summary of data after computation (for
     * SRT Version)
     * 
     * @param (list) list that contains the process map to be sorted by the arrival
     *               time stored in the TimeValues object, it is now a computed copy
     *               of the original map
     */
    public static void createTableSummarySRT(List<Map.Entry<String, ProcessesTable.TimeValues>> list) {
        String leftAlignFormat = "| %-3s | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d |%n";

        System.out.println();
        System.out.format("+-------------------------------------------------------------+%n");
        System.out.format("|                         TABLE SUMMARY                       |%n");
        System.out.format("+-----+------+------+------+------+------+------+------+------+%n");
        System.out.format("| ID  | AT   | BT   | ST   | WQT  | CT   | PWT  | WT   | tAT  |%n");
        System.out.format("+-----+------+------+------+------+------+------+------+------+%n");

        for (Map.Entry<String, ProcessesTable.TimeValues> i : list) {
            System.out.format(leftAlignFormat, i.getKey(), i.getValue().getArrival(), i.getValue().getBurst(),
                    i.getValue().getStartingTime(), i.getValue().getCompletionTime(), i.getValue().getWaitingTime(),
                    i.getValue().getTurnAroundTime());
        }

        System.out.format("+-----+------+------+------+------+------+------+------+------+%n");
    }

    /**
     * Used to create the Gantt Chart after the computation
     */
    public static void createGanttChart(List<Map.Entry<String, ProcessesTable.TimeValues>> list) {
        String processLabel = "  %-2s  |";
        String times = "| %-2d   ";

        boolean beginsAtZero = true;
        if (list.get(0).getValue().getStartingTime() != 0) {
            beginsAtZero = false;
        }

        System.out.println();
        System.out.format("+------------------------------------------------+%n");
        System.out.format("|                  GANTT CHART                   |%n");
        System.out.format("+------------------------------------------------+%n");

        if (!beginsAtZero) {
            System.out.print("|      ");
        }

        System.out.print("|");
        for (Map.Entry<String, ProcessesTable.TimeValues> i : list) {
            System.out.format(processLabel, i.getKey());

        }
        System.out.println();

        if (!beginsAtZero) {
            System.out.print("+------");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print("+------");
        }
        System.out.println();

        if (!beginsAtZero) {
            System.out.print("^      ");
        }
        System.out.print("^");
        for (int i = 0; i < list.size(); i++) {
            System.out.print("      ^");
        }
        System.out.println();

        if (!beginsAtZero) {
            System.out.format(times, 0);
        }
        System.out.format(times, list.get(0).getValue().getStartingTime());
        for (Map.Entry<String, ProcessesTable.TimeValues> i : list) {
            System.out.format(times, i.getValue().getCompletionTime());
        }

        System.out.println();
        System.out.println();
    }
}
