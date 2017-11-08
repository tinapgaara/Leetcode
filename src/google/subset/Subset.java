package google.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 11/1/15.
 */
/*
* Given a set of distinct integers, nums, return all possible subsets.
*
*
* For example,
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
* */
public class Subset {

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

    // Use recursion
    public List<List<Integer>> subsets_topToDown(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> level = new ArrayList<Integer>();
        Arrays.sort(nums);

        recurSubsets(res, nums, 0, level);
        return res;
    }

    public void recurSubsets(List<List<Integer>> res, int[] nums, int index, List<Integer> level) {
        res.add(new ArrayList<Integer>(level));
        for (int i = index; i < nums.length; i ++) {
            level.add(nums[i]);
            // important !!!! should be (i+1) instead of (index + 1)
            recurSubsets(res, nums, i + 1, level);
            level.remove(level.size() - 1);
        }
        return;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if ((nums == null) || (nums.length == 0)) return res;

        Arrays.sort(nums);
        List<Integer> empty = new ArrayList<>();
        res.add(empty);

        for (int i = 0 ; i < nums.length ; i ++) {
            List<List<Integer>> newRes = new ArrayList<List<Integer>>();
            newRes.addAll(res);

            for (List<Integer> list : res) {
                List<Integer> copy = new ArrayList<>(list);
                copy.add(nums[i]);
                newRes.add(copy);
            }

            res = newRes;
        }
        return res;
    }
}

