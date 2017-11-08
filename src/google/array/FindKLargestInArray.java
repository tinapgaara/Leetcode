package google.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 11/1/17.
 *
 * 215. Kth Largest Element in an Array
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.

 Credits:
 Special thanks to @mithmatt for adding this problem and creating all test cases.


 */
public class FindKLargestInArray {
    public class MinHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i1.intValue() - i2.intValue();
        }
    }
    public class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i2.intValue() - i1.intValue();
        }
    }
    /* PriorityQueue in java:
     Implementation note: this implementation provides O(log(n)) time for the enqueing and dequeing methods (offer, poll, remove() and  add); linear time for the remove(Object) and contains(Object) methods; and constant time for the retrieval methods (peek,  element, and size).
     */
    // Best way: quick selection:
    public int findKthLargest(int[] nums, int k) {
        if (nums == null) return -1;
        if (k <= 0) {
            return 0;
        }
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int pivotCorrectPos = partition(nums, start, end);
        if (pivotCorrectPos + 1 == k) {
            return nums[pivotCorrectPos];
        }
        else {
            if (pivotCorrectPos + 1 < k) {
                return quickSelect(nums, pivotCorrectPos + 1, end, k);
            }
            else {
                return quickSelect(nums, start, pivotCorrectPos -1, k);
            }
        }
    }

    private int partition(int[] nums, int start, int end) {
        int i = start;
        int j = end;
        int pivot = nums[start];
        //important !!!  i < j  instead of i <= j
        while(i < j) {
            while(i < j && nums[j] <= pivot) {
                j --;
            }
            //important !!!
            nums[i] = nums[j];
            while(i < j && nums[i] >= pivot) {
                i ++;
            }
            //important !!!
            nums[j] = nums[i];
        }
        nums[i] = pivot;
        return i;
    }

    // Solution 1: minHeap
    public int findKthLargest_minHeap(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        MinHeapComparator comparator = new MinHeapComparator();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(comparator);
        // o (nlogk)
        for (int i = 0 ; i < nums.length; i ++) {
            if (i < k) {
                queue.offer(nums[i]);
            }
            else if (i >= k && nums[i] > queue.peek()) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }
    // Solution 2: maxHeap
    public int findKthLargest_maxHeap(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        MaxHeapComparator comparator = new MaxHeapComparator();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k, comparator);
        // o (nlogk)
        for (int i = 0 ; i < nums.length; i ++) {
            queue.offer(nums[i]);
        }
        for (int i = 0 ; i < k -1 ; i ++) {
            queue.poll();// o(klogk)
        }
        return queue.poll();
    }

    // Solution 3:  bubble sort
    public int findKthLargest_bubble(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        // o(nk) no space in place
        for (int i = 0 ; i < k; i ++) { // make sure ith pos has the correct number, for example, for the first pos, need to make                                         sure the elements later all are smaller than this.
            for (int j = i + 1; j < nums.length; j ++) {
                if (nums[i] < nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        return nums[k-1];
    }
}
