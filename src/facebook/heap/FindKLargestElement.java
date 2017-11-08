package facebook.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 5/21/17.
 *
 * 215. Kth Largest Element in an Array Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 127191
 Total Submissions: 329882
 Difficulty: Medium
 Contributor: LeetCode
 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class FindKLargestElement {
    public class QueueComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i2.intValue() - i1.intValue();
        }
    }

    public int findKthLargest_queue(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        QueueComparator comparator = new QueueComparator();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(comparator);
        for (int i = 0 ; i < nums.length; i ++) {
            queue.offer(nums[i]);
        }
        for (int i = 0 ; i < k -1 ; i ++) {
            queue.poll();// o(logn)
        }
        return queue.poll();
    }

    // Solution 2:  bubble sort
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        // o(nk) no space in place
        for (int i = 0 ; i < k; i ++) {
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
