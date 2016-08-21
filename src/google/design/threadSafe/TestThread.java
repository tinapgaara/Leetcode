package google.design.threadSafe;

/**
 * Created by yingtan on 11/29/15.
 */
public class TestThread extends Thread {

    private Box box;

    public TestThread(Box obj) {
        box = obj;
    }

    public void run() {
        box.inc();
    }
}
