package linkedin.palindromic;

/**
 * Created by yingtan on 11/28/17.
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
