package google.stream;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 11/7/15.
 *
 * Examples:
 [2,3,4] , the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.
 For example:

 addNum(1)
 addNum(2)
 findMedian() -> 1.5
 addNum(3)
 findMedian() -> 2
 */
public class FindStreamMedian {
    PriorityQueue<Integer> smaller; // maxHeap
    PriorityQueue<Integer> larger; // minHeap, = smaller.size() + 1, or == smaller.size(); so it contains median

    /** initialize your data structure here. */
    public FindStreamMedian() {
        smaller = new PriorityQueue<Integer>(
                new Comparator<Integer>() {
                    public int compare(Integer i1, Integer i2) {
                        return i2.compareTo(i1);
                    }
                }
        );
        larger = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {
        if (num >= findMedian()) {
            larger.add(num);
        }
        else
            smaller.add(num);

        if (larger.size() - smaller.size() > 1) {
            smaller.add(larger.poll());
        }
        if (smaller.size() > larger.size()) {
            larger.add(smaller.poll());
        }

    }

    public double findMedian() {
        if (smaller.isEmpty() && larger.isEmpty()) return 0;
        if (smaller.size() == larger.size()) {
            return ((double)smaller.peek() + (double)larger.peek()) / (2.0);
        }
        else { //larger.size() >= smaller.size()
            return (double)larger.peek();
        }
    }
}
