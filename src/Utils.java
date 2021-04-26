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
        // TODO: process creation here; with user input
    }

    /**
     * Creates a process and saves it into the processes map. It does not need user
     * input
     */
    public static void randomProcessCreator() {
        // TODO: process creation here; with NO user input
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
