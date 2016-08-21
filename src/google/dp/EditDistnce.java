package google.dp;

/**
 * Created by yingtan on 12/22/15.
 */
public class EditDistnce {

    public int minDistance(String word1, String word2) {
        if ((word1 == null) && (word2 == null)) {
            return 0;
        }
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0 ; i <= len1; i ++) {
            dp[i][0] = i;
        }
        for (int j = 0 ; j <= len2; j ++) {
            dp[0][j] = j;
        }
        for (int i = 1 ; i <= len1; i ++) {
            char ch1= word1.charAt(i-1);
            for (int j = 1 ; j <= len2; j ++) {
                char ch2 = word2.charAt(j-1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    int replace = dp[i-1][j-1] + 1;
                    int insert = dp[i-1][j] + 1;
                    int delete = dp[i][j-1] + 1;

                    int min = Math.min(replace, insert);
                    min = Math.min(delete, min);

                    dp[i][j] = min;
                }
            }
        }
        return dp[len1][len2];
    }
}
