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
    public int findKthLargest_queue(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i = 0 ; i < nums.length; i ++) {
            queue.offer(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }
    // Solution 2:  bubble sort
    public int findKthLargest_bubble(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        for (int i = 0 ; i < k; i ++) {
            // swap the largest element to the end
            for (int j = 1 ; j < nums.length - i; j ++) {
                if (nums[j] < nums[j - 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = tmp;
                }
            }
        }
        return nums[nums.length - k];
    }
}
