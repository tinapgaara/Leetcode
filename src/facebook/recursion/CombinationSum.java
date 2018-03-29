package facebook.recursion;
import java.util.*;
public class CombinationSum {
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
        else if (target < 0) {
            return;
        }
        for (int i = index; i < candidates.length; i ++) {
            path.add(candidates[i]);
            recurCombine(candidates, i, target - candidates[i], path, res);
            path.remove(path.size() - 1);
        }
    }
}
