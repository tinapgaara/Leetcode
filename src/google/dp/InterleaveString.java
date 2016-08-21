package google.dp;

/**
 * Created by yingtan on 12/22/15.
 */
public class InterleaveString {

    public boolean isInterleave(String s1, String s2, String s3) {
        if ( (s1 == null) || (s2 == null) || (s3 == null) ) return false;
        int row = s1.length();
        int col = s2.length();

        if ((row + col) != s3.length()) return false;

        boolean[][] dp = new boolean[row + 1][col + 1];
        dp[0][0] = true;

        for (int i = 1 ; i <= row; i ++) {
            if ((s1.charAt(i-1) == s3.charAt(i-1)) && (dp[i-1][0])) {
                dp[i][0] = true;
            }
        }
        for (int j = 1; j <= col; j ++) {
            if ((s2.charAt(j-1) == s3.charAt(j-1)) && (dp[0][j-1])) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= row; i ++) {
            for (int j = 1; j <= col; j ++) {
                if (dp[i-1][j]) {
                    if (s1.charAt(i-1) == s3.charAt(i+j-1)) dp[i][j] = true;
                }
                else if (dp[i][j-1]) {
                    if (s2.charAt(j-1) == s3.charAt(i+j-1)) dp[i][j] = true;
                }
            }
        }
        return dp[row][col];
    }
}
