package uber.backtrack;

/**
 * Created by yingtan on 12/2/17.
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

   32
  /  \
 2   16     [2,16]
    /  \
   2   8    [2,2,8]
      / \
     2  4   [2,2,2,4]
       / \
      2  2  [2,2,2,2]

   32
  / \
 4   8      [4, 8]
    /\
   4
 */
import java.util.*;
public class FactorCombinations {

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 0 ) return res;
        List<Integer> path = new ArrayList<>();
        recur(n, 2, path, res);
        return res;
    }
    /*
    *
   32
  /  \
 2   16     [2,16]
    /  \
   2   8    [2,2,8]
      / \
     2  4   [2,2,2,4]
       / \
      2  2  [2,2,2,2]

   32
  / \
 4   8      [4, 8]
    /\
   4
    * */
    public void recur(int n, int startNum, List<Integer> path, List<List<Integer>> res) {
        for (int i = startNum; i * i <= n; i ++) {
            if (n % i == 0) {
                // important !!!! n/i must >= i
                if (n / i >= i) {
                    List<Integer> rescopy = new ArrayList<>(path);
                    rescopy.add(i);
                    rescopy.add(n / i);
                    res.add(rescopy);

                    List<Integer> nextcopy = new ArrayList<>(path);
                    nextcopy.add(i);
                    recur(n / i, i, nextcopy, res);
                    nextcopy.remove(nextcopy.size() - 1);
                }
            }

        }
    }

    public static void main(String[] args) {
        FactorCombinations ob = new FactorCombinations();
        System.out.println(ob.getFactors(32));
    }
}
