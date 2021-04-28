import java.util.Scanner;

public class RoundRobin {
    public static void roundRobin() {
        Scanner scanner = new Scanner(System.in);
        int num, qslice, total;
        // 10 = the max number of processes
        int btime[] = new int[10]; // burst time
        int rtime[] = new int[10]; // remaining time

        System.out.print("enter the number of processes (max 10): ");
        num = scanner.nextInt();
        System.out.print("enter burst time: ");
        for (int i = 0; i < num; i++) {
            System.out.print("\nP[" + (i + 1) + "]: ");
            btime[i] = scanner.nextInt();
            rtime[i] = btime[i];
        }
        System.out.print("\n\nEnter quantum slice: ");
        qslice = scanner.nextInt();
        int rp = num;
        int i = 0;
        int time = 0;
        // eto ung G-chart
        System.out.print("\nG-Chart\n\n");
        System.out.print("0");
        while (rp != 0) {
            if (rtime[i] > qslice) {
                rtime[i] -= qslice;
                System.out.print(" | P[" + (i + 1) + "] | ");
                time += qslice;
                System.out.print(time);
            } else if (rtime[i] <= qslice && rtime[i] > 0) // pag time remaining ng process ay less than na sa qslice
            {
                time += rtime[i];
                rtime[i] = rtime[i] - rtime[i];
                System.out.print(" | P[" + (i + 1) + "] | ");
                rp--;
                System.out.print(time);
            }
            i++;
            if (i == num)
                i = 0;
        }
    }
}