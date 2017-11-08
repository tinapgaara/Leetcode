package google.dp;

/**
 * Created by yingtan on 11/4/17.
 *
 * 583. Delete Operation for Two Strings
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

 Example 1:
 Input: "sea", "eat"
 Output: 2
 Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 */
public class DeleteOperationForTwoStrings {

    public int minDistance(String word1, String word2) {
        // longest common subsequence
        if (word1 == null || word2 == null) return 0;
        int row = word1.length();
        int col = word2.length();
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0 ; i < row; i ++) {
            dp[i][0] = 0;
        }
        for (int j = 0 ; j < col; j ++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= row; i ++) {
            for (int j = 1; j <= col; j ++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
                }
                else {
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        int longestCommonSeqLen = dp[row][col];
        return word1.length() - longestCommonSeqLen + word2.length() - longestCommonSeqLen;
    }
}
