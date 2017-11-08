package google.stream;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 6/24/17.
 */
public class MovingAverageFromDataStream {

    public class MovingAverage {
        double avg;
        int maxSize;
        Queue<Integer> queue;

        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            maxSize = size;
            queue = new LinkedList<Integer>();
            avg = 0;
        }

        public double next(int val) {
            if (queue.size() + 1 <= maxSize) {
                avg = (val + avg * queue.size()) / (queue.size() + 1);
                queue.offer(val);
            }
            else {
                // out of range
                Integer removed = queue.poll();
                avg = (avg * maxSize - removed + val) / (maxSize);
                queue.offer(val);
            }
            return avg;
        }
    }
}
