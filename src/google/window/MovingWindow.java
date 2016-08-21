package google.window;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by yingtan on 11/8/15.
 */
public class MovingWindow {

    private Iterator<Double> mStream;
    private int mWindowSize;

    private double[] mCache;
    private int mPosInCache;
    private double mCurAvg;

    public static void main(String[] args) {

        ArrayList<Double> list = new ArrayList<Double>();
        for (int i = 0; i < 4; i++) {
            list.add((double) (i*2 + 1));
        }

        MovingWindow obj = new MovingWindow(list.iterator(), 5);
        while (obj.hasNext()) {
            try {
                System.out.println("AVG = " + obj.next());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public MovingWindow(Iterator<Double> stream, int windowSize) {
        mStream = stream;
        mWindowSize = windowSize;

        mCache = new double[windowSize];
        mPosInCache = -1;
    }

    public boolean hasNext() {
        return mStream.hasNext();
    }

    public double next() throws Exception {
        double val, sum = 0;

        if (mPosInCache < 0) { // init
            int count = 0;
            for (int i = 0; i < mWindowSize; i++) {
                if (mStream.hasNext()) {
                    val = mStream.next();

                    mCache[count] = val;
                    count++;
                    sum += val;
                }
                else {
                    break;
                }
            }

            if (count > 0) {
                mCurAvg = sum / count;
                mPosInCache = 0;
            }
            else {
                throw new Exception();
            }
        }

        else {
            if (mStream.hasNext()) {
                val = mStream.next();
                mCurAvg = (mCurAvg * mWindowSize - mCache[mPosInCache] + val) / mWindowSize;
                mCache[mPosInCache] = val;

                mPosInCache++;
                if (mPosInCache >= mWindowSize) {
                    mPosInCache = 0;
                }
            }
            else {
                throw new Exception();
            }
        }

        return mCurAvg;
    }
}
