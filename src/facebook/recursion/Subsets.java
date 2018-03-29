package facebook.recursion;

import java.util.*;

/**
 * Created by yingtan on 12/20/17.
 *
 * 78. Subsets
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a set of distinct integers, nums, return all possible subsets (the power set).

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
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> path = new ArrayList<>();
        res.add(new ArrayList<>());
        recur(nums, 0, path, res);
        return res;
    }

    public void recur(int[] nums, int index, List<Integer> path, List<List<Integer>> res) {
        if (index == nums.length) {
            //res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < nums.length; i ++) {
            path.add(nums[i]); // [1] [2] [3]
            res.add(new ArrayList<>(path));
            recur(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
