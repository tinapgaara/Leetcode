package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 4/17/18.
 *
 * 39. Combination Sum
 DescriptionHintsSubmissionsDiscussSolution
 Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

 The same repeated number may be chosen from candidates unlimited number of times.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 [7],
 [2,2,3]
 ]
 Example 2:

 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]
 */
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
        // base case
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
        }
        for (int i = index ; i < candidates.length; i ++) {
            path.add(candidates[i]);
            recurCombine(candidates, i, target - candidates[i], path, res);
            path.remove(path.size() - 1);
        }
    }
}
