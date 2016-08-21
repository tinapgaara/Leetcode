package google.dp;

/**
 * Created by yingtan on 12/22/15.
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        int len = s.length();
        int[] dp = new int[len+1];
        int maxLen = 0;
        dp[0] = 0;

        for (int i = 1 ; i <= s.length(); i ++) {
            int curStrIndex = i - 1;
            int j = curStrIndex - dp[curStrIndex] - 1;
            if (s.charAt(i-1) == ')') {
                if ((j >= 0) && (s.charAt(j) == '(')) {
                    dp[i] = dp[j] + 2 + dp[curStrIndex];
                    maxLen = Math.max(maxLen, dp[i]);
                    continue;
                }
            }
            dp[i] = 0;
        }
        return maxLen;
    }
}
