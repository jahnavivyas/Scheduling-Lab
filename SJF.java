import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class SJF {

    static int numTerminatedProcesses = 0;
    static ArrayList<Process> readyList = new ArrayList<Process>();

    public static void SJF(int numProcesses, Process[] array1, boolean verbose) {

        System.out.print("The original input was: ");
        for (int i = 0; i < array1.length; i++) {
            System.out.print("(" + array1[i].toString() + "), ");
        }
        System.out.println(" ");


        boolean runningProcess = false;
        boolean blockedProcess;
        int IOUsage = 0;

        int n = array1.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array1[j].getA() > array1[j + 1].getA()) {
                    // swap arr[j+1] and arr[i]
                    Process temp = array1[j];
                    array1[j] = array1[j + 1];
                    array1[j + 1] = temp;
                }

            }
        }
        int totalCompute = 0;
        for (int i = 0; i < n; i++) {
            totalCompute += array1[i].getC();
        }
        //System.out.println("total compute: " + totalCompute);
        System.out.print("The sorted input is: ");
        for (int i = 0; i < array1.length; i++) {
            System.out.print("(" + array1[i].toString() + "), ");
        }
        System.out.println(" ");

        System.out.println("The scheduling algorithm used was Shortest Job First.");

        int cycle = 0;
        while (numTerminatedProcesses < numProcesses) {

            blockedProcess = false;

            if(verbose) {
                System.out.print("Before Cycle " + cycle + ": ");
                for (int j = 0; j < array1.length; j++) {
                    System.out.print(" " + array1[j].getStatus() + " ");
                    if (array1[j].getStatus().equals("Unstarted") || (array1[j].getStatus().equals("Ready"))) {
                        System.out.print("0");
                    } else if (array1[j].getStatus().equals("Blocked")) {
                        System.out.print(array1[j].getBurstIO());
                    } else if (array1[j].getStatus().equals("Running")) {
                        System.out.print(array1[j].getBurstCPU());
                    }
                }
            }


            for (int i = 0; i < numProcesses; i++) {
                if (array1[i].getStatus().equals("Unstarted")) {
                    if (array1[i].getA() == cycle) {
                        array1[i].setStatus("Ready");
                        readyList.add(array1[i]);
                    }
                } else if (array1[i].getStatus().equals("Ready")) {
                    array1[i].updateWaitTime();
                } else if (array1[i].getStatus().equals("Blocked")) {
                    blockedProcess = true;
                    array1[i].updateBurstIO();
                    array1[i].updateIOTime();
                    if (array1[i].getBurstIO() == 0) {
                        array1[i].setStatus("Ready");
                        readyList.add(array1[i]);
                    }
                    //decrement how long it'll be blocked
                    //when it hits 0, blocked to ready
                } else if (array1[i].getStatus().equals("Running")) {
                    array1[i].updateBurstCPU();
                    array1[i].updateComputeTime();
                    if (array1[i].getC() == 0) {
                        array1[i].setStatus("Terminated");
                        numTerminatedProcesses++;
                        array1[i].setFinishTime(cycle);
                        runningProcess = false;
                    } else if (array1[i].getBurstCPU() == 0) {
                        array1[i].setStatus("Blocked");
                        array1[i].setBurstIO(Main.randomOS(array1[i].getIO()));
                        runningProcess = false;
                    }
                }
            }
            //System.out.println("\nOld Ready list");
            /**
             for (int i= 0 ; i < readyList.size(); i++){
             System.out.println(readyList.get(i).toString());
             }
             System.out.println("Temp List");

             for (int i= 0 ; i < tempReadyList.size(); i++){
             System.out.println(tempReadyList.get(i).toString());
             }
             **/

            /**
             System.out.println("Ready list");
             for (int i= 0 ; i < readyList.size(); i++){
             System.out.println(readyList.get(i).toString());
             }
             **/

            // sort the ready list based on C
            Collections.sort(readyList);

            if ((!runningProcess) && (readyList.size() > 0)) {
                Process x = readyList.get(0);
                x.setStatus("Running");
                readyList.remove(0);
                int burst = Main.randomOS(x.getB());
                if (burst > x.getC()) {
                    burst = x.getC();
                }
                x.setBurstCPU(burst);
                runningProcess = true;
                //add print stuff
            }
            if (blockedProcess) IOUsage++;
            System.out.println(" ");
            cycle++;
        }
        cycle--;
        for (int i = 0; i < n; i++) {
            System.out.println("Process " + i + ":");
            System.out.println("(A, B, C, IO): " + "(" + array1[i].getA() + ", " + array1[i].getB() + ", " + array1[i].getC_original() + ", " + array1[i].getIO() + ")");
            System.out.println("Finishing time: " + array1[i].getFinishTime());
            System.out.println("Turnaround time: " + (array1[i].getFinishTime() - array1[i].getA()));
            System.out.println("I/O time: " + array1[i].getIOTime());
            System.out.println("Waiting time: " + array1[i].getWaitTime());
        }

        System.out.println("Summary Data: ");
        System.out.println("Finishing time: " + cycle);
        System.out.println("CPU Utilization: " + ((float) totalCompute / (float) cycle));
        System.out.println("I/O Utilization: " + ((float) IOUsage / (float) cycle));
        System.out.println("Throughput: " + (((float) numProcesses / (float) cycle)*100) + " processes per hundred cycles.");

        int totalTurnaroundTime = 0;

        for (int i = 0; i < n; i++) {
            int y = (array1[i].getFinishTime() - array1[i].getA());
            totalTurnaroundTime += y;
        }
        System.out.println("Average turnaround time: " + (((float)totalTurnaroundTime / (float) numProcesses)));
        int totalWaitTime = 0;
        for (int i = 0; i < n; i++) {
            totalWaitTime += array1[i].getWaitTime();
        }
        System.out.println("Average waiting time: " + (( (float) totalWaitTime / (float) numProcesses)));
    }
}

        /*

        int UDRI = randomOS(array11[i].getB());
                    //If the value returned by randomOS(B), is larger than the total CPU time remaining, set the next CPU burst to the remaining time
                    if (UDRI > array1[i].getC()  ){
                       UDRI =  array1[i].getC();
                        //set next CPU burst to remaining time?
         */



