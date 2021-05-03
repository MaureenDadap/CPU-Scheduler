import java.util.Scanner;

public class App {
    static Scanner scanner;

    /**
     * Main menu method. Separated from main method for recursive calls
     */
    private static void menu() {
        ProcessesTable processesTable = new ProcessesTable();
        char choice = '0';
        do {
            System.out.println();
            System.out.println("CHOOSE THE SCHEDULING ALGORITHM");
            System.out.println("-------------------------------");
            System.out.println("( 1 ) First Come First Serve (FCFS)");
            System.out.println("( 2 ) Shortest Job First (SJF)");
            System.out.println("( 3 ) Shortest Remaining Time (SRT)");
            System.out.println("( 4 ) Priority Scheduling");
            System.out.println("( 5 ) Round-Robin (RR)");
            System.out.println("( 6 ) Multi-level Queue Scheduling");
            System.out.println("( 0 ) EXIT");
            System.out.print("CHOICE: ");

            choice = scanner.next().charAt(0);

            if (choice == '1') {
                System.out.println();
                System.out.println("FIRST COME FIRST SERVE ALGORITHM");
                System.out.println("--------------------------------");

                firstComeFirstServe(processesTable);

            } else if (choice == '2') {
                System.out.println();
                System.out.println("SHORTEST JOB FIRST ALGORITHM");
                System.out.println("--------------------------------");

                shortestJobFirst(processesTable);

            } else if (choice == '3') {
                System.out.println();
                System.out.println("SHORTEST REMAINING TIME ALGORITHM");
                System.out.println("--------------------------------");

                shortestRemainingTime(processesTable);

            } else if (choice == '4') {
                System.out.println();
                System.out.println("(NON-PREEMPTIVE) PRIORITY SCHEDULING ALGORITHM");
                System.out.println("--------------------------------");

                priorityScheduling(processesTable);

            } else if (choice == '5') {
                System.out.println();
                System.out.println("ROUND-ROBIN ALGORITHM");
                System.out.println("--------------------------------");

                RoundRobin.roundRobin();

            } else if (choice == '6') {
                System.out.println();
                System.out.println("MULTI-LEVEL QUEUE ALGORITHM");
                System.out.println("--------------------------------");

                multilevelQueue(processesTable);

            } else if (choice == '0') {
                System.exit(0);
            } else {
                System.out.println("\t ________________________________________________");
                System.out.println("\t||                                                ||");
                System.out.println("\t|| > INVALID INPUT                                ||");
                System.out.println("\t||________________________________________________||");
            }
            System.out.println(); // new line after sub-menu
        } while (choice != '0');
    }

    /**
     * First Come First Serve sub menu method
     */
    private static void firstComeFirstServe(ProcessesTable processesTable) {
        char ch = '0';

        // Check first if a process exists; if none exist:
        if (Utils.checkProcessesTableExisting(processesTable) == false) {
            System.out.println("\t ________________________________________________");
            System.out.println("\t||                                                ||");
            System.out.println("\t|| > THERE ARE CURRENTLY NO PROCESSES EXISTING    ||");
            System.out.println("\t||________________________________________________||");
            System.out.println();

            // Sub-menu for actions to be taken.
            // It is the same as if there are existing processes,
            // except that it doesn't have the "Compute" option
            do {
                System.out.println("( 1 ) Create a process");
                System.out.println("( 2 ) Generate a random process");
                System.out.println("( 0 ) Exit");
                System.out.print("CHOICE: ");

                ch = scanner.next().charAt(0);

                if (ch == '1') {
                    Utils.processCreator(processesTable, false);
                    break;
                } else if (ch == '2') {
                    Utils.randomProcessCreator(processesTable, false);
                    break;
                } else if (ch == '0') {
                    processesTable = null; // garbage
                    menu();
                } else {
                    System.out.println("\t ________________________________________________");
                    System.out.println("\t||                                                ||");
                    System.out.println("\t|| > INVALID INPUT                                ||");
                    System.out.println("\t||________________________________________________||");
                }
            } while (ch != '0');

            firstComeFirstServe(processesTable);
        }

        // Check first if a process exists; if a process exists:
        else {
            // display the processes table
            System.out.println();
            Utils.displayProcessesTable(processesTable, false);

            // Sub-menu for actions to be taken.
            // It is the same as if there are no existing processes,
            // except that it has the "Compute" option
            do {
                System.out.println("( 1 ) Compute");
                System.out.println("( 2 ) Create a process");
                System.out.println("( 3 ) Generate a random process");
                System.out.println("( 0 ) Exit");
                System.out.print("CHOICE: ");

                ch = scanner.next().charAt(0);

                if (ch == '1') {
                    Algorithms.firstComeFirstServe(processesTable);
                    break;
                } else if (ch == '2') {
                    Utils.processCreator(processesTable, false);
                    break;
                } else if (ch == '3') {
                    Utils.randomProcessCreator(processesTable, false);
                    break;
                } else if (ch == '0') {
                    processesTable = null; // garbage
                    menu();
                } else {
                    System.out.println("\t ________________________________________________");
                    System.out.println("\t||                                                ||");
                    System.out.println("\t|| > INVALID INPUT                                ||");
                    System.out.println("\t||________________________________________________||");
                }
            } while (ch != '0');

            firstComeFirstServe(processesTable);

        }
    }

