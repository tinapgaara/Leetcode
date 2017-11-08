package DP;

/**
 * Created by yingtan on 9/25/15.
 */
public class PaintHouse {

    // more efficiently
    public int minCost_dp(int[][] costs) {
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


    public int minCost(int[][] costs) {
        if (costs == null) return 0;
        int row = costs.length;
        if (row == 0) return 0;
        int col = costs[0].length;

        int[][] minCost = new int[row][col];
        minCost[0][0] = costs[0][0];
        minCost[0][1] = costs[0][1];
        minCost[0][2] = costs[0][2];

        for (int curHouse = 1; curHouse < row; curHouse ++) {
            // mincost of painting house i ending with color j
            for (int curColor = 0; curColor < 3; curColor ++) {
                int prevColor = 0;
                int minCurCost = Integer.MAX_VALUE;
                while (prevColor < 3) {
                    if (prevColor != curColor) {
                        int curCost = minCost[curHouse-1][prevColor] + costs[curHouse][curColor];
                        minCurCost = Math.min(minCurCost, curCost);
                    }
                    prevColor ++;
                }
                minCost[curHouse][curColor] = minCurCost;
            }
        }
        int min = Math.min(minCost[row-1][0], minCost[row-1][1]);
        min = Math.min(min, minCost[row-1][2]);
        return min;
    }

    // k different colors: Could you solve it in O(nk) runtime?
    public int minCostII(int[][] costs) {
        if (costs == null) return 0;
        int row = costs.length;
        if (row == 0) return 0;
        int col = costs[0].length;

        int[][] minCost = new int[row][col];
        for (int i = 0; i < col ; i ++) {
            minCost[0][i] = costs[0][i];
        }

        for (int curHouse = 1; curHouse < row; curHouse ++) {
            // mincost of painting house i ending with color j
            for (int curColor = 0; curColor < col; curColor ++) {
                int prevColor = 0;
                int minCurCost = Integer.MAX_VALUE;
                while (prevColor < col) {
                    if (prevColor != curColor) {
                        int curCost = minCost[curHouse-1][prevColor] + costs[curHouse][curColor];
                        minCurCost = Math.min(minCurCost, curCost);
                    }
                    prevColor ++;
                }
                minCost[curHouse][curColor] = minCurCost;
            }
        }
        int min = minCost[row-1][0];
        for (int i = 1; i < col ; i ++) {
            min = Math.min(min, minCost[row-1][i]);
        }
        return min;
    }

    public static void main(String[] args) {
        PaintHouse ob = new PaintHouse();
        int[][] costs = new int[][]{{20,18,4},{9,9,10}};
        ob.minCost(costs);
    }
}
