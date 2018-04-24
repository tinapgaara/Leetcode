package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/28/15.
 *
 * 216. Combination Sum III
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


 Example 1:

 Input: k = 3, n = 7

 Output:

 [[1,2,4]]
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 0) return res;
        List<Integer> path = new ArrayList<>();
        recurCombine3(k, 1, n, path, res);
        return res;
    }
    public void recurCombine3(int k, int index, int target, List<Integer> path, List<List<Integer>> res) {
        // base case
        if (k == 0) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = index; i <= 9; i ++) {
            path.add(i);
            recurCombine3(k - 1, i + 1,target - i, path, res);// i + 1
            path.remove(path.size() - 1);
        }
    }
}
