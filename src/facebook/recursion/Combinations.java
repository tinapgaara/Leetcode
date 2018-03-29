package facebook.recursion;
import java.util.*;
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        recurCombine(1, k, n, path, res);
        return res;
    }
    public void recurCombine(int num, int k, int N, List<Integer> path, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = num ; i <= N ; i++) {
            path.add(i);
            recurCombine(i + 1, k -1, N, path, res);
            path.remove(path.size() - 1);
        }
    }
}
