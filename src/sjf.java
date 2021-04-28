import java.util.Scanner;

public class sjf {

	private static float sumwait, sumturn;
	private static int n, CpuTime, ArrivalTime[], BurstTime[], WaitingTime[], TurnaroundTime[];
	private static String id[];
	private static Scanner r;

	public static void shortestJobFirst() {
		r = new Scanner(System.in);
		System.out.print("Input the number process: ");
		n = r.nextInt();
		ArrivalTime = new int[n];
		BurstTime = new int[n];
		WaitingTime = new int[n];
		TurnaroundTime = new int[n];
		id = new String[n];
		read();
		sort();
		sjf2();
		print();
	}

	private static void read() {
		for (int i = 0; i < n; i++) {
			System.out.print("Input the Arrival Time of p" + (i + 1) + " : ");
			id[i] = "p" + (i + 1);
			ArrivalTime[i] = r.nextInt();
			System.out.print("Input the Burst Time of p" + (i + 1) + " : ");
			BurstTime[i] = r.nextInt();
		}
	}

	private static void sort() {
		int t;
		String p;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (ArrivalTime[i] < ArrivalTime[j]) {
					t = ArrivalTime[i]; 
					ArrivalTime[i] = ArrivalTime[j];
					ArrivalTime[j] = t;
					t = BurstTime[i];
					BurstTime[i] = BurstTime[j];
					BurstTime[j] = t;
					p = id[i];
					id[i] = id[j];
					id[j] = p;
				}

				// if (BurstTime[i] < BurstTime[j]) {
				// 	t = ArrivalTime[i]; 
				// 	ArrivalTime[i] = ArrivalTime[j];
				// 	ArrivalTime[j] = t;
				// 	t = BurstTime[i];
				// 	BurstTime[i] = BurstTime[j];
				// 	BurstTime[j] = t;
				// 	p = id[i];
				// 	id[i] = id[j];
				// 	id[j] = p;
				// }
			}
	}

	private static void sjf2() {
		for (int i = 1; i < n; i++) {

			CpuTime += BurstTime[i - 1];
			compare(i);
			WaitingTime[i] = CpuTime - ArrivalTime[i];
			sumwait += WaitingTime[i];

			if (i == 1) {
				WaitingTime[0] = 0;
				TurnaroundTime[i - 1] = WaitingTime[i - 1] + BurstTime[i - 1];
				sumturn += TurnaroundTime[i - 1];
			}
			TurnaroundTime[i] = WaitingTime[i] + BurstTime[i];
			sumturn += TurnaroundTime[i];

		}
	}

	private static void compare(int postion) {
		int t;
		String p;
		for (int i = postion; i < n; i++)
			for (int j = postion; j < n; j++) {
				if (BurstTime[i] < BurstTime[j]) {
					if (ArrivalTime[i] <= CpuTime) {
						t = ArrivalTime[i];
						ArrivalTime[i] = ArrivalTime[j];
						ArrivalTime[j] = t;
						t = BurstTime[i];
						BurstTime[i] = BurstTime[j];
						BurstTime[j] = t;
						p = id[i];
						id[i] = id[j];
						id[j] = p;
					}
				}
			}

	}

	private static void print() {

		System.out.println("Process name \t Arrival Time \t Burst Time \t Waiting Time \t Turnaround Time");
		for (int i = 0; i < n; i++) {
			System.out.println(
					"   " + id[i] + "\t\t" + ArrivalTime[i] + "\t\t" + BurstTime[i] + "\t\t" + WaitingTime[i] + "\t\t" + TurnaroundTime[i]);

		}
		System.out.println("Average Waiting time : " + sumwait / n + "\nAverage Turnaround Time : " + sumturn / n);

		//TODO: gantt chart
	}
}
