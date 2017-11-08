package google.dp;

/**
 * Created by yingtan on 12/19/15.
 */
public class BurstBallon {

    public int maxCoins(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;
        int[] newNum = new int[nums.length + 2];
        newNum[0] = 1;
        for (int i = 0 ; i < nums.length; i ++) {
            newNum[i+1] = nums[i];
        }
        newNum[newNum.length-1] = 1;
        //dp[i][j] : kick all bullons in (i, j) , not include ith and jth bullon
        int[][] dp = new int[newNum.length][newNum.length];

        for (int len = 2; len < newNum.length; len ++) {
            for (int low = 0; low < newNum.length - len; low ++) {
                int high = low + len; // len = 1
                for (int mid = low+1; mid < high; mid ++) {
                    dp[low][high] = Math.max(dp[low][high],
                            newNum[low] * newNum[high] * newNum[mid] + dp[low][mid] + dp[mid][high]);
                }
            }
        }
        return dp[0][newNum.length-1];
    }

    public static void main(String[] args) {
        BurstBallon ob = new BurstBallon();
        int[] nums = new int[]{3,1,5,8};
        ob.maxCoins(nums);
    }
}
