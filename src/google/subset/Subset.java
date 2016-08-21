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

