package assignment;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        int n = sc.nextInt();
        int pid[] = new int[n];    // process ids
        int ar[] = new int[n];     // arrival times
        int bt[] = new int[n];     // burst times
        int ct[] = new int[n];     // completion times
        int ta[] = new int[n];     // turn around times
        int wt[] = new int[n];     // waiting times
        int rt[] = new int[n];     // response time
        int temp;
        float avgwt = 0, avgta = 0;
        System.out.print("Enter each process arrival time (ms) and CPU burst:");


        //store input into arrays
        String inputs = new String();
        inputs=sc.next();
        String [] pipes = inputs.split("\\|");  //split by pipes
        int len = pipes.length;
        String[] values = new String[n];
        for (int i = 0; i <len ; i++) {
            values = pipes[i].split(",");
            pid[i] = Integer.parseInt(values[0]);
            ar[i] = Integer.parseInt(values[1]);
            bt[i] = Integer.parseInt(values[2]);
        }


        //sorting according to arrival times
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (ar[j] > ar[j + 1]) {
                    temp = ar[j];
                    ar[j] = ar[j + 1];
                    ar[j + 1] = temp;
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;
                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;
                }
            }
        }

        // finding completion times
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ct[i] = ar[i] + bt[i];

            } else {
                if (ar[i] > ct[i - 1]) {
                    ct[i] = ar[i] + bt[i];
                } else
                    ct[i] = ct[i - 1] + bt[i];
            }
            // response time =complete time - burst time
            rt[i] = ct[i] - bt[i];

            // turnaround time= completion time- response time
            ta[i]=ct[i]-rt[i];

            // waiting time= turnaround time- burst time
            wt[i] = rt[i] - ar[i];

            // total waiting time
            avgwt += wt[i];

            // total turnaround time
            avgta += ta[i];
        }

        System.out.println("\nProcess   |   Turnaround time |   Response time   |   Waiting time");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "  \t\t\t\t\t " + ta[i] + "\t\t\t\t\t" + rt[i] + "\t\t\t\t\t"+ wt[i]);
        }
        sc.close();
        System.out.println("\nAverage turnaround time: " + (avgta / n)+"ms");     // printing average turnaround time.
        System.out.println("Average waiting time: " + (avgwt / n)+"ms");         // printing average waiting time.

    }
}


