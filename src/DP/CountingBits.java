package DP;

/**
 * Created by yingtan on 4/18/18.
 * 338. Counting Bits
 DescriptionHintsSubmissionsDiscussSolution
 Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

 Example:
 For num = 5 you should return [0,1,1,2,1,2].

 Follow up:

 It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 Space complexity should be O(n).
 Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 Credits:
 */
public class CountingBits {
    public int[] countBits(int num) {
        // how many bits change from 0->1, how many bits change from 1->0
        // 0-> 0 1->1  2->1 4->1 8->1
        // dp[i] = dp[i - 2^x] + dp[2^x]
        int[] dp = new int[num + 1];
        dp[0] = 0;
        if (num == 0) return dp;
        dp[1] = 1;
        int multipleTwo = 2;
        for (int i = 2; i <= num; i ++) {
            if (i == multipleTwo) {
                dp[i] = 1;
                multipleTwo = multipleTwo * 2;
                continue;
            }
            else {
                int oldMultipleTwo = multipleTwo / 2;
                dp[i] = dp[i - oldMultipleTwo] + dp[oldMultipleTwo];
            }
        }
        return dp;
    }
}
