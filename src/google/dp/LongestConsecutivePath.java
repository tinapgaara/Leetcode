package google.dp;

/**
 * Created by yingtan on 11/5/15.
 */
public class LongestConsecutivePath {

    // dp[i,j] = max(dp[i-1,j], dp[i,j-1], dp[i+1,j], dp[i,j+1]]) + 1
    public int longestConsecutivePath(int[][] arr, int x, int y) {
        return dp(x, y, arr);
    }


    public int dp(int i,  int j, int[][] arr) {
        int max = 0;
        if (i-1 >= 0) {
            if (arr[i-1][j] == arr[i][j] + 1)
                max = Math.max(max, dp(i-1, j, arr));
        }
        if (j-1 >= 0) {
            if (arr[i][j-1] == arr[i][j] + 1)
                max = Math.max(max, dp(i, j-1, arr));
        }
        if (i+1 < arr.length) {
            if (arr[i+1][j] == arr[i][j] + 1)
                max = Math.max(max, dp(i+1, j, arr));
        }
        if (j + 1< arr[0].length) {
            if (arr[i][j+1] == arr[i][j] + 1)
                max = Math.max(max, dp(i, j+1, arr));
        }
        return max + 1;
    }

    public static void main(String[] args) {
        LongestConsecutivePath ob = new LongestConsecutivePath();
        int[][] nums = new int[][]{{7,7,6},{9,4,5},{2,3,1}};
        System.out.println(ob.longestConsecutivePath(nums, 2, 0));
    }
}
