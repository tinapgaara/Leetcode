package bloomberg.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 11/15/15.
 */
/*
*
* Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
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
* */
public class SubsetI {

    public List<List<Integer>> subsets(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> empty = new ArrayList<>();
        res.add(empty);

        for (int i = 0 ; i < nums.length ; i ++) {
            int num = nums[i];
            List<List<Integer>> newList = new ArrayList<>(res);

            for (List<Integer> list : res) {
                List<Integer> copy = new ArrayList<>(list);
                copy.add(num);
                newList.add(copy);
            }
            res = newList;
        }
        return res;
    }
}
