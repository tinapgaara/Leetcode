package google.heapPriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 1/24/17.
 *
 *
 * 295. Find Median from Data Stream   Add to List QuestionEditorial Solution  My Submissions
 Total Accepted: 33451
 Total Submissions: 142601
 Difficulty: Hard
 Contributors: Admin
 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 Examples:
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


/**
 * Your MedianFinder object will be instantiated and called as such:
 * DataStreamMedian obj = new DataStreamMedian();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
public class DataStreamMedian {

    PriorityQueue<Integer> smaller; // maxHeap
    PriorityQueue<Integer> larger; // minHeap, = smaller.size() + 1, or == smaller.size(); so it contains median

    /** initialize your data structure here. */
    public DataStreamMedian() {
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
