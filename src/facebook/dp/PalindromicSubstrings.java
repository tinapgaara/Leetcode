package facebook.dp;

/**
 * Created by yingtan on 1/4/18.
 *
 * 647. Palindromic Substrings
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:
 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".
 Example 2:
 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        for (int len = 1; len <= s.length(); len ++) {
            for (int i = 0 ; i <= s.length() - len; i ++) {
                int j = i + len - 1;
                if (len <= 2 || dp[i+1][j-1]) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        count ++;
                    }
                }
            }
        }
        return count;
    }
}
