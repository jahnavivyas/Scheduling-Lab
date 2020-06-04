import java.util.Scanner;
import java.io.*;


public class Main {

    public static int numProcesses;
    public static Scanner randomScanner;
    public static boolean verbose = false;



    public static int randomOS(int U){
        int x = Integer.parseInt(randomScanner.next());
        int y = 1 + (x % U);
        return y;
    }

    public static void main (String[] args)  {

        if(args.length==3 && args[2].equals("--verbose")) verbose = true;

        File x = new File(args[0]);

        try{
            randomScanner = new Scanner(new File("random-numbers.txt"));
            Scanner scan = new Scanner(x);
            numProcesses = Integer.parseInt(scan.next());
            Process [] processes =  new Process[numProcesses];
            for (int i =0; i < numProcesses; i++){
                processes[i] = new Process(Integer.parseInt(scan.next()), Integer.parseInt(scan.next()), Integer.parseInt(scan.next()), Integer.parseInt(scan.next()));

            }
            scan.close();
            if(args[1].equals("FCFS")) {
                FCFS.FCFS(numProcesses, processes, verbose);
            }
            else if (args[1].equals("RR")){
                RR.RR(numProcesses, processes, verbose);
            }
            else if (args[1].equals("SJF")){
                SJF.SJF(numProcesses, processes, verbose);
            }
            else if (args[1].equals("Uniprogrammed")) {
                Uniprogrammed.Uniprogrammed(numProcesses, processes, verbose);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        randomScanner.close();

    }


}



