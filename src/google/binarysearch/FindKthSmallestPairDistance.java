package google.binarysearch;

import java.util.Arrays;

/**
 * Created by yingtan on 11/10/17.
 *
 * 719. Find K-th Smallest Pair Distance
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

 Example 1:
 Input:
 nums = [1,3,1]
 k = 1
 Output: 0
 Explanation:
 Here are all the pairs:
 (1,3) -> 2
 (1,1) -> 0
 (3,1) -> 2
 Then the 1st smallest distance pair is (1,1), and its distance is 0.
 Note:
 */
public class FindKthSmallestPairDistance {

    public int smallestDistancePair(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        // Minimum absolute difference
        int lowDist = 0;
        // // Maximum absolute difference
        int highDist = nums[nums.length - 1] - nums[0];
        // Do binary search for k-th absolute difference
        return smallestDist(nums, k, lowDist, highDist);
    }
    // find how many pairs whose distance <= midDist
    // Returns number of pairs with absolute difference less than or equal to mid.
    private int countSmaller(int[] nums, int midDist) {
        int count = 0;
        int j = 0;
        // o (n) instead of o(n * n)
        for (int i = 0; i < nums.length; ++i) {
            // trick here, when i increases, j stays the same
            while (j < nums.length && nums[j] - nums[i] <= midDist) ++j;
            count += j - i-1;
        }
        return count;
    }
    private int smallestDist(int[] nums, int k, int lowDist, int highDist) {
        while(lowDist < highDist) {
            int midDist = lowDist + (highDist - lowDist) / 2;
            int countSmallerDist = countSmaller(nums, midDist);
            if (countSmallerDist < k) {
                lowDist = midDist + 1;
            }
            else {
                highDist = midDist;
            }

        }
        return highDist;
    }
}
