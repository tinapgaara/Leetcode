package array;

/**
 * Created by yingtan on 3/1/18.
 *
 * 665. Non-decreasing Array
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

 We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

 Example 1:
 Input: [4,2,3]
 Output: True
 Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 Example 2:
 Input: [4,2,1]
 Output: False
 Explanation: You can't get a non-decreasing array by modify at most one element.
 */
public class NonDecreasingArray {
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        // only decrease once and the decreased value is larger than previous smallest value
        boolean hasDecrease = false;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] < nums[i-1]) {
                if (! hasDecrease) {
                    // two way, increase nums[i] or decrease nums[i-1]
                    if (i >= 2 && nums[i] < nums[i-2]) {
                        // nums[i-2] > nums[i], then, 1) we can not decrease nums[i-1]. 2) check if can increase nums[i]
                        // 1) case: [3,4,2,3],  then, need to check if 2 < 3, if so, can't decrease 4
                        //     return false; wrong [2,3,3,2,4]
                        // 2) Another way: check if can increase nums[i]: need to look at it in future, so we set:
                        nums[i] = nums[i-1];
                        hasDecrease = true;
                    }
                    else { // nums[i-2] < nums[i]: we can decrease nums[i-1], so we go to the next step.
                        hasDecrease = true;
                    }
                }
                else { // can't decrease twice
                    return false;
                }
            }
        }
        return true;
    }
}
