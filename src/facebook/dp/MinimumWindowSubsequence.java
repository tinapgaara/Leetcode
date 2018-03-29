package facebook.dp;

import java.awt.*;

/**
 * Created by yingtan on 3/17/18.
 *
 * 727. Minimum Window Subsequence
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

 If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

 Example 1:
 Input:
 S = "abcdebdde", T = "bde"
 Output: "bcde"
 Explanation:
 "bcde" is the answer because it occurs before "bdde" which has the same length.
 "deb" is not a smaller window because the elements of T in the window must occur in order.
 Note:

 All the strings in the input will only contain lowercase letters.
 The length of S will be in the range [1, 20000].
 The length of T will be in the range [1, 100].

 */
public class MinimumWindowSubsequence {

    // s_i == t_i: dp[i][j] = Math.min(dp[i-1][j-1] + 1, dp[i-1][j])
    // TODO: wrong
    public String minWindow(String S, String T) {
        if (S == null || T == null) return null;
        Integer[][] dp = new Integer[S.length()][T.length()];
        Point[][] res = new Point[S.length()][T.length()];
        for (int i = 0 ; i < S.length(); i ++) {
            if (S.charAt(i) == T.charAt(0)) {
                dp[i][0] = 1;
                res[i][0] = new Point(i, i);
            }
            else if (i > 0){
                dp[i][0] = dp[i-1][0];
                res[i][0] = new Point(res[i-1][0].x, res[i-1][0].y);
            }
        }
        for (int i = 1; i < S.length(); i ++) {
            for (int j = 1; j < T.length(); j ++) {
                if (S.charAt(i) == T.charAt(j)) {
                    if (dp[i-1][j-1] != null) {
                        dp[i][j] = dp[i-1][j-1] + (i - res[i-1][j-1].y);
                        Point prev = res[i-1][j-1];
                        res[i][j] = new Point(prev.x, i);
                    }
                    if (dp[i-1][j] != null) {
                        if (dp[i][j] == null || dp[i-1][j] < dp[i][j]) {
                            dp[i][j] = dp[i-1][j];
                            Point prev = res[i-1][j];
                            res[i][j] = new Point(prev.x, prev.y);
                        }
                        else if (dp[i][j].intValue() == dp[i-1][j].intValue()) {
                            Point up = res[i-1][j];
                            Point leftUp = res[i][j];
                            if (up.x < leftUp.x) {
                                // using up one
                                res[i][j].x = up.x;
                                res[i][j].y = up.y;
                            }
                            else if (up.x > leftUp.x) {
                                // using leftup one
                                res[i][j].x = leftUp.x;
                            }
                        }
                    }
                }
                else {
                    if (dp[i-1][j] != null) {
                        dp[i][j] = dp[i-1][j];
                        Point prev = res[i-1][j];
                        res[i][j] = new Point(prev.x, prev.y);
                    }
                }
                //if (res[i][j] != null) {
                    //System.out.println(i + "," + j + ":" + res[i][j].x + "," + res[i][j].y);
                //}
            }
        }
        Point end = res[S.length()-1][T.length()-1];
        return S.substring(end.x, end.y + 1);
    }
    public static void main(String[] args) {
        MinimumWindowSubsequence ob = new MinimumWindowSubsequence();
        System.out.println(ob.minWindow("abcdebdde", "bde"));
    }
}
