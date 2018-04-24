package lyft.recursion;

/**
 * Created by yingtan on 4/11/18.
 */
import java.util.*;
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        recurCombine(candidates, 0, target, path, res);
        return res;
    }
    public void recurCombine(int[] candidates, int index, int target, List<Integer> path, List<List<Integer>> res) {
        // base case
        if (target < 0) return; // important !!!!
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (index >= candidates.length) return;
        for (int i = index; i < candidates.length; i ++) {
            if (i > index && candidates[i] == candidates[i-1]) continue;
            path.add(candidates[i]);
            recurCombine(candidates, i + 1, target - candidates[i], path, res);
            path.remove(path.size() - 1);
        }
    }

}
