package facebook.array;

/**
 * Created by yingtan on 2/19/18.
 *
 * 334. Increasing Triplet Subsequence
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

 Formally the function should:
 Return true if there exists i, j, k
 such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 Your algorithm should run in O(n) time complexity and O(1) space complexity.

 Examples:
 Given [1, 2, 3, 4, 5],
 return true.

 Given [5, 4, 3, 2, 1],
 return false.
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        // o(n) time, o(1) space: more pointers, one pass/two pass
        if (nums == null || nums.length == 0) return false;
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        // Consider boundary case : [1,0,0,2,0,-1,-1,3]
        // Can not use the alg to update min, and reset secondMin = unknown, probably it can has larger min before and can have increasing triplet subsequence.
        for (int i = 0 ; i < nums.length; i ++) {
            // important !!! use <= here, becasue secondMin must > min, instead of >= min.
            if (nums[i] <= min) {
                min = nums[i];
            }
            else if (nums[i] <= secondMin) { // secondMin must > min, instead of >= min
                secondMin = nums[i];
            }
            // Can have the case:
            // oldMin secondMin newMin nums[i]
            // if secondMin is not Integer.MAX_VALUE, this means there must be a increasing subsequence of size 2 :
            // [oldMin secondMin]
            // nums[i] > secondMin. this means this means there is increasing triplet.
            else if (nums[i] > secondMin) {
                return true;
            }

        }
        return false;
    }
}
