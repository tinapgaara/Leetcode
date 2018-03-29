package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 *
 * time: o(n)
 *
 */
public class PaintHouse {

    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        //costs[i][j] : paint ith house with jth color
        for (int i = 1; i < costs.length; i ++) {
            costs[i][0] = Math.min(costs[i-1][1], costs[i-1][2]) + costs[i][0];
            costs[i][1] = Math.min(costs[i-1][0], costs[i-1][2]) + costs[i][1];
            costs[i][2] = Math.min(costs[i-1][0], costs[i-1][1]) + costs[i][2];
        }
        int min = Math.min(costs[costs.length - 1][0],costs[costs.length - 1][1]);
        return Math.min(min, costs[costs.length - 1][2]);
    }
}
