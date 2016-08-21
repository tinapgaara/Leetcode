package google.window;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 11/28/15.
 */
public class AvgInStreamWithKWindow {

    private int windowSize;
    private Queue<Double> queue;
    private Iterator<Double> iter;

    private double avg;

    public AvgInStreamWithKWindow(int k, Iterator<Double> iter) {
        windowSize = k;
        queue = new LinkedList<>();
        avg = 0;
        this.iter = iter;
    }

    public double next() {
        if (iter.hasNext()) {
            double nextElement = iter.next();

            int size = queue.size();
            if (size < windowSize) {
                avg = (avg * size + nextElement) / (size + 1);
                queue.offer(nextElement);
            }
            else {
                double popElement = queue.poll();
                avg = ((avg * size) - popElement + nextElement) / size;
                queue.offer(nextElement);
            }
        }
        return avg;
    }

    public boolean hasNext() {
        return iter.hasNext();
    }

    public static void main(String[] args) {

        ArrayList<Double> list = new ArrayList<Double>();
        for (int i = 0; i < 4; i++) {
            list.add((double) (i*2 + 1));// 1 3 5 7
        }

        AvgInStreamWithKWindow obj = new AvgInStreamWithKWindow(5, list.iterator());
        while (obj.hasNext()) {
            try {
                System.out.println("AVG = " + obj.next());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
