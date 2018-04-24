package multithread.evenOdd;

import java.util.Queue;

/**
 * Created by yingtan on 4/3/18.
 */
public class EvenPrint extends Thread {
    Boolean[] isOddTurn;
    int lastDigit;
    public EvenPrint(Boolean[] isOddTurn) {
        this.isOddTurn = isOddTurn;
        lastDigit = 2;
    }
    public void run() {
        while(lastDigit <= 100) {
            synchronized (isOddTurn) {
                while(isOddTurn[0]) {
                    try {
                        isOddTurn.wait();
                    }
                    catch (Exception e) {

                    }
                }
                System.out.println("even print:" + lastDigit);
                lastDigit = lastDigit + 2;
                isOddTurn[0] = true;
                isOddTurn.notify();
            }
        }
    }
}
