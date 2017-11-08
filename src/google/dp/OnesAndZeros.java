package google.dp;

/**
 * Created by yingtan on 2/5/17.
 *
 * 474. Ones and Zeroes
 Description  Submission  Discussion  Add to List
 Total Accepted: 5307
 Total Submissions: 14554
 Difficulty: Medium
 Contributors: piy9
 In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

 For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

 Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

 Note:
 The given numbers of 0s and 1s will both not exceed 100
 The size of given string array won't exceed 600.
 Example 1:
 Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 Output: 4

 Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
 Example 2:
 Input: Array = {"10", "0", "1"}, m = 1, n = 1
 Output: 2

 Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 */
public class OnesAndZeros {

    // Dp:  from top to down
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] : max ways to form i "0" + j "1"
        // dp[i][j] = Math.max(dp[i][j], 1 + dp[i - curNumContains0][j - curNumContains1]);

        if ( (strs == null) || (strs.length == 0) )  return 0;
        int[][] dp = new int[m+1][n+1];

        for (String str : strs) {
            int[] num = count(str);
            int zero = num[0];
            int one = num[1];

            // req: m > zero , n < one
            // dp[zero][one] = 1;
            for (int i = m ; i >= zero ; i --) {
                for (int j = n ; j >= one; j --) {
                    dp[i][j] = Math.max(dp[i-zero][j-one] + 1, dp[i][j]);

                }
            }
            // first element: "10"
            // dp[5][3]  = 1,  which means, we use only one number to form dp[1][1], but can not form dp[5][3], so just let
            // dp[5][3] = dp[0][0]
            //System.out.println(dp[m][n]);
        }
        return dp[m][n];

    }

    // Dp: from down to top
    public int findMaxFormDown2Top(String[] strs, int m, int n) {
        // dp[i][j] : include cur num, max ways to form i "0" + j "1"
        // dp[i][j] = Math.max(dp[i][j], 1 + dp[i - curNumContains0][j - curNumContains1]);

        if ( (strs == null) || (strs.length == 0) )  return 0;
        int[][] dp = new int[m+1][n+1];
        for (String str : strs) {
            int[] num = count(str);
            int zero = num[0];
            int one = num[1];

            int[][] newdp = new int[m+1][n+1]; // everytime when add a new num, how many num used to form i "0" tp j "1"
            for (int i = 0 ; i < m + 1 ; i ++) {
                for (int j = 0 ; j < n + 1; j ++) {
                    if ( ( i >= zero ) && (j >= one) ) {
                        newdp[i][j] = Math.max(dp[i-zero][j-one] + 1, dp[i][j]);
                    }
                    else {
                        newdp[i][j] = dp[i][j];
                    }
                }
            }
            dp = newdp;
        }
        return dp[m][n];

    }


    public int[] count(String str) {
        int[] res = new int[2];
        int zero = 0;
        int one = 0;
        for (int i = 0 ; i < str.length(); i ++) {
            char ch = str.charAt(i);
            if (ch == '0') {
                zero ++;
            }
            else if (ch == '1') {
                one ++;
            }
        }
        res[0] = zero;
        res[1] = one;
        return res;
    }
}
