package google.design.threadSafe;

/**
 * Created by yingtan on 11/29/15.
 */
public class Box {

    private int mVal = 0;
    public synchronized void inc() {
        mVal ++;
    }

    public synchronized int get() {
        return mVal;
    }



}
