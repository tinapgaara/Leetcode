package facebook.sort;

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
    public int findKthLargest_quickselect(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;
        while(low <= high) {
            int index = partition(nums, low, high);
            System.out.println(index);
            if (index + 1 == k) {
                return nums[index];
            }
            else if (index + 1 < k) {
                low = index + 1;
            }
            else {
                high = index - 1;
            }
        }
        return -1;
    }
    public int partition(int[] nums, int low, int high) {
        // choose a pivot
        int pivot = nums[high];
        // partition to > pivot, < pivot
        int largerIndex = low;
        for (int i = low; i < high; i ++) {
            if (nums[i] > pivot) {
                int tmp = nums[i];
                nums[i] = nums[largerIndex];
                nums[largerIndex] = tmp;
                largerIndex ++;
            }
            else {
                // nothing to do
            }
        }
        int tmp = nums[high];
        nums[high] = nums[largerIndex];
        nums[largerIndex] = tmp;
        return largerIndex;
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
