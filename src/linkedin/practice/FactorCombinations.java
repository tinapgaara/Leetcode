package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 *
 *  Numbers can be regarded as product of its factors. For example,

 8 = 2 x 2 x 2;
 = 2 x 4.
 Write a function that takes an integer n and return all possible combinations of its factors.

 Note:
 You may assume that n is always positive.
 Factors should be greater than 1 and less than n.
 Examples:
 input: 1
 output:
 []
 input: 37
 output:
 []
 input: 12
 output:
 [
 [2, 6],
 [2, 2, 3],
 [3, 4]
 ]
 input: 32
 output:
 [
 [2, 16],
 [2, 2, 8],
 [2, 2, 2, 4],
 [2, 2, 2, 2, 2],
 [2, 4, 4],
 [4, 8]
 ]
 */

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        recurFactor(2, n, path, res);
        return res;
    }
    public void recurFactor(int startNum, int total, List<Integer> path, List<List<Integer>> res) {
        // important !!!
        for (int i = startNum; i * i <= total; i ++) {
            if (total % i == 0) {
                path.add(i);
                // Important !!!
                List<Integer> pathres = new ArrayList<>(path);
                pathres.add(total / i);
                res.add(pathres);

                recurFactor(i, total / i, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] agrs) {
        FactorCombinations ob = new FactorCombinations();
        System.out.println(ob.getFactors(32));
    }
}
