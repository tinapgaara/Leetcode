package vmware;

/**
 * Created by yingtan on 4/18/18.
 * 295. Find Median from Data Stream
 DescriptionHintsSubmissionsDiscussSolution
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
import java.util.*;
public class FindMedianFromDataStream {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap; // maxheap.size == minheap + 1 or maxheap.size == minheap

    public FindMedianFromDataStream() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek())  {
            maxHeap.offer(num);
        }
        else {
            minHeap.offer(num);
        }
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        else if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / (2.0);
        }
        else {
            return maxHeap.peek();
        }
    }
}
