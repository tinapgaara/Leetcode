package amazon.greedy;

/**
 * Created by yingtan on 3/24/18.
 *
 * 646. Maximum Length of Pair Chain
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

 Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

 Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

 Example 1:
 Input: [[1,2], [2,3], [3,4]]
 Output: 2
 Explanation: The longest chain is [1,2] -> [3,4]
 Note:
 */
import java.util.*;
public class MaximumLengthOfPairChain {
    public int findLongestChain(int[][] pairs) {
        return findLongestChain_greedy(pairs);
    }
    // o(nlogn)
    public int findLongestChain_greedy(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        // greedy: sort by end time
        // greedy: make sure task finishs earlier and be greater, so sort intervals by end time
        Arrays.sort(pairs, (a,b) -> a[1] - b[1]);
        int[] last = pairs[0];
        int len = 1;
        for (int i = 1; i < pairs.length; i ++) {
            System.out.println(pairs[i][0] + " " + pairs[i][1]);
            int[] cur = pairs[i];
            if (cur[0] > last[1]) {
                last = cur;
                len ++;
            }
            else {
                if (cur[1] < last[0]) {
                    last = cur;
                }
            }
        }
        return len;
    }
    //o(n*n)
    public int findLongestChain_dp(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        PairComparator comp = new PairComparator();
        List<int[]> list = new ArrayList<>();
        for (int[] pair: pairs) {
            list.add(pair);
        }
        Collections.sort(list, comp);
        int[] dp = new int[list.size()];
        dp[0] = 1;
        for (int i = 1; i < list.size(); i ++) {
            dp[i] = 1;
            for (int j = 0; j < i; j ++) {
                if(list.get(i)[0] > list.get(j)[1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < dp.length; i ++) {
            max = Math.max(max, dp[i]);
        }
        return dp[list.size() - 1];
    }
    public class PairComparator implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            if (p1[0] == p2[0]) {
                return p1[1] - p2[1];
            }
            else {
                return p1[0] - p2[0];
            }
        }
    }
}
