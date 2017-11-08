package facebook.hashTable;

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
public class twoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null) return res;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0 ; i < nums.length; i ++) {
            map.put(nums[i], i);
        }
        for (int i = 0 ; i < nums.length; i ++) {
            int num1 = nums[i];
            int num2 = target - nums[i];
            if (map.containsKey(num2)) {
                int index2 = map.get(num2);
                if (i != index2) {
                    res[0] = i;
                    res[1] = index2;
                    return res;
                }
            }
        }
        return res;
    }
}
