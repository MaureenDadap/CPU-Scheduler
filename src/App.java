import java.util.Scanner;

public class App {
    final static String ANSI_MOVE_UP = "\033[%dA";
    final static String ANSI_ERASE_LINE = "\033[2K";

    /**
     * Main menu method. separated from main method for recursive calls
     * 
     * @param (choice)  initialized in the main method, used to select an item in
     *                  the menu; also used for yes/no prompts
     * @param (scanner) initialized in the main method, used for input
     */
    private static void menu(char choice, Scanner scanner) {
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

                firstComeFirstServe();

            } else if (choice == '2') {
                System.out.println();
                System.out.println("SHORTEST JOB FIRST ALGORITHM");
                System.out.println("--------------------------------");

                shortestJobFirst();

            } else if (choice == '3') {
                System.out.println();
                System.out.println("SHORTEST REMAINING TIME ALGORITHM");
                System.out.println("--------------------------------");

                shortestRemainingTime();

            } else if (choice == '4') {
                System.out.println();
                System.out.println("PRIORITY SCHEDULING ALGORITHM");
                System.out.println("--------------------------------");

                priorityScheduling();

            } else if (choice == '5') {
                System.out.println();
                System.out.println("ROUND-ROBIN ALGORITHM");
                System.out.println("--------------------------------");

                roundRobin();

            } else if (choice == '6') {
                System.out.println();
                System.out.println("MULTI-LEVEL QUEUE ALGORITHM");
                System.out.println("--------------------------------");

                multilevelQueue();

            } else if (choice == '0') {
                System.exit(0);
            } else {
                System.out.println("\t ________________________________________________");
                System.out.println("\t||                                                ||");
                System.out.println("\t|| > INVALID INPUT                                ||");
                System.out.println("\t||________________________________________________||");
            }
        } while (choice != '0');
    }

    private static void firstComeFirstServe() {
        
    }

    private static void shortestJobFirst() {
        
    }

    private static void shortestRemainingTime() {
        
    }

    private static void priorityScheduling() {
        
    }

    private static void roundRobin() {
        
    }

    private static void multilevelQueue() {
        
    }

    

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        char choice = '0';

        menu(choice, scanner);
    }
}
