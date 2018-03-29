package linkedin.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/22/17.
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        boolean[] vis = new boolean[nums.length];
        recurPermute(nums, 0, path, res, vis);
        return res;
    }

    public void recurPermute(int[] nums, int index, List<Integer> path, List<List<Integer>> res, boolean[] vis) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        //[2, 2 ,3]
        for (int i = 0; i < nums.length; i ++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;;
            if (! vis[i]) {
                vis[i] = true;
                path.add(nums[i]);
                recurPermute(nums, index + 1, path, res, vis);
                vis[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
