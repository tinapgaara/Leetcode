package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 4/17/18.
 *
 * 77. Combinations
 DescriptionHintsSubmissionsDiscussSolution
 Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 For example,
 If n = 4 and k = 2, a solution is:

 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        recurCombine(1, n, k, path, res);
        return res;
    }
    public void recurCombine(int index, int n, int k, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index ; i <= n ; i ++) {
            path.add(i);
            recurCombine(i + 1, n, k, path, res);
            path.remove(path.size() - 1);
        }
    }
}
