package linkedin.dp;

/**
 * Created by yingtan on 11/28/17.
 */
public class PaintHouse {

    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;

        // costs[i][0] : total cost of painting house 0~i with color 0 in ith house
        // reuse costs, so no need to use space
        // only one time will visit costs[i][0]
        for (int i = 1; i < costs.length; i ++) {
            // total cost of painting house 0~i with color 0 in ith house =
            // cost of painting house ith with color 0 + min costs of (total costs of painting house 0~i-1 with color 1 in i-1th house and total costs of painting house 0~i-1 with color 2)
            // when we use costs[i][0] + .. here, it is first time to use costs[i][0], so costs[i][0] is the cost of paining house i individually
            costs[i][0] = costs[i][0] + Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] = costs[i][1] + Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] = costs[i][2] + Math.min(costs[i-1][0], costs[i-1][1]);
        }
        int len = costs.length;
        return Math.min(Math.min(costs[len-1][0], costs[len-1][1]), costs[len-1][2]);
    }
}
