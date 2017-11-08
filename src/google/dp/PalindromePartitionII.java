package google.dp;

/**
 * Created by yingtan on 12/19/15.
 */
public class PalindromePartitionII {

    public int minCut(String s) {
        int len = s.length();
        int[] minCut = new int[len];
        boolean[][] dp = new boolean[len][len];

        for (int j = 0; j < len; j ++) {
            minCut[j] = j;
            for (int i = 0 ; i <=j ; i ++) {
                if ( (s.charAt(i) == s.charAt(j)) && ((j - i <= 1) || (dp[i+1][j-1])) ) {
                    dp[i][j] = true;
                    if (i > 0) {
                        minCut[j] = Math.min(minCut[j], minCut[i-1] + 1);
                    }
                    else
                        minCut[j] = 0;
                }
            }
        }
        return minCut[len-1];
    }
}
