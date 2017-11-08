package google.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingtan on 1/22/17.
 *
 *
 * 377. Combination Sum IV   Add to List QuestionEditorial Solution  My Submissions
 Total Accepted: 25295
 Total Submissions: 61022
 Difficulty: Medium
 Contributors: Admin
 Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

 Example:

 nums = [1, 2, 3]
 target = 4

 The possible combination ways are:
 (1, 1, 1, 1)
 (1, 1, 2)
 (1, 2, 1)
 (1, 3)
 (2, 1, 1)
 (2, 2)
 (3, 1)

 Note that different sequences are counted as different combinations.

 Therefore the output is 7.

 */
public class CombinationSumIV {

    public int combinationSum4_dp(int[] nums, int target) {
        if ( (nums == null) || (nums.length == 0) ) return 0;

        // dp[i]: use nums[] , how many ways to construct the number : i
        int[] dp = new int[target + 1];

        dp[0] = 1 ;
        for (int i = 1 ; i < target + 1 ; i ++) {
            for (int j = 0 ; j < nums.length ; j ++) {
                int num = nums[j];
                if (num <= i) {
                    dp[i] = dp[i - num] + dp[i];
                }
            }

        }
        return dp[target];

    }

    // what if it contains negative numbers
    /*
    * Follow up:
        What if negative numbers are allowed in the given array?
        How does it change the problem?

        . Think about nums = [-1, 1] and target = 1.
        We can have all sequences of arbitrary length
        that follow the patterns -1, 1, -1, 1, ..., -1, 1, 1
        and 1, -1, 1, -1, ..., 1, -1, 1
        (there are also others, of course, just to give an example).
        So we should limit the length of the combination sequence,
        so as to give a bound to the problem.

        We need to use recursive way to solve it

        [-1, 2] 4  12

        [-1, 2] 4-(-1) 12   (5) -> (6 3)
        [-1, 2] 4-2    12   (2) -> (3 0)


        [4]

    *
    * */

    public int combinationSum4_neg(int[] nums, int target, int depth, int maxSeqLen) {
        if (target == 0)
            return 1;
        if (depth == maxSeqLen) return 0;

        int res = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            res = res + combinationSum4_neg(nums, target - nums[i], depth + 1, maxSeqLen);
        }
        return res;
    }

    public int combinationSum4_2(int[] nums, int target) {
        if (nums == null) return 0;
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0 ; i < nums.length; i ++) {
            set.add(nums[i]);
        }

        int res = recurCombine(set, nums, target);
        if (set.contains(target)) {
            res ++;
        }
        return res;
    }

    // Time Exceed limits
    // everytime, calculate the combinations of a same number repeatedly
    public int recurCombine(Set<Integer> set, int[] nums, int target) {
        int count = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            int rest = target - nums[i];
            if ( rest >= 0 ) {
                if (set.contains(rest)) {
                    count ++;
                }
                count = count + recurCombine(set, nums, rest);
            }

        }
        return count;
    }

    // dp
    // dp[i] == hashmap.get(i) // how many combinations can num i has
    // [1, 2, 3]
    // 4: 1 3 hashmap.get(1)  2 2 hashmap.get(2)   3 1 hashmap.get(3)
    // hashmap.set(4)  =
    // 1. If these has been calculated:
    //     hashmap.get(3) (4-1) + hashmap.get(2) (4-2) + hashmap.get(1) (4-3)
    // 2. Else:
    //    count  = count + recurCombine(4-1) + recurCombine(4-2) + recurCombine(4-3)

    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public int combinationSum4(int[] nums, int target) {
        if (nums == null) return 0;
        if (target < 0) return 0;
        else if (target == 0) return 1; // base case
        // 4  1 2 3   3 1 2 3

        // important !!! use memory
        if (map.containsKey(target)) return map.get(target);
        int count = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            count = count + combinationSum4(nums, target - nums[i]);
        }
        map.put(target, count);
        return count;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2};
        CombinationSumIV ob = new CombinationSumIV();
        System.out.println(ob.combinationSum4_neg(nums, 4, 0, 12));
    }
}
