package google.heapPriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 12/22/15.
 */
public class UglyNumberII {

    public class UglyNumber {
        long lastElement;
        long val;

        public UglyNumber(long last, long value) {
            lastElement = last;
            val = value;
        }
    }
    // Solution 2: need to generate a sequence of ugly numbers
    public int nthUglyNumber(int n) {
        UglyComparator comparator = new UglyComparator();
        PriorityQueue<UglyNumber> queue = new PriorityQueue<UglyNumber>(comparator);

        if (n == 1) return 1;

        queue.offer(new UglyNumber(2,2));
        queue.offer(new UglyNumber(3,3));
        queue.offer(new UglyNumber(5,5));

        int count = 1;
        while( ! queue.isEmpty()) {
            UglyNumber num = queue.poll();
            count ++;
            if (count == n) return (int)num.val;
            else {
                long last = num.lastElement;
                if (last == 2) {
                    queue.offer(new UglyNumber(2,2*num.val));
                    queue.offer(new UglyNumber(3,3*num.val));
                    queue.offer(new UglyNumber(5,5*num.val));
                }
                else if (last == 3) {
                    queue.offer(new UglyNumber(3,3*num.val));
                    queue.offer(new UglyNumber(5,5*num.val));
                }
                else if (last == 5) {
                    queue.offer(new UglyNumber(5,5*num.val));
                }
            }
        }
        return 0;
    }

    public class UglyComparator implements Comparator<UglyNumber> {
        @Override
        public int compare(UglyNumber n1, UglyNumber n2) {
            if (n1.val > n2.val) return 1;
            else if (n1.val < n2.val) return -1;
            else return 0;
        }
    }
}
