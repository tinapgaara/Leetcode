package square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 10/19/17.
 *
 *  N Sum
 *
 *
 Add to List
 18. 4Sum
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

 Note: The solution set must not contain duplicate quadruplets.

 For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

 A solution set is:
 [
 [-1,  0, 0, 1],
 [-2, -1, 1, 2],
 [-2,  0, 0, 2]
 ]
 */
public class FourSum {

    // must go with Down to Top recursive here, because we are not building result from a single value, but a pair
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        if(nums == null || nums.length == 0) {
            return res;
        }
        return nSum(nums, 0, 4, target);
    }

    public List<List<Integer>> nSum(int[] nums, int index, int n, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (n == 2) {
            return twoSum(nums, index, target);
        }
        for (int i = index; i < nums.length - n + 1; i ++) {
            // skip duplicate
            if (i > index && nums[i] == nums[i-1]) continue;

            List<List<Integer>> next = nSum(nums, i + 1, n - 1, target - nums[i]);
            for (List<Integer> level : next) {
                List<Integer> newLevel = new ArrayList<Integer>(level);
                newLevel.add(0, nums[i]);
                res.add(newLevel);
            }
        }
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int index, int target) {
        int low = index;
        int high = nums.length - 1;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        while(low < high) {
            if (nums[low] + nums[high]  == target) {
                List<Integer> level = new ArrayList<Integer>();
                level.add(nums[low]);
                level.add(nums[high]);
                res.add(level);
                // low ++ , skip the same one
                while (low + 1 < nums.length && nums[low] == nums[low + 1]) {
                    low ++;
                }
                low ++;
                // high --
                while (high - 1 >= 0 && nums[high] == nums[high - 1]) {
                    high --;
                }
                high --;
            }
            else if (nums[low] + nums[high] < target) {
                // low ++ , skip the same one
                while (low + 1 < nums.length && nums[low] == nums[low + 1]) {
                    low ++;
                }
                low ++;
            }
            else {
                // high --
                while (high - 1 >= 0 && nums[high] == nums[high - 1]) {
                    high --;
                }
                high --;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FourSum ob = new FourSum();
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        System.out.println(ob.fourSum(nums, 0));
    }
}
