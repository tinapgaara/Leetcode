package facebook.dp;

/**
 * Created by yingtan on 2/11/18.
 *
 * 10. Regular Expression Matching
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true

 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
 3, If p.charAt(j) == '*':
 here are two sub conditions:
 1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
 2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
 dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
 or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
 or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty

 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 1 ; j <= p.length(); j ++) {
            char ch = p.charAt(j-1);
            if (ch == '*') { // a* count as empty
                dp[0][j] = dp[0][j-2];
            }
        }
        for (int i = 1; i <= s.length(); i ++) {
            for (int j = 1; j <= p.length(); j ++) {
                char ch_i = s.charAt(i - 1);
                char ch_j = p.charAt(j - 1);
                if (ch_i == ch_j || ch_j == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }
                else if (ch_j == '*') {
                    char prevch_j = p.charAt(j - 2);
                    if (prevch_j != '.' && prevch_j != ch_i) { // a* only counts as empty
                        dp[i][j] = dp[i][j-2];
                    }
                    else { // a* counts as single a, empty, multiple a
                        dp[i][j] = dp[i][j-1] || dp[i][j-2] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
