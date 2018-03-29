package facebook.recursion;
import java.util.*;
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        recurCombine(1, k, n, path, res);
        return res;
    }
    public void recurCombine(int num, int k, int target, List<Integer> path, List<List<Integer>> res) {
        if (k == 0) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = num ; i <= 9 ; i++) {
            path.add(i);
            recurCombine(i + 1, k -1, target - i, path, res);
            path.remove(path.size() - 1);
        }
    }
}
