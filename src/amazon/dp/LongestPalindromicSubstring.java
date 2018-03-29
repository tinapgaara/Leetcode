package amazon.dp;

/**
 * Created by yingtan on 3/25/18.
 *
 * 5. Longest Palindromic Substring
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example:

 Input: "babad"

 Output: "bab"

 Note: "aba" is also a valid answer.


 Example:

 Input: "cbbd"

 Output: "bb"
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxlen = 0;
        int[] index = new int[2];
        for (int len = 1; len <= s.length(); len ++) {
            for (int i = 0 ; i <= s.length() - len; i ++) {
                int j = i + len - 1;
                if (j - i < 2 || dp[i+1][j-1]) {
                    dp[i][j] = true;

                    int curlen = j - i + 1;
                    if (curlen > maxlen) {
                        maxlen = curlen;
                        index[0] = i;
                        index[1] = j;
                    }
                }
            }
        }
        return s.substring(index[0], index[1] + 1);
    }
}
