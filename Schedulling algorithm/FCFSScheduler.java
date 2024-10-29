import java.util.*;

class ProcessFCFS {
    int pid;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;
    int responseTime;

    public ProcessFCFS(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class FCFSScheduler {
    static void fcfsScheduling(List<ProcessFCFS> processes) {
        // Sort processes by arrival time
        processes.sort((p1, p2) -> {
            if (p1.arrivalTime == p2.arrivalTime)
                return p1.pid - p2.pid;  // If arrival times are equal, sort by PID
            return p1.arrivalTime - p2.arrivalTime;
        });

        int currentTime = 0;
        int[] timeline = new int[1000];  // For tracking process execution

        // Process each job in order of arrival
        for (ProcessFCFS p : processes) {
            // If there's a gap between processes, CPU will be idle
            if (currentTime < p.arrivalTime) {
                // Fill timeline with idle time (-1)
                while (currentTime < p.arrivalTime) {
                    timeline[currentTime] = -1;
                    currentTime++;
                }
            }

            // Response time is same as waiting time in FCFS
            p.responseTime = currentTime - p.arrivalTime;
            p.waitingTime = p.responseTime;

            // Execute the process
            for (int i = 0; i < p.burstTime; i++) {
                timeline[currentTime] = p.pid;
                currentTime++;
            }

            // Calculate completion and turnaround times
            p.completionTime = currentTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
        }

        printResults(processes, timeline, currentTime);
    }

    static void printResults(List<ProcessFCFS> processes, int[] timeline, int totalTime) {
        // Print the timeline
        System.out.println("\nProcess Execution Timeline:");
        System.out.println("Time\tProcess");
        System.out.println("-".repeat(20));

        for (int i = 0; i < totalTime; i++) {
            System.out.printf("%d\tP%d\n", i, timeline[i] == -1 ? 0 : timeline[i]);
        }

        // Print process details
        System.out.println("\nProcess Details:");
        System.out.println("PID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting\tResponse");
        System.out.println("-".repeat(75));

        double avgTurnaround = 0;
        double avgWaiting = 0;
        double avgResponse = 0;

        for (ProcessFCFS p : processes) {
            System.out.printf("P%d\t%d\t%d\t%d\t\t%d\t\t%d\t%d\n",
                    p.pid, p.arrivalTime, p.burstTime,
                    p.completionTime, p.turnaroundTime,
                    p.waitingTime, p.responseTime);

            avgTurnaround += p.turnaroundTime;
            avgWaiting += p.waitingTime;
            avgResponse += p.responseTime;
        }

        // Print average metrics
        int n = processes.size();
        System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTurnaround / n);
        System.out.printf("Average Waiting Time: %.2f\n", avgWaiting / n);
        System.out.printf("Average Response Time: %.2f\n", avgResponse / n);

        // Calculate and print CPU utilization
        int idleTime = 0;
        for (int i = 0; i < totalTime; i++) {
            if (timeline[i] == -1) idleTime++;
        }
        double cpuUtilization = ((double)(totalTime - idleTime) / totalTime) * 100;
        System.out.printf("CPU Utilization: %.2f%%\n", cpuUtilization);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<ProcessFCFS> processes = new ArrayList<>();

        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();

        System.out.println("\nEnter process details:");
        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess " + (i + 1) + ":");
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();

            processes.add(new ProcessFCFS(i + 1, arrivalTime, burstTime));
        }

        fcfsScheduling(processes);
        scanner.close();
    }
}