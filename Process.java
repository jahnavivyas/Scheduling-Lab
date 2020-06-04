public class Process implements Comparable<Process>{

    public int A;
    public int B;
    public int C;
    public int C_original;
    public int IO;
    String status = "Unstarted";
    public int waitTime = 0;
    public int burstIO = -1;
    public int burstCPU = -1;
    public int finishTime;
    public int IOTime;

    public Process(int A, int B, int C, int IO) {
        this.A = A;
        this.B = B;
        this.C = C;
        C_original = C;
        this.IO = IO;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    public int getC() {
        return C;
    }

    public int getC_original() { return C_original; }

    public int getIO() {
        return IO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void updateWaitTime() {
        waitTime++;
    }

    public void updateBurstCPU() {
        burstCPU--;
    }

    public int getBurstCPU() {
        return burstCPU;
    }

    public int getBurstIO() {
        return burstIO;
    }

    public void updateBurstIO() {
        burstIO--;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }


    public void setBurstIO(int burstIO) {
        this.burstIO = burstIO;
    }

    public void setBurstCPU(int burstCPU) {
        this.burstCPU = burstCPU;
    }

    public void updateComputeTime() {
        C--;
    }

    public void setC(int c) {
        C = c;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
          this.finishTime = finishTime;
    }

    public int getIOTime() {
        return IOTime;
    }

    public void setIOTime(int IOTime) {
        this.IOTime = IOTime;
    }

    public void updateIOTime() {
        IOTime++;
    }

    public String toString(){
        return A + ", " + B + ", " + C + ", " + IO;
    }

    public int compareTo(Process other){
        if (this.getC()<other.getC()){
            return -1;
        } else if (this.getC()==other.getC()){
            return 0;
        } else return 1;
    }
}
