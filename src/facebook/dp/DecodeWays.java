package facebook.dp;

/**
 * Created by yingtan on 2/11/18.
 *
 * 91. Decode Ways
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given an encoded message containing digits, determine the total number of ways to decode it.

 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

 The number of ways decoding "12" is 2.
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        // dp[i] = dp[i-2]  [i-1, i]'s is bewteen [10-26]
        // dp[i] = dp[i] + dp[i-1] , if (ith) is not zero
        int[] dp = new int[s.length() + 1];
        dp[0] = 1; // important !!!  msut be 1 here for dp[0]
        if (s.charAt(0) != '0') {
            dp[1] = 1;
        }
        for (int i = 2; i <= s.length(); i ++) {
            // ways to decode [0 - i-1]
            char ch = s.charAt(i-1);
            if (ch != '0') {
                dp[i] = dp[i] + dp[i-1];
            }
            String substr = s.substring(i-2, i);
            int second = Integer.parseInt(substr);
            if (second >= 10 && second <= 26) {
                dp[i] = dp[i] + dp[i-2];
            }
        }
        return dp[s.length()];
    }

}
