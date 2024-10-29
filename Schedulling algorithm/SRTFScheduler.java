import java.util.*;

class ProcessSRTF {  // Changed class name from Process to ProcessSRTF
    int pid;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;
    int responseTime;
    boolean started;

    public ProcessSRTF(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.started = false;
    }
}

public class SRTFScheduler {
    static void srtfScheduling(List<ProcessSRTF> processes) {
        int currentTime = 0;
        int completedProcesses = 0;
        int n = processes.size();
        int[] timeline = new int[1000];

        processes.sort((p1, p2) -> p1.arrivalTime - p2.arrivalTime);

        while (completedProcesses < n) {
            ProcessSRTF shortestJob = null;
            int shortestTime = Integer.MAX_VALUE;

            for (ProcessSRTF p : processes) {
                if (p.arrivalTime <= currentTime && p.remainingTime > 0) {
                    if (p.remainingTime < shortestTime) {
                        shortestJob = p;
                        shortestTime = p.remainingTime;
                    }
                }
            }

            if (shortestJob == null) {
                currentTime++;
                timeline[currentTime] = -1;
                continue;
            }

            if (!shortestJob.started) {
                shortestJob.responseTime = currentTime - shortestJob.arrivalTime;
                shortestJob.started = true;
            }

            shortestJob.remainingTime--;
            timeline[currentTime] = shortestJob.pid;

            if (shortestJob.remainingTime == 0) {
                completedProcesses++;
                shortestJob.completionTime = currentTime + 1;
                shortestJob.turnaroundTime = shortestJob.completionTime - shortestJob.arrivalTime;
                shortestJob.waitingTime = shortestJob.turnaroundTime - shortestJob.burstTime;
            }

            currentTime++;
        }

        printResults(processes, timeline, currentTime);
    }

    static void printResults(List<ProcessSRTF> processes, int[] timeline, int totalTime) {
        System.out.println("\nProcess Execution Timeline:");
        System.out.println("Time\tProcess");
        System.out.println("-".repeat(20));

        for (int i = 0; i < totalTime; i++) {
            System.out.printf("%d\tP%d\n", i, timeline[i] == -1 ? 0 : timeline[i]);
        }

        System.out.println("\nProcess Details:");
        System.out.println("PID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting\tResponse");
        System.out.println("-".repeat(75));

        double avgTurnaround = 0;
        double avgWaiting = 0;
        double avgResponse = 0;

        for (ProcessSRTF p : processes) {
            System.out.printf("P%d\t%d\t%d\t%d\t\t%d\t\t%d\t%d\n",
                    p.pid, p.arrivalTime, p.burstTime,
                    p.completionTime, p.turnaroundTime,
                    p.waitingTime, p.responseTime);

            avgTurnaround += p.turnaroundTime;
            avgWaiting += p.waitingTime;
            avgResponse += p.responseTime;
        }

        System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTurnaround / processes.size());
        System.out.printf("Average Waiting Time: %.2f\n", avgWaiting / processes.size());
        System.out.printf("Average Response Time: %.2f\n", avgResponse / processes.size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<ProcessSRTF> processes = new ArrayList<>();

        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();

        System.out.println("\nEnter process details:");
        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess " + (i + 1) + ":");
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();

            processes.add(new ProcessSRTF(i + 1, arrivalTime, burstTime));
        }

        srtfScheduling(processes);
        scanner.close();
    }
}