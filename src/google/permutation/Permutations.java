package google.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 12/22/15.
 */
/*
* Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*
* */
public class Permutations {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        boolean[] vis = new boolean[nums.length];
        Arrays.sort(nums);
        List<Integer> level = new ArrayList<Integer>();
        recurPermute(nums, res, level,vis);
        return res;
    }

    public void recurPermute(int[] nums, List<List<Integer>> res, List<Integer>level, boolean[] vis) {
        if (level.size() == nums.length) {
            res.add(new ArrayList<Integer>(level));
            return;
        }
        for (int i = 0 ; i < nums.length; i ++) {
            // this item already been added to the result
            if (vis[i]) continue;
            // skip same items
            // important !!! must define !vis[i-1] here , case:
            // 1 + [1, 2] when add the second one to it, the first one is the caller, so the first one is already visited.in this case, we still need to add [1,2] to 1
            // 1 + [1, 1, 2] when add the third one to it, the first one is the caller, but the second one and the third one is the same one, and we need to skip the third one.
            if (i > 0 && nums[i] == nums[i-1] && !vis[i-1]) continue;
            level.add(nums[i]);
            vis[i] = true;
            recurPermute(nums, res, level, vis);
            vis[i] = false;
            level.remove(level.size() - 1);
        }
    }

    public List<List<Integer>> permute_top2Down(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (nums == null) return res;
        return recurPermute(nums, 0);
    }

    public List<List<Integer>> recurPermute(int[] nums, int index) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (index == nums.length - 1) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(nums[index]);
            res.add(list);
            return res;
        }
        int num = nums[index];
        List<List<Integer>> next = recurPermute(nums, index + 1);


        for (List<Integer> list : next) {
            for (int i = 0 ; i < list.size(); i ++) {
                List<Integer> copy = new ArrayList<Integer>(list);
                copy.add(i, num); // add element at position i
                res.add(copy);
            }
            List<Integer> lastCopy = new ArrayList<Integer>(list);
            lastCopy.add(num); // add tail
            res.add(lastCopy);
        }
        return res;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null) {
            return res;
        }
        return recurPermute(nums, 0, nums.length - 1);
    }

    public List<List<Integer>> recurPermute(int[] nums, int low, int high) {
        if ((low == high)  && (low < nums.length)) {
            List<List<Integer>> list = new ArrayList<List<Integer>>();
            List<Integer> l = new ArrayList<Integer>();
            l.add(nums[low]);
            list.add(l);
            return list;
        }

        int num = nums[low];
        List<List<Integer>> next = recurPermute(nums, low + 1, high);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (List<Integer> list : next) {
            for (int i = 0 ; i < list.size(); i ++) {
                List<Integer> copy = new ArrayList<Integer>(list);
                copy.add(i, num); // add element at position i
                res.add(copy);
            }
            List<Integer> lastCopy = new ArrayList<Integer>(list);
            lastCopy.add(num); // add tail
            res.add(lastCopy);
        }
        return res;
    }
}
