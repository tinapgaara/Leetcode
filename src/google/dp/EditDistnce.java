package google.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 12/22/15.
 */
public class EditDistnce {

    // Sol 1: using dp
    public int minDistance_dp(String word1, String word2) {
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
                    // we need to delete ith ch in word 1
                    // .....i-1 i
                    // ......j
                    int delete = dp[i-1][j] + 1;
                    // we need to insert i+1 th ch in word1 which matchs jth ch in word2
                    // ...... i i+1
                    // ..... j-1
                    int insert = dp[i][j-1] + 1;

                    int min = Math.min(replace, insert);
                    min = Math.min(delete, min);

                    dp[i][j] = min;
                }
            }
        }
        return dp[len1][len2];
    }

    // Sol2 : using recursion
    /*
    * Time complexity:
    *
    *
    * */
    public int editDistance_recursion(String word1, String word2, int len1, int len2) {
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }

        // T(n) = 3 * T(n - 1)
        /*
        * T(n)  = 3 * T(n-1)
        * T(n-1)= 3 * T(n-2)
        * T(n-2)= 3 * T(n-3)
        * ...
        * T(2)  = 3 * T(1)
        *
        * multiple all together:
        * T(n) = 3^n * T(1)
        * so time complexity: 3^n (three to the order of n)
        * */
        if (word1.charAt(len1-1) == word2.charAt(len2 - 1)) {
            return editDistance_recursion(word1, word2, len1-1, len2 -1);
        }
        else {
            int replace = editDistance_recursion(word1, word2, len1-1, len2 -1);
            int delete = editDistance_recursion(word1, word2, len1 -1, len2);
            int insert = editDistance_recursion(word1, word2, len1, len2-1);

            int min = Math.min(replace, insert);
            min = Math.min(delete, min);
            return min;
        }
    }

}
