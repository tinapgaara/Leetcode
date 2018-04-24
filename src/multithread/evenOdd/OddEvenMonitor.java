package multithread.evenOdd;

/**
 * Created by yingtan on 4/3/18.
 */

public class OddEvenMonitor {
    public static void main(String[] args) {
        // Have to use Boolean[] !!!!! instead of Boolean
        Boolean[] isOdd = new Boolean[1];
        isOdd[0] = true;
        Thread t1 = new OddPrint(isOdd);
        Thread t2 = new EvenPrint(isOdd);
        t2.start();
        t1.start();

    }
}
