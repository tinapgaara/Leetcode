package DP;

/**
 * Created by yingtan on 2/27/18.
 * 718. Maximum Length of Repeated Subarray
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

 Example 1:
 Input:
 A: [1,2,3,2,1]
 B: [3,2,1,4,7]
 Output: 3
 Explanation:
 The repeated subarray with maximum length is [3, 2, 1].
 */
// Same idea as longest common subsequence
public class MaximumLengthOfRepeatedSubarray {
    public int findLength(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;
        int alen = A.length;
        int blen = B.length;
        int[][] dp = new int[alen][blen];// maxlen of A,B which has the same character at the end
        int max = 0;
        if(A[0] == B[0]) {
            dp[0][0] = 1;
            max = Math.max(max, 1);
        }
        for (int i = 1; i < A.length; i ++) {
            if (A[i] == B[0]) {
                dp[i][0] = 1;
                max = Math.max(max, 1);
            }
        }
        for (int j = 1; j < B.length; j ++) {
            if (B[j] == A[0]) {
                dp[0][j] = 1;
                max = Math.max(max, 1);
            }
        }
        for (int i = 1; i < A.length; i ++) {
            for (int j = 1; j < B.length; j ++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(max, dp[i][j]);
                }

            }
        }
        return max;
    }
}
