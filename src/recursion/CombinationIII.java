package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 10/14/17.
 *
 * 216. Combination Sum III
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


 Example 1:

 Input: k = 3, n = 7

 Output:

 [[1,2,4]]

 Example 2:

 Input: k = 3, n = 9

 Output:

 [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        recurCombine_topToDown(res, path, k, 1, n);

        //return recurCombine_Down2Top(k, 1, n);
        return res;
    }

    public void recurCombine_topToDown(List<List<Integer>> res, List<Integer> path, int k, int index, int target) {
        if (k < 0) return;
        else if (k == 0) {
            if (target == 0) {
                res.add(new ArrayList<Integer>(path));
                return ;
            }
            else {
                return;
            }
        }
        for (int i = index; i <= 9; i ++) {
            path.add(i);
            recurCombine_topToDown(res, path, k-1, i + 1, target - i);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> recurCombine_Down2Top(int k, int index, int target) {
        List<List<Integer>> res= new ArrayList<List<Integer>>();;
        if (k < 0) return null;
        else if (k == 0) {
            if (target == 0) {
                res.add(new ArrayList<Integer>());
                return res;
            }
            else { // k == 0, but target still > 0 , so can not adds up to sum with k elements
                return res;
            }
        }
        for (int i = index; i <= 9; i ++) {
            List<List<Integer>> lists = recurCombine_Down2Top(k-1, i + 1, target - i);
            if (lists != null) {
                for (List<Integer> list : lists) {
                    List<Integer> copy = new ArrayList<Integer>(list);
                    copy.add(i);
                    res.add(copy);
                }
            }
        }
        return res;
    }
}
