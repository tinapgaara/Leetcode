package google.dp;

import java.util.Arrays;

/**
 * Created by yingtan on 2/12/17.
 */
public class maxRussionDolls {

    public int maxEnvelopes(int[][] envelopes) {
        if ((envelopes == null) || (envelopes.length == 0)) return 0;
        int len = envelopes.length;

        //dp[i] : when add ith dolls, maxways can form

        int[] dp = new int[len];

        Arrays.sort(envelopes, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] > b[0]) {
                    return 1;
                } else if (a[0] < b[0]) {
                    return -1;
                } else if (a[0] == b[0]) {
                    if (a[1] > b[1]) {
                        return 1;
                    } else if (a[1] < b[1]) {
                        return -1;
                    }
                }
                return 0;

            }
        });

        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if ((envelopes[i][0] > envelopes[j][0]) && (envelopes[i][1] > envelopes[j][1])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        maxRussionDolls ob = new maxRussionDolls();
        int[][] envs = new int[][]{{10,8},{1,12},{6,15},{2,18}};
        ob.maxEnvelopes(envs);
    }
}
