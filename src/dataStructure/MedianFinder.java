package dataStructure;

import java.util.*;

/**
 * Created by yingtan on 10/21/15.
 */
/*
* Solution 1: use two priority queue, one is min heap , one is max heap
* Solution 2: use inseration sort
* Solution 3: use balanced search tree, or binary sort tree(treeset)
*
*
* */
class MedianFinder {

    PriorityQueue<Integer> lowHalf;
    PriorityQueue<Integer> highHalf;

    public MedianFinder() {
        MaxHeapComparator comparator = new MaxHeapComparator();
        lowHalf = new PriorityQueue<Integer>(comparator); // max queue
        highHalf = new PriorityQueue<Integer>(); // min queue
    }
    // Adds a number into the data structure.
    public void addNum(int num) {
        if ((highHalf.isEmpty()) || (num > highHalf.peek())) {
            highHalf.offer(num);
        } else {
            lowHalf.offer(num);
        }

        // keep balance:
        if (highHalf.size() - lowHalf.size() > 1) {
            lowHalf.offer(highHalf.poll());
        } else if (lowHalf.size() - highHalf.size() > 1) {
            highHalf.offer(lowHalf.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if ((lowHalf.isEmpty()) || (highHalf.size() > lowHalf.size()) ) {
            return highHalf.peek();
        } else if ((highHalf.isEmpty()) || (lowHalf.size() > highHalf.size())) {
            return lowHalf.peek();
        } else {
            return ((double)(lowHalf.peek() + highHalf.peek()))/2 ;
        }
    }

    public class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            if (i1 > i2) return -1;
            else if (i1 < i2) return 1;
            else return 0;
        }
    }



    public static void main(String[] args) {

        MedianFinder finder = new MedianFinder();
        finder.addNum(1);;
        System.out.println(finder.findMedian());
        finder.addNum(-2);
        System.out.println(finder.findMedian());
        finder.addNum(-3);
        System.out.println(finder.findMedian());

    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();
