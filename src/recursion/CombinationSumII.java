package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 10/14/17.
 *
 * 40. Combination Sum II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 Each number in C may only be used once in the combination.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 A solution set is:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 */
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
