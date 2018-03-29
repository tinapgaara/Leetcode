package facebook.dp;

/**
 * Created by yingtan on 3/21/18.
 *
 * 44. Wildcard Matching
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "*") → true
 isMatch("aa", "a*") → true
 isMatch("ab", "?*") → true
 isMatch("aab", "c*a*b") → false
 */
public class WildcardMatching {
    // since * represent any sequence , two cases:
    // if  ch_j == '*'
    // 1) represent zero length: dp[i][j] = dp[i][j-1]
    // 2) represent any sequence: dp[i][j] = dp[i-1][j]
    // so, dp[i][j] = dp[i][j-1] || dp[i-1][j]
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 1 ; j <= p.length(); j ++) {
            if (p.charAt(j-1) == '*') {
                dp[0][j] = dp[0][j-1];
            }
        }
        for (int i = 1; i <= s.length(); i ++) {
            for (int j = 1; j <= p.length(); j ++) {
                if (p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                }
                else if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
                else {
                    if (p.charAt(j-1) == s.charAt(i-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
