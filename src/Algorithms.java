import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Class that contains all algorithms
 */
public class Algorithms {
    /**
     * First Come First Serve Algorithm
     */
    public static void firstComeFirstServe(ProcessesTable processesTable) {
        List<Map.Entry<String, ProcessesTable.TimeValues>> list = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>(
                processesTable.processesMap.entrySet());

        // #1 - sort the processes according to arrival time
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

        /**
         * CALCULATE THE TIME VALUES
         */

        // STARTING TIME
        list.get(0).getValue().setStartingTime(list.get(0).getValue().getArrival()); // starting time for first process
                                                                                     // will be 0
        for (int i = 1; i < list.size(); i++) {
            list.get(i).getValue().setStartingTime(
                    list.get(i - 1).getValue().getBurst() + list.get(i - 1).getValue().getStartingTime());
        }

        // COMPLETION TIME
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getValue()
                    .setCompletionTime(list.get(i).getValue().getStartingTime() + list.get(i).getValue().getBurst());
        }

        // WAITING TIME
        for (int i = 0; i < list.size(); i++) {
            int wt = list.get(i).getValue().getStartingTime() - list.get(i).getValue().getArrival();
            if (wt < 0) {
                wt = 0;
            }
            list.get(i).getValue().setWaitingTime(wt);
        }

