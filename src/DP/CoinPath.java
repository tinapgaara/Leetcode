package DP;

/**
 * Created by yingtan on 3/8/18.
 * 656. Coin Path
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B denotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.

 Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.

 If there are multiple paths with the same cost, return the lexicographically smallest such path.

 If it's not possible to reach the place indexed N then you need to return an empty array.

 Example 1:
 Input: [1,2,4,-1,2], 2
 Output: [1,3,5]
 Example 2:
 Input: [1,2,4,-1,2], 1
 Output: []
 */
import java.util.*;
public class CoinPath {
    public List<Integer> cheapestJump(int[] A, int B) {
        //dp[next] = Math.min(dp[next], dp[cur] + cost[cur])
        List<Integer> res = new ArrayList<>();
        if (A == null || A.length == 0) return res;
        int[] dp = new int[A.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int[] prevIndex = new int[A.length];
        prevIndex[0] = 0;
        for (int i = 0 ; i < A.length - 1; i ++) {
            if (A[i] == -1) {
                // can not go to anywhere
                continue;
            }
            for (int next = i + 1; next <= i + B && next < A.length; next ++) {
                int costToNext = dp[i] + A[i];
                if (costToNext <= dp[next]) {
                    dp[next] = costToNext;
                    prevIndex[next] = i;
                }
            }
        }
        if (dp[A.length - 1] == Integer.MAX_VALUE || A[A.length - 1] == -1) {
            return res;
        }
        int i = A.length - 1;
        while(i != 0) {
            res.add(i + 1);
            i = prevIndex[i];
        }
        res.add(1);
        Collections.reverse(res);
        for (int num : A) {
            if (num != 0 && num != -1) return res;
        }
        // all zeros
        res.clear();
        for (i = 0 ; i< A.length; i ++) {
            if (A[i] != -1) res.add(i + 1);
        }
        return res;
    }
    public static void main(String[] args) {
        CoinPath ob = new CoinPath();
        int[] A = {1,2,4,-1,2};
        ob.cheapestJump(A, 1);
    }
}
