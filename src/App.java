import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        char choice;

        // -------
        // Menu
        // -------
        do {
            System.out.println("CHOOSE THE SCHEDULING ALGORITHM\n");
            System.out.println("( 1 ) First-Come First-Served (FCFS) non-preemptive\n");
            System.out.println("( 2 ) Shortest Job First (SJF) non-preemptive\n");
            System.out.println("( 3 ) Shortest Remaining Time (SRT) preemptive\n");
            System.out.println("( 4 ) Round-Robin (RR) preemptive\n");
            System.out.println("( 5 ) Priority - non-preemptive\n");
            System.out.println("( 6 ) Priority - preemptive\n");
            System.out.println("( 0 ) EXIT\n");
            System.out.println("CHOICE: ");

            choice = scanner.next().charAt(0);

        } while (choice != '0');
    }
}
