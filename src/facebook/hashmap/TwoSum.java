package facebook.hashmap;

import java.util.HashMap;

/**
 * Created by yingtan on 5/20/17.
 *
 * 1. Two Sum Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 502516
 Total Submissions: 1523654
 Difficulty: Easy
 Contributor: LeetCode
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) return res;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < nums.length; i ++) {
            int num1 = nums[i];
            if (map.containsKey(target - num1)) { // don’t use same elements twice
                int index = map.get(target - num1);
                res[0] = index;
                res[1] = i;
                return res;
            }
            map.put(num1, i);
        }
        return res;
    }

}
