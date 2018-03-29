package array;

/**
 * Created by yingtan on 3/1/18.
 *
 * 548. Split Array with Equal Sum
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

 0 < i, i + 1 < j, j + 1 < k < n - 1
 Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
 where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
 Example:
 Input: [1,2,1,2,1,2,1]
 Output: True
 Explanation:
 i = 1, j = 3, k = 5.
 sum(0, i - 1) = sum(0, 0) = 1
 sum(i + 1, j - 1) = sum(2, 2) = 1
 sum(j + 1, k - 1) = sum(4, 4) = 1
 sum(k + 1, n - 1) = sum(6, 6) = 1

 Elements in the given array will be in range [-1,000,000, 1,000,000].

 */
import java.util.*;
public class SplitArrayWithEqualSum {
    public boolean splitArray(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            sums[i] = nums[i] + sums[i -1];
        }
        // sum is not an increasing array because can contain negative numbers.
        // if they are all positive numbers, can use binary search
        int total_sum = sums[nums.length - 1];
        for (int i = 3 ; i < sums.length; i ++) {
            // if left == right, can split into half equally
            int left_total = sums[i-1];
            Set<Integer> leftsum = new HashSet<>();
            for (int left = 1; left < i; left ++) {
                // can split left into halves equally
                if (sums[left] + sums[left-1] == left_total) {
                    leftsum.add(sums[left-1]);
                }
            }
            for (int right = i + 2; right < nums.length; right ++) {
                // can split right into halves equally
                if (sums[right -1] + sums[right] == total_sum + sums[i]) {
                    // right halve == left halve
                    if (leftsum.contains(sums[right-1] - sums[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
