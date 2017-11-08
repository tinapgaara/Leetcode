package google.heapPriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 1/24/17.
 *
 * 480. Sliding Window Median   Add to List QuestionEditorial Solution  My Submissions
 Total Accepted: 2065
 Total Submissions: 6533
 Difficulty: Hard
 Contributors: YutingLiu
 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 Examples:
 [2,3,4] , the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Median
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
 Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 */
public class SlideWindowMedian {

    PriorityQueue<Integer> smaller = new PriorityQueue<Integer>(
            new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i2.compareTo(i1);
                }
            }
    ); // using maxHeap

    PriorityQueue<Integer> larger = new PriorityQueue<Integer>(); // using minHeap, median locates at larger

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null) return null;
        double[] res = new double[nums.length - k + 1];
        for (int i = 0 ; i < nums.length; i ++) {
            int num = nums[i];

            if (i >= k) {
                int removedIndex = i - k;
                res[removedIndex] = getMedian();
                remove(nums[removedIndex]);
            }

            add(num);
        }

        int lastRemovedIndex = nums.length - k;
        res[lastRemovedIndex] = getMedian();

        return res;
    }

    public void add(int num) {
        if (num < getMedian()) {
            //add to smaller
            smaller.add(num);
        }
        else {
            larger.add(num);
        }
        if (larger.size() - smaller.size() > 1) {
            smaller.add(larger.poll());
        }
        else if (smaller.size() > larger.size()) {
            larger.add(smaller.poll());
        }

    }

    public void remove(int num) {
        if (num >= getMedian()) {
            larger.remove(num);
        }
        else {
            smaller.remove(num);
        }

        if (larger.size() - smaller.size() > 1) {
            smaller.add(larger.poll());
        }
        else if (smaller.size() > larger.size()) {
            larger.add(smaller.poll());
        }
    }

    public double getMedian() {
        if (larger.isEmpty() && smaller.isEmpty()) return 0;

        if (larger.size() == smaller.size()) {
            return ((double)larger.peek() + (double)smaller.peek())/(2.0);
        }
        else
            return (double)larger.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        SlideWindowMedian ob = new SlideWindowMedian();
        ob.medianSlidingWindow(nums, 2);
    }
}
