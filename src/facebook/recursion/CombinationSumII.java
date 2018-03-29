package facebook.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class CombinationSumII {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        recurCombine(candidates, 0, target, path, res);
        return res;
    }
    public void recurCombine(int[] candidates, int index, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        else if (target < 0 || index >= candidates.length) {
            return;
        }
        for (int i = index; i < candidates.length; i ++) {
            if (i > index && candidates[i]  == candidates[i-1]) continue;
            path.add(candidates[i]);
            recurCombine(candidates, i+1, target - candidates[i], path, res);
            path.remove(path.size() - 1);
        }
    }
}
