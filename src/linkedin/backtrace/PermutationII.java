package linkedin.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 11/28/17.
 */
public class PermutationII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        boolean[] vis = new boolean[nums.length];
        Arrays.sort(nums);
        List<Integer> level = new ArrayList<Integer>();
        recurPermute(nums, res, level,vis);
        return res;
    }

    public void recurPermute(int[] nums, List<List<Integer>> res, List<Integer> level, boolean[] vis) {
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
}
