package facebook.recursion;

/**
 * Created by yingtan on 12/21/17.
 * 494. Target Sum
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

 Find out how many ways to assign symbols to make sum of integers equal to target S.

 Example 1:
 Input: nums is [1, 1, 1, 1, 1], S is 3.
 Output: 5
 Explanation:

 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 There are 5 ways to assign symbols to make the sum of nums be target 3.
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        return recur(nums, 0, S);
    }

    public int recur(int[] nums, int index, int target) {
        if (index == nums.length) {
            if (target == 0) return 1;
            else return 0;
        }
        int sum = 0;
        sum = sum + recur(nums, index + 1, target - nums[index]);
        sum = sum + recur(nums, index + 1, target + nums[index]);
        return sum;
    }
}
