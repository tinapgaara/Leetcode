package linkedin.string.palindrome;

/**
 * Created by yingtan on 11/20/17.
 *
 * 647. Palindromic Substrings
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
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
        if(s == null) return 0;
        int count = 0;
        // for a, b, c
        count = count + s.length();
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int len = 1;  len < s.length(); len ++) {
            for (int i = 0 ; i < s.length() - len ; i ++) {
                int j = i + len;
                if (s.charAt(i) == s.charAt(j)) {
                    // j - i >= 2 : (aaa aa)
                    if (j - i <= 2 || dp[i+1][j-1]) {
                        dp[i][j] = true;
                        count ++;
                    }
                }
            }
        }
        return count;
    }
}
