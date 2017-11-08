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
public class CombinationII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        // important !!! need to sort in order to jump duplicate numbers
        Arrays.sort(candidates);
        recurCombine_topToDown(candidates, target, res,path, 0);

        // return recurCombine_DownToTop(candidates, target, 0);
        return res;
    }

    public void recurCombine_topToDown(int[] candidates, int target, List<List<Integer>> res, List<Integer> path, int index) {
        if (target < 0) return;
        else if (target == 0) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for(int i = index ; i < candidates.length; i ++) {
            path.add(candidates[i]);
            recurCombine_topToDown(candidates, target - candidates[i], res, path, i+1);
            path.remove(path.size() - 1);
            // Very important !!! The solution set must not contain duplicate combinations.
            while(i+1 < candidates.length && candidates[i] == candidates[i+1]) {
                i ++;
            }
        }
    }

    public List<List<Integer>> recurCombine_DownToTop(int[] candidates, int target, int index) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (target < 0) return null;
        else if (target == 0) {
            res.add(new ArrayList<Integer>());
            return res;
        }
        for(int i = index ; i < candidates.length; i ++) {
            List<List<Integer>> lists = recurCombine_DownToTop(candidates, target - candidates[i], i+1);
            if (lists != null) {
                for (List<Integer> list : lists) {
                    List<Integer> copy = new ArrayList<Integer>(list);
                    copy.add(candidates[i]);
                    res.add(copy);
                }
            }
            // Very important !!! The solution set must not contain duplicate combinations.
            while(i+1 < candidates.length && candidates[i] == candidates[i+1]) {
                i ++;
            }
        }
        return res;
    }
}
