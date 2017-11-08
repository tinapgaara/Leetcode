package google.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 2/5/17.
 */
public class FindTargetSumWays {

    public int findTargetSumWays(int[] nums, int S) {
        if ( (nums == null) || (nums.length == 0) ) return 0;

        // the ith number in nums with value j, how many ways to reach S
        // int[][] dp = new int[nums.length][2 * 1000 + 1];
        // dp[i][-1000]  dp[i][1000] dp[i][0]

        // dp[i][S] = dp[i-1][S + nums[i]] + dp[i-1][S - nums[i]] how to imple ?

        // evolve to use DFS + recur
        return dfsFind(nums, S, 0, 0);
    }

    public int dfsFind(int[] nums, int S, int curIndex, int curRes) {
        if (curIndex >= nums.length) return 0;

        if (curIndex == nums.length - 1) {
            int res = 0;
            if (curRes + nums[curIndex] == S) {
                res ++;
            }
            if (curRes - nums[curIndex] == S) {
                res ++;
            }
            return res;
        }

        int curNum = nums[curIndex];
        return dfsFind(nums, S, curIndex + 1, curRes + curNum) + dfsFind(nums, S, curIndex + 1, curRes - curNum);
    }
    // Time exceeds limits
    public int findTargetSumWays2(int[] nums, int S) {

        if ( (nums == null) || (nums.length == 0) ) return 0;
        List<Integer>[] dp = new ArrayList[nums.length];
        List<Integer> first = new ArrayList<Integer>();
        first.add(nums[0]);
        first.add(-1 * nums[0]);
        dp[0] = first;

        int res = 0;
        for (int i = 1; i < nums.length; i ++) {
            List<Integer> preWays = dp[i-1];
            int curNum = nums[i];
            if (preWays != null) {
                List<Integer> curWays = new ArrayList<Integer>();
                for (Integer way : preWays) {
                    int addNum = way.intValue() + curNum;
                    int minNum = way.intValue() - curNum;
                    curWays.add(addNum);
                    curWays.add(minNum);
                }
                dp[i] = curWays;
            }
        }
        List<Integer> finalWays = dp[nums.length - 1];
        for (Integer sum : finalWays) {
            if (sum == S) {
                res ++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{44,20,38,6,2,47,18,50,41,38,32,24,38,38,30,5,26,15,37,35};
        FindTargetSumWays ob = new FindTargetSumWays();
        System.out.println(ob.findTargetSumWays(nums, 44));
    }
}
