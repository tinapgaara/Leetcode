package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yingtan on 12/22/15.
 */
/*
* Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].

Show Company Tags
Show Tags
Show Similar Problems

*
* */
public class PermutationUnique {
    /*
    *
    * Use an extra boolean array " boolean[] used" to indicate whether the value is added to list.

Sort the array "int[] nums" to make sure we can skip the same value.

when a number has the same value with its previous, we can use this number only if his previous is used
    * */

    public List<List<Integer>> permuteUnique_easy(int[] nums) {
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

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int num : nums) list.add(num);
        permute(list, 0, res);
        return res;
    }
    private void permute(LinkedList<Integer> nums, int start, List<List<Integer>> res){
        if (start == nums.size() - 1){
            res.add(new LinkedList<Integer>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++){
            if (i > start && nums.get(i) == nums.get(i - 1)) continue;
            nums.add(start, nums.get(i));
            nums.remove(i + 1);
            permute(nums, start + 1, res);
            nums.add(i + 1, nums.get(start));
            nums.remove(start);
        }
    }
}
