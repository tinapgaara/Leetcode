package facebook.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 5/20/17.
 *
 * 78. Subsets Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 157182
 Total Submissions: 403119
 Difficulty: Medium
 Contributor: LeetCode
 Given a set of distinct integers, nums, return all possible subsets.

 Note: The solution set must not contain duplicate subsets.

 For example,
 If nums = [1,2,3], a solution is:

 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
 */
public class Subset {

    // Solution 1: use bits [1, 2, 3] & [0, 0, 0], [0, 0, 1], [0, 1, 0] ....
    public List<List<Integer>> subsets_bits(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);

        int len = nums.length;
        int bits = (int)Math.pow(2, len);
        for (int i = 0 ; i < bits; i ++) {
            int numToAnd = i;
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < len ; j ++) {
                int digit = (numToAnd) & 1;
                if (digit == 1)
                    list.add(nums[j]);
                numToAnd = numToAnd >> 1;
            }
            res.add(list);
        }

        return res;
    }

    // Use recursion: slow
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);

        return recurSubsets(nums, 0);
    }

    public List<List<Integer>> recurSubsets(int[] nums, int cur) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (cur == nums.length - 1) {
            List<Integer> self = new ArrayList<Integer>();
            self.add(nums[cur]);

            List<Integer> empty = new ArrayList<Integer>();

            res.add(self);
            res.add(empty);

            return res;
        }

        int num = nums[cur];

        List<List<Integer>> next = recurSubsets(nums, cur + 1);

        for (List<Integer> list : next) {
            List<Integer> copy = new ArrayList<Integer>(list);
            res.add(copy);

            list.add(0, num);
            res.add(list);
        }
        return res;
    }
}
