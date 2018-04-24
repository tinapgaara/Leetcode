package multithread.evenOdd;
import java.util.*;
/**
 * Created by yingtan on 4/3/18.
 */
public class OddPrint extends Thread {
    Boolean[] isOddTurn;
    int lastDigit;
    public OddPrint(Boolean[] isOddTurn) {
        this.isOddTurn = isOddTurn;
        lastDigit = 1;
    }
    public void run() {
        while(lastDigit <= 100) {
            synchronized (isOddTurn) {
                while(! isOddTurn[0]) {
                    try {
                        isOddTurn.wait();
                    }
                    catch (Exception e) {

                    }
                }
                System.out.println("odd print:" + lastDigit);
                lastDigit = lastDigit + 2;
                isOddTurn[0] = false;
                isOddTurn.notify();
            }
        }
    }
}
