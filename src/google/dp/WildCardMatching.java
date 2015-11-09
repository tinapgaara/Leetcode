package google.dp;

/**
 * Created by yingtan on 11/7/15.
 */
public class WildCardMatching {

    public boolean isMatch(String s, String p) {
        if ((s == null) || (p == null)) return false;
        int row = s.length();
        int col = p.length();
        boolean[][] dp = new boolean[row + 1][col + 1];
        dp[0][0] = true;
        for (int i = 1; i < row + 1; i++) {
            if (s.charAt(i - 1) == '*') {
                if (dp[i-1][0]) dp[i][0] = true;
            }
        }
        for (int j = 1; j < col + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                if (dp[0][j-1]) dp[0][j] = true;
            }
        }

        for (int i = 1; i < row + 1; i++) {
            char ch_i = s.charAt(i-1);
            for (int j = 1; j < col + 1; j++) {
                char ch_j = p.charAt(j-1); // has *
                if ((ch_i == '*') || (ch_j == '*')){
                    if ((ch_i == '*') && (ch_j != '*')){
                        for (int k = j; k >= 0; k--) {
                            if (dp[i-1][k]) {
                                dp[i][j] = true;
                                break;
                            }
                        }
                    }
                    else if ((ch_i != '*') && (ch_j == '*')){
                        for (int k = i; k >= 0; k--) {
                            if (dp[k][j-1]) { // Important !!! dp[k][j-1]
                                dp[i][j] = true;
                                break;
                            }
                        }
                    }
                    else{
                        for (int m = i; m >= 0; m--) {
                            if (dp[m][j-1]) {
                                dp[i][j] = true;
                                break;
                            }
                        }
                        if (! dp[i][j]) {
                            for (int n = j; n >= 0; n--) {
                                if (dp[i-1][n]) {
                                    dp[i][j] = true;
                                    break;
                                }
                            }
                        }
                    }
                } // has ?
                else if ((ch_i == '?') || (ch_j == '?')){
                    dp[i][j] = dp[i - 1][j - 1];
                } // only regular char
                else{
                    if (ch_i == ch_j) dp[i][j] = dp[i - 1][j - 1];
                }
                 // System.out.print(" " + dp[i][j]);
            }
            // System.out.println();
        }
        return dp[row][col];
    }


    public static void main(String[] args) {
        WildCardMatching ob = new WildCardMatching();
        System.out.println(ob.isMatch("a*bcbcd", "aaab*c*"));
    }
}