        // TURNAROUND TIME
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getValue()
                    .setTurnAroundTime(list.get(i).getValue().getBurst() + list.get(i).getValue().getWaitingTime());
        }

        // CREATE THE TABLES
        Utils.createTableSummary(list, false);
        Utils.createGanttChart(list);

        // PRINT AVERAGES
        System.out.println("# Average Waiting Time: " + Utils.findAverageWT(list));
        System.out.println("# Average Turnaround Time: " + Utils.findAverageTaT(list));

        System.out.println("\n/////////////////////////////////////////////");
    }

    /**
     * Shortest Job First Version 2 Algorithm.
     */
    public static void shortestJobFirstVer2(ProcessesTable processesTable) {
        List<Map.Entry<String, ProcessesTable.TimeValues>> list = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>(
                processesTable.processesMap.entrySet());

        List<Boolean> isCompleted = new ArrayList<Boolean>();
        int completed = 0;
        int currentTime = 0;
        ProcessesTable.TimeValues values;

        for (int i = 0; i < list.size(); i++) {
            isCompleted.add(false);
        }

        while (completed != list.size()) {

            int index = -1;
            int max = 1000;

            for (int i = 0; i < list.size(); i++) {
                values = list.get(i).getValue();

                if (values.getArrival() <= currentTime && isCompleted.get(i) == false) {
                    if (values.getBurst() < max) {
                        max = values.getBurst();
                        index = i;
                    }
                    if (values.getBurst() == max) {
                        if (values.getArrival() < list.get(index).getValue().getArrival()) {
                            max = values.getBurst();
                            index = i;
                        }
                    }
                }
            }

            if (index != -1) {
                values = list.get(index).getValue();

                values.setStartingTime(currentTime);
                values.setCompletionTime(values.getStartingTime() + values.getBurst());
                values.setTurnAroundTime(values.getCompletionTime() - values.getArrival());
                values.setWaitingTime(values.getTurnAroundTime() - values.getBurst());

                isCompleted.set(index, true);
                completed++;
                currentTime = values.getCompletionTime();

            } else {
                currentTime++;
            }
        }

        Collections.sort(list, new Comparator<Map.Entry<String, ProcessesTable.TimeValues>>() {
            public int compare(Map.Entry<String, ProcessesTable.TimeValues> o1,
                    Map.Entry<String, ProcessesTable.TimeValues> o2) {

                int temp;
                int a = o1.getValue().getStartingTime();
                int b = o2.getValue().getStartingTime();

                if (a > b)
                    temp = +1;
                else if (a < b)
                    temp = -1;
                else
                    temp = 0;

                return temp;
            }
        });
    }

    /**
     * Shortest Remaining Time First Algorithm
     */
    public static void shortestRemainingTime(ProcessesTable processesTable) {
        List<Map.Entry<String, ProcessesTable.TimeValues>> list = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>(
                processesTable.processesMap.entrySet());

        List<Map.Entry<String, ProcessesTable.TimeValues>> newList = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>();
        List<Boolean> isCompleted = new ArrayList<Boolean>();
        List<Integer> remainingTime = new ArrayList<Integer>();
        int completed = 0;
        int currentTime = 0;
        ProcessesTable.TimeValues values;

        for (int i = 0; i < list.size(); i++) {
            isCompleted.add(false);
            remainingTime.add(list.get(i).getValue().getBurst());
        }

        while (completed != list.size()) {

            int index = -1;
            int min = 10000000;

            for (int i = 0; i < list.size(); i++) {
                values = list.get(i).getValue();

                if (values.getArrival() <= currentTime && isCompleted.get(i) == false) {
                    if (remainingTime.get(i) < min) {
                        min = remainingTime.get(i);
                        index = i;
                    }
                    if (remainingTime.get(i) == min) {
                        if (values.getArrival() < list.get(index).getValue().getArrival()) {
                            min = remainingTime.get(i);
                            index = i;
                        }
                    }
                }
            }

            if (index != -1) {
                values = list.get(index).getValue();

                if (remainingTime.get(index) == values.getBurst()) {
                    values.setStartingTime(currentTime);
                }

                remainingTime.set(index, remainingTime.get(index) - 1);
                currentTime++;

                if (remainingTime.get(index) == 0) {
                    values.setCompletionTime(currentTime);
                    values.setTurnAroundTime(values.getCompletionTime() - values.getArrival());
                    values.setWaitingTime(values.getTurnAroundTime() - values.getBurst());

                    isCompleted.set(index, true);
                    completed++;
                }

            } else {
                currentTime++;
            }
        }

        Collections.sort(list, new Comparator<Map.Entry<String, ProcessesTable.TimeValues>>() {
            public int compare(Map.Entry<String, ProcessesTable.TimeValues> o1,
                    Map.Entry<String, ProcessesTable.TimeValues> o2) {

                int temp;
                int a = o1.getValue().getCompletionTime();
                int b = o2.getValue().getCompletionTime();

                if (a > b)
                    temp = +1;
                else if (a < b)
                    temp = -1;
                else
                    temp = 0;

                return temp;
            }
        });
    }

    /**
     * (NON-PREEMPTIVE) Priority Algorithm.
     */
    public static void priorityScheduling(ProcessesTable processesTable) {
        List<Map.Entry<String, ProcessesTable.TimeValues>> list = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>(
                processesTable.processesMap.entrySet());

        List<Boolean> isCompleted = new ArrayList<Boolean>();
        int completed = 0;
        int currentTime = 0;
        ProcessesTable.TimeValues values;

        for (int i = 0; i < list.size(); i++) {
            isCompleted.add(false);
        }

        while (completed != list.size()) {

            int index = -1;
            int max = 1000;

            for (int i = 0; i < list.size(); i++) {
                values = list.get(i).getValue();

                if (values.getArrival() <= currentTime && isCompleted.get(i) == false) {
                    if (values.getPriority() < max) {
                        max = values.getPriority();
                        index = i;
                    }
                    if (values.getPriority() == max) {
                        if (values.getArrival() < list.get(index).getValue().getArrival()) {
                            max = values.getPriority();
                            index = i;
                        }
                    }
                }
            }

            if (index != -1) {
                values = list.get(index).getValue();

                values.setStartingTime(currentTime);
                values.setCompletionTime(values.getStartingTime() + values.getBurst());
                values.setTurnAroundTime(values.getCompletionTime() - values.getArrival());
                values.setWaitingTime(values.getTurnAroundTime() - values.getBurst());

                isCompleted.set(index, true);
                completed++;
                currentTime = values.getCompletionTime();

            } else {
                currentTime++;
            }
        }

        Collections.sort(list, new Comparator<Map.Entry<String, ProcessesTable.TimeValues>>() {
            public int compare(Map.Entry<String, ProcessesTable.TimeValues> o1,
                    Map.Entry<String, ProcessesTable.TimeValues> o2) {

                int temp;
                int a = o1.getValue().getStartingTime();
                int b = o2.getValue().getStartingTime();

                if (a > b)
                    temp = +1;
                else if (a < b)
                    temp = -1;
                else
                    temp = 0;

                return temp;
            }
        });
    }

    /**
     * (PREEMPTIVE) Priority Algorithm.
     */
    public static void prioritySchedulingPreemptive(ProcessesTable processesTable) {
        List<Map.Entry<String, ProcessesTable.TimeValues>> list = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>(
                processesTable.processesMap.entrySet());

        List<Boolean> isCompleted = new ArrayList<Boolean>();
        int completed = 0;
        int currentTime = 0;
        ProcessesTable.TimeValues values;

        for (int i = 0; i < list.size(); i++) {
            isCompleted.add(false);
        }

        while (completed != list.size()) {

            int index = -1;
            int max = 1000;

            for (int i = 0; i < list.size(); i++) {
                values = list.get(i).getValue();

                if (values.getArrival() <= currentTime && isCompleted.get(i) == false) {
                    if (values.getPriority() < max) {
                        max = values.getPriority();
                        index = i;
                    }
                    if (values.getPriority() == max) {
                        if (values.getArrival() < list.get(index).getValue().getArrival()) {
                            max = values.getPriority();
                            index = i;
                        }
                    }
                }
            }

            if (index != -1) {
                values = list.get(index).getValue();

                values.setStartingTime(currentTime);
                values.setCompletionTime(values.getStartingTime() + values.getBurst());
                values.setTurnAroundTime(values.getCompletionTime() - values.getArrival());
                values.setWaitingTime(values.getTurnAroundTime() - values.getBurst());

                isCompleted.set(index, true);
                completed++;
                currentTime = values.getCompletionTime();

            } else {
                currentTime++;
            }
        }

        Collections.sort(list, new Comparator<Map.Entry<String, ProcessesTable.TimeValues>>() {
            public int compare(Map.Entry<String, ProcessesTable.TimeValues> o1,
                    Map.Entry<String, ProcessesTable.TimeValues> o2) {

                int temp;
                int a = o1.getValue().getStartingTime();
                int b = o2.getValue().getStartingTime();

                if (a > b)
                    temp = +1;
                else if (a < b)
                    temp = -1;
                else
                    temp = 0;

                return temp;
            }
        });
    }

    /**
     * Multi Level Queue Algorithm. FIXED PRIO
     */
    public static void multilevelQueue(ProcessesTable processesTable) {
        List<Map.Entry<String, ProcessesTable.TimeValues>> list = new ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>(
                processesTable.processesMap.entrySet());

        ProcessesTable q1 = new ProcessesTable();
        ProcessesTable q2 = new ProcessesTable();

        List<Boolean> lvl1isCompleted = new ArrayList<Boolean>();
        List<Boolean> lvl2isCompleted = new ArrayList<Boolean>();

        int completed1 = 0;
        int completed2 = 0;

        // for (int i = 0; i < list.size(); i++) {
        // if (list.get(i).getValue().getLevel() == 1)
        // lvl1isCompleted.add(false);
        // else
        // lvl2isCompleted.add(false);
        // }

        // Collections.sort(list, new Comparator<Map.Entry<String,
        // ProcessesTable.TimeValues>>() {
        // public int compare(Map.Entry<String, ProcessesTable.TimeValues> o1,
        // Map.Entry<String, ProcessesTable.TimeValues> o2) {

        // int temp;
        // int a = o1.getValue().getLevel();
        // int b = o2.getValue().getLevel();

        // if (a > b)
        // temp = +1;
        // else if (a < b)
        // temp = -1;
        // else
        // temp = 0;

        // return temp;
        // }
        // });

        // List<Map.Entry<String, ProcessesTable.TimeValues>> q1 = new
        // ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>();
        // List<Map.Entry<String, ProcessesTable.TimeValues>> q2 = new
        // ArrayList<Map.Entry<String, ProcessesTable.TimeValues>>();
        // List<Boolean> isCompleted = new ArrayList<Boolean>();

        // for (int i = 0; i < list.size(); i++) {
        // ProcessesTable.TimeValues values = list.get(i).getValue();
        // ProcessesTable.TimeValues values2;
        // int arrival = values.getArrival();
        // int burst = values.getBurst();
        // int priority = values.getPriority();
        // int level = values.getLevel();

        // if (list.get(i).getValue().getLevel() == 1) {
        // values2 = q1.new TimeValues(arrival, burst, 0, 0, priority, 0, 0, level);
        // q1.processesMap.put(list.get(i).getKey(), values2);
        // } else {
        // values2 = q2.new TimeValues(arrival, burst, 0, 0, priority, 0, 0, level);
        // q2.processesMap.put(list.get(i).getKey(), values2);
        // }
        // }

        // // queue level 1 uses fcfs
        // firstComeFirstServe(q1);
        // // queue level 2 uses priority
        // priorityScheduling(q2);

        // level 1 queue uses fcfs
        while (completed1 != lvl1isCompleted.size()) {
        }

        Collections.sort(list, new Comparator<Map.Entry<String, ProcessesTable.TimeValues>>() {
            public int compare(Map.Entry<String, ProcessesTable.TimeValues> o1,
                    Map.Entry<String, ProcessesTable.TimeValues> o2) {

                int temp;
                int a = o1.getValue().getCompletionTime();
                int b = o2.getValue().getCompletionTime();

                if (a > b)
                    temp = +1;
                else if (a < b)
                    temp = -1;
                else
                    temp = 0;

                return temp;
            }
        });
    }

}
