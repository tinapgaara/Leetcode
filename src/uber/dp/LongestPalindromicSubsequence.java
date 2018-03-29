package uber.dp;
import java.util.*;
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int[][] dp = new int[s.length()][s.length()];
        for (int len = 1; len <= s.length(); len ++) {
            for (int i = 0 ; i <= s.length() - len; i ++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 2) {
                        dp[i][j] = len;
                    }
                    else {
                        dp[i][j] = Math.max(dp[i][j], dp[i+1][j-1] + 2);
                    }
                }
                else {
                    if (len <= 2) {
                        dp[i][j] = 1;
                    }
                    else {
                        dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                    }
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
