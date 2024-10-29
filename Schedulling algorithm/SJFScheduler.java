import java.util.*;

class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;

    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class SJFScheduler {
    static void sjfScheduling(List<Process> processes) {
        int currentTime = 0;
        int completedProcesses = 0;
        int n = processes.size();
        List<Process> completed = new ArrayList<>();
        List<Process> readyQueue = new ArrayList<>();
        boolean isCPUIdle = false;

        // Sort initially by arrival time
        processes.sort((p1, p2) -> p1.arrivalTime - p2.arrivalTime);

        while (completedProcesses != n) {
            // Add processes that have arrived to ready queue
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime && !completed.contains(p) && !readyQueue.contains(p)) {
                    readyQueue.add(p);
                }
            }

            // Sort ready queue by burst time (shortest job first)
            readyQueue.sort((p1, p2) -> p1.burstTime - p2.burstTime);

            if (readyQueue.isEmpty()) {
                currentTime++;
                isCPUIdle = true;
                continue;
            }

            Process currentProcess = readyQueue.remove(0);

            // Execute process
            if (isCPUIdle) {
                currentTime = Math.max(currentTime, currentProcess.arrivalTime);
                isCPUIdle = false;
            }

            currentTime += currentProcess.burstTime;
            currentProcess.completionTime = currentTime;
            currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
            currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;

            completed.add(currentProcess);
            completedProcesses++;
        }

        printResults(completed);
    }

    static void printResults(List<Process> processes) {
        System.out.println("\nProcess Execution Order:");
        System.out.println("PID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        System.out.println("-".repeat(65));

        double totalTurnaround = 0;
        double totalWaiting = 0;

        for (Process p : processes) {
            System.out.printf("P%d\t%d\t%d\t%d\t\t%d\t\t%d\n",
                    p.pid, p.arrivalTime, p.burstTime,
                    p.completionTime, p.turnaroundTime, p.waitingTime);
            totalTurnaround += p.turnaroundTime;
            totalWaiting += p.waitingTime;
        }

        System.out.printf("\nAverage Turnaround Time: %.2f\n", totalTurnaround / processes.size());
        System.out.printf("Average Waiting Time: %.2f\n", totalWaiting / processes.size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();

        System.out.println("\nEnter process details:");
        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess " + (i + 1) + ":");
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();

            processes.add(new Process(i + 1, arrivalTime, burstTime));
        }

        sjfScheduling(processes);
        scanner.close();
    }
}