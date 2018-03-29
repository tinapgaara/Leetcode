package DP;

/**
 * Created by yingtan on 2/24/18.
 *
 * 546. Remove Boxes
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given several boxes with different colors represented by different positive numbers.
 You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
 Find the maximum points you can get.

 Example 1:
 Input:

 [1, 3, 2, 2, 2, 3, 4, 3, 1]
 Output:
 23
 Explanation:
 [1, 3, 2, 2, 2, 3, 4, 3, 1]
 ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 ----> [1, 3, 3, 3, 1] (1*1=1 points)
 ----> [1, 1] (3*3=9 points)
 ----> [] (2*2=4 points)
 */
import java.util.*;
public class RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
        //return dfs_solution(boxes); too slow
        Integer[][][] dp = new Integer[boxes.length][boxes.length][boxes.length];
        return dfs_dp_solution(boxes, 0, boxes.length-1, 0, dp);
    }
    // Solution 1: using dp:
    // dp[i][j][k] : max value of [i,j] when there are k consecutive numbers before i
    // 所以我们的dp数组应该是一个三维数组dp[i][j][k]，表示区间[i, j]中能获得的最大积分，当boxes[i]左边有k个数字跟其相等
    //     对于dp[i][j][k]，如果我们移除boxes[i]，那么我们得到(1+k)*(1+k) + dp[i+1][j][0]
    //     当某个位置m，有boxes[i] == boxes[m]时，我们也应该考虑先移除[i+1,m-1]这部分，
    //        我们得到积分dp[i+1][m-1][0]，然后再处理剩下的部分，得到积分dp[m][j][k+1]，
    //        这里k加1点原因是，移除了中间的部分后，原本和boxes[m]不相邻的boxes[i]现在相邻了，又因为二者值相同，所以k应该加1
    // dp[i][j][k] = Math.max( ((k+1 * k+1) +dp[i+1][j][0]), dp[i+1][m-1][0] + dp[m+1][j][k+1])  when nums[i] == nums[m]
    public int dfs_dp_solution(int[] boxes, int i, int j, int k, Integer[][][] dp) {
        if (i > j) return 0;
        if (dp[i][j][k] != null) {
            return dp[i][j][k];
        }
        while(i + 1 <= j && boxes[i] == boxes[i+1]) {
            i ++;
            k ++;
        }
        //对于dp[i][j][k]，如果我们移除boxes[i]，那么我们得到(1+k)*(1+k) + dp[i+1][j][0]
        int res = (k+1) * (k+1) + dfs_dp_solution(boxes, i+1, j, 0, dp);
        for (int m = i + 1; m <= j; m ++) {
            if (boxes[m] == boxes[i]) {
                //当某个位置m，有boxes[i] == boxes[m]时，我们也应该考虑先移除[i+1,m-1]这部分，
                res = Math.max(res, dfs_dp_solution(boxes, i+1, m-1, 0, dp) + dfs_dp_solution(boxes, m, j, k+1, dp));
            }
        }
        dp[i][j][k] =res;
        return res;
    }
    // Solution 2: using dfs  + memorization, the key is array->string, Time Exceed Limit !
    public int dfs_solution(int[] boxes) {
        Map<String, Integer> maxProfits = new HashMap<>();
        boolean[] vis = new boolean[boxes.length];
        dfs(boxes, vis, maxProfits);
        String key = "";
        for (int num : boxes) {
            key = key + num + "_";
        }
        return maxProfits.get(key);
    }
    public int dfs(int[] nums, boolean[] vis, Map<String, Integer> maxProfits) {
        String key = encode(nums, vis);
        if (key.length() == 0) {
            // vis all nodes
            return 0;
        }
        if (maxProfits.containsKey(key)) {
            return maxProfits.get(key);
        }
        int prev = -1;
        int count = 0;
        int maxCost = 0;
        boolean[] originalVis = vis.clone();
        for (int i = 0; i < nums.length; i ++) {
            if (vis[i]) continue;
                // error rpone
            else if (prev == -1) {
                prev = i;
            }
            if (nums[i] != nums[prev]) {
                // not consecutive, calculate cost
                int cost = count * count;
                maxCost = Math.max(maxCost, cost + dfs(nums, vis, maxProfits));
                // reset everything to loop before
                prev = i;
                count = 1;
                // very important !!!! using clone here
                vis = originalVis.clone();
                // error prone, must set prev = true
                vis[prev] = true;
            }
            else {
                count ++;
                vis[i] = true;
            }
        }
        if (count > 0) {
            int cost = count * count;
            maxCost = Math.max(maxCost, cost);
        }
        maxProfits.put(key, maxCost);
        return maxCost;
    }
    public String encode(int[] nums, boolean[] vis) {
        String res = "";
        for (int i = 0 ; i < nums.length; i ++) {
            int num = nums[i];
            if (! vis[i]) res = res + num + "_";
        }
        return res;
    }
}