    /**
     * Shortest Job First Version 2 sub menu method
     */
    private static void shortestJobFirst(ProcessesTable processesTable) {
        char ch = '0';

        // Check first if a process exists; if none exist:
        if (Utils.checkProcessesTableExisting(processesTable) == false) {
            System.out.println("\t ________________________________________________");
            System.out.println("\t||                                                ||");
            System.out.println("\t|| > THERE ARE CURRENTLY NO PROCESSES EXISTING    ||");
            System.out.println("\t||________________________________________________||");
            System.out.println();

            // Sub-menu for actions to be taken.
            // It is the same as if there are existing processes,
            // except that it doesn't have the "Compute" option
            do {
                System.out.println("( 1 ) Create a process");
                System.out.println("( 2 ) Generate a random process");
                System.out.println("( 0 ) Exit");
                System.out.print("CHOICE: ");

                ch = scanner.next().charAt(0);

                if (ch == '1') {
                    Utils.processCreator(processesTable, false);
                    break;
                } else if (ch == '2') {
                    Utils.randomProcessCreator(processesTable, false);
                    break;
                } else if (ch == '0') {
                    processesTable = null; // garbage
                    menu();
                } else {
                    System.out.println("\t ________________________________________________");
                    System.out.println("\t||                                                ||");
                    System.out.println("\t|| > INVALID INPUT                                ||");
                    System.out.println("\t||________________________________________________||");
                }
            } while (ch != '0');

            shortestJobFirst(processesTable);
        }

        // Check first if a process exists; if a process exists:
        else {
            // display the processes table
            System.out.println();
            Utils.displayProcessesTable(processesTable, false);

            // Sub-menu for actions to be taken.
            // It is the same as if there are no existing processes,
            // except that it has the "Compute" option
            do {
                System.out.println("( 1 ) Compute");
                System.out.println("( 2 ) Create a process");
                System.out.println("( 3 ) Generate a random process");
                System.out.println("( 0 ) Exit");
                System.out.print("CHOICE: ");

                ch = scanner.next().charAt(0);

                if (ch == '1') {
                    Algorithms.shortestJobFirstVer2(processesTable);
                    break;
                } else if (ch == '2') {
                    Utils.processCreator(processesTable, false);
                    break;
                } else if (ch == '3') {
                    Utils.randomProcessCreator(processesTable, false);
                    break;
                } else if (ch == '0') {
                    processesTable = null; // garbage
                    menu();
                } else {
                    System.out.println("\t ________________________________________________");
                    System.out.println("\t||                                                ||");
                    System.out.println("\t|| > INVALID INPUT                                ||");
                    System.out.println("\t||________________________________________________||");
                }
            } while (ch != '0');

            shortestJobFirst(processesTable);

        }
    }

