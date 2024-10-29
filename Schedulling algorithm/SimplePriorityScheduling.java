import java.util.Scanner;

public class SimplePriorityScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        // Arrays to store process information
        int[] pid = new int[n];         // Process IDs
        int[] burstTime = new int[n];   // Burst Times
        int[] priority = new int[n];    // Priorities
        int[] waitTime = new int[n];    // Waiting Times
        int[] turnTime = new int[n];    // Turnaround Times

        // Input process details
        for(int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.println("\nProcess " + (i + 1) + ":");
            System.out.print("Burst Time: ");
            burstTime[i] = sc.nextInt();
            System.out.print("Priority: ");
            priority[i] = sc.nextInt();
        }

        // Sort based on priority using bubble sort
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                if(priority[j] > priority[j + 1]) {
                    // Swap all details
                    swap(priority, j);
                    swap(burstTime, j);
                    swap(pid, j);
                }
            }
        }

        // Calculate waiting time and turnaround time
        waitTime[0] = 0;  // First process has 0 waiting time

        for(int i = 1; i < n; i++) {
            waitTime[i] = waitTime[i-1] + burstTime[i-1];
        }

        for(int i = 0; i < n; i++) {
            turnTime[i] = waitTime[i] + burstTime[i];
        }

        // Display results
        System.out.println("\nProcess\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");

        float avgWait = 0, avgTurn = 0;
        for(int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + burstTime[i] + "\t\t" +
                    priority[i] + "\t\t" + waitTime[i] + "\t\t" + turnTime[i]);
            avgWait += waitTime[i];
            avgTurn += turnTime[i];
        }

        System.out.println("\nAverage Waiting Time: " + (avgWait/n));
        System.out.println("Average Turnaround Time: " + (avgTurn/n));

        sc.close();
    }

    // Simple swap method for array elements
    private static void swap(int[] arr, int j) {
        int temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
    }
}
