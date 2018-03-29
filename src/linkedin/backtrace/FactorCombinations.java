package linkedin.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/18/17.
 *
 * 254. Factor Combinations
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Numbers can be regarded as product of its factors. For example,

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
public class FactorCombinations {

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        recurFactors(n, path, res, 2);
        return res;
    }
    // startNum is important !!! 12 = 3 * 4,  must loop the number larger than 3 (from 4)
    public void recurFactors(int n, List<Integer> path, List<List<Integer>> res, int startNum) {
        for (int i = startNum; i * i <= n; i ++) {
            if (n % i == 0) {
                // this is a solution
                path.add(i);
                List<Integer> pathres = new ArrayList<Integer>(path);
                pathres.add(n/i);
                res.add(pathres);

                recurFactors(n/i, path, res, i);
                path.remove(path.size() - 1);
            }
        }
    }
}