    private static void shortestRemainingTime(ProcessesTable processesTable) {
        char ch = '0';

        // Check first if a process exists; if none exist:
        if (Utils.checkProcessesTableExisting(processesTable) == false) {
            System.out.println("\t ________________________________________________");
            System.out.println("\t||                                                ||");
            System.out.println("\t|| > THERE ARE CURRENTLY NO PROCESSES EXISTING    ||");
            System.out.println("\t||________________________________________________||");
            System.out.println();

            // Sub-menu for actions to be taken.
            // It is the same as if there are existing processes,
            // except that it doesn't have the "Compute" option
            do {
                System.out.println("( 1 ) Create a process");
                System.out.println("( 2 ) Generate a random process");
                System.out.println("( 0 ) Exit");
                System.out.print("CHOICE: ");

                ch = scanner.next().charAt(0);

                if (ch == '1') {
                    Utils.processCreator(processesTable, false);
                    break;
                } else if (ch == '2') {
                    Utils.randomProcessCreator(processesTable, false);
                    break;
                } else if (ch == '0') {
                    processesTable = null; // garbage
                    menu();
                } else {
                    System.out.println("\t ________________________________________________");
                    System.out.println("\t||                                                ||");
                    System.out.println("\t|| > INVALID INPUT                                ||");
                    System.out.println("\t||________________________________________________||");
                }
            } while (ch != '0');

            shortestRemainingTime(processesTable);
        }

        // Check first if a process exists; if a process exists:
        else {
            // display the processes table
            System.out.println();
            Utils.displayProcessesTable(processesTable, false);

            // Sub-menu for actions to be taken.
            // It is the same as if there are no existing processes,
            // except that it has the "Compute" option
            do {
                System.out.println("( 1 ) Compute");
                System.out.println("( 2 ) Create a process");
                System.out.println("( 3 ) Generate a random process");
                System.out.println("( 0 ) Exit");
                System.out.print("CHOICE: ");

                ch = scanner.next().charAt(0);

                if (ch == '1') {
                    Algorithms.firstComeFirstServe(processesTable);
                    break;
                } else if (ch == '2') {
                    Utils.processCreator(processesTable, false);
                    break;
                } else if (ch == '3') {
                    Utils.randomProcessCreator(processesTable, false);
                    break;
                } else if (ch == '0') {
                    processesTable = null; // garbage
                    menu();
                } else {
                    System.out.println("\t ________________________________________________");
                    System.out.println("\t||                                                ||");
                    System.out.println("\t|| > INVALID INPUT                                ||");
                    System.out.println("\t||________________________________________________||");
                }
            } while (ch != '0');

            firstComeFirstServe(processesTable);
        }
    }

    /**
     * First Come First Serve sub menu method
     */
    private static void priorityScheduling(ProcessesTable processesTable) {
        char ch = '0';

        // Check first if a process exists; if none exist:
        if (Utils.checkProcessesTableExisting(processesTable) == false) {
            System.out.println("\t ________________________________________________");
            System.out.println("\t||                                                ||");
            System.out.println("\t|| > THERE ARE CURRENTLY NO PROCESSES EXISTING    ||");
            System.out.println("\t||________________________________________________||");
            System.out.println();

            // Sub-menu for actions to be taken.
            // It is the same as if there are existing processes,
            // except that it doesn't have the "Compute" option
            do {
                System.out.println("( 1 ) Create a process");
                System.out.println("( 2 ) Generate a random process");
                System.out.println("( 0 ) Exit");
                System.out.print("CHOICE: ");

                ch = scanner.next().charAt(0);

                if (ch == '1') {
                    Utils.processCreator(processesTable, true);
                    break;
                } else if (ch == '2') {
                    Utils.randomProcessCreator(processesTable, true);
                    break;
                } else if (ch == '0') {
                    processesTable = null; // garbage
                    menu();
                } else {
                    System.out.println("\t ________________________________________________");
                    System.out.println("\t||                                                ||");
                    System.out.println("\t|| > INVALID INPUT                                ||");
                    System.out.println("\t||________________________________________________||");
                }
            } while (ch != '0');

            priorityScheduling(processesTable);
        }

        // Check first if a process exists; if a process exists:
        else {
            // display the processes table
            System.out.println();
            Utils.displayProcessesTable(processesTable, true);

            // Sub-menu for actions to be taken.
            // It is the same as if there are no existing processes,
            // except that it has the "Compute" option
            do {
                System.out.println("( 1 ) Compute");
                System.out.println("( 2 ) Create a process");
                System.out.println("( 3 ) Generate a random process");
                System.out.println("( 0 ) Exit");
                System.out.print("CHOICE: ");

                ch = scanner.next().charAt(0);

                if (ch == '1') {
                    Algorithms.priorityScheduling(processesTable);
                    break;
                } else if (ch == '2') {
                    Utils.processCreator(processesTable, true);
                    break;
                } else if (ch == '3') {
                    Utils.randomProcessCreator(processesTable, true);
                    break;
                } else if (ch == '0') {
                    processesTable = null; // garbage
                    menu();
                } else {
                    System.out.println("\t ________________________________________________");
                    System.out.println("\t||                                                ||");
                    System.out.println("\t|| > INVALID INPUT                                ||");
                    System.out.println("\t||________________________________________________||");
                }
            } while (ch != '0');

            priorityScheduling(processesTable);

        }
    }

    private static void multilevelQueue(ProcessesTable processesTable) {

    }

    public static void main(String[] args) throws Exception {
        scanner = new Scanner(System.in);
        menu();
    }
}
