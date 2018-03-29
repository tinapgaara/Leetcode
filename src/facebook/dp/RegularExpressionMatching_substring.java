package facebook.dp;

/**
 * Created by yingtan on 3/21/18.
 *
 * 题目是一道hard题，leetcode 10改。给一个regression expression，
 * 问在另一个string里面有没有和他匹配的substring。面之前我刚刚看过这一题，
 * 还以为撞了大运。。。没想到。。。哎。 我的回答是把string里的每个可能的substring拿出来比较是不是match，
 * 发现有的话就返回true。具体比较的思路讲了很久，用二维dp做的。很紧张，吭哧吭哧的写完代码之后，
 * 面试官好像还是有点confused，又给他解释了一遍之后，他问时间复杂度，然后问怎么优化，我想了一会儿把时间复杂度从n^4优化到了n^3，然后没时间写了


 because we want to match substring of s,  so,
 dp[i][j] : if s[x, i-1] where x >=0 matches p[0,j-1]
 finally, loop dp[row-1][j] and if anyone = true, return true

 rule:
 if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
   // substring ending with ch_j-1 matchs p
   dp[i][j] = true;
 }
 else if (p.charAt(j-1) == '*') {
    if (p.charAt(j-2) == s.charAt(i-1)) {
        // p.charAt(j-2) can appear: zero/one/multiple times
        dp[i][j] = dp[i][j-2] || dp[i][j-1] || dp[i-1][j]
    }
    else {
        // only way to match is to make ch_j-2 appear 0 times
        dp[i][j] = dp[i][j-2]
    }
 }
 */
public class RegularExpressionMatching_substring {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s.length(); i ++) {
            dp[i][0] = true;
        }
        for (int j = 1; j <= p.length(); j++) {
            char ch = p.charAt(j - 1);
            if (ch == '*') { // a* count as empty
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                char ch_i = s.charAt(i - 1);
                char ch_j = p.charAt(j - 1);
                if (ch_i == ch_j || ch_j == '.') {
                    dp[i][j] = dp[i-1][j-1];
                    if (j == 1) {
                        // only when j == 1, this is the first word in p, if s == p, then s should be true
                        dp[i][j] = true;
                    }
                } else if (ch_j == '*') {
                    char prevch_j = p.charAt(j - 2);
                    if (prevch_j != '.' && prevch_j != ch_i) { // a* only counts as empty
                        dp[i][j] = dp[i][j - 2];
                    } else { // a* counts as single a, empty, multiple a
                        dp[i][j] = dp[i][j - 1] || dp[i][j - 2] || dp[i - 1][j];
                    }
                }
            }
        }
        for (int i = 0 ; i < s.length(); i ++) {
            if (dp[i][p.length()-1]) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        RegularExpressionMatching_substring ob = new RegularExpressionMatching_substring();
        System.out.println(ob.isMatch("accab", "cca*b"));// s p
    }

}
