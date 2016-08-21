package google.dp;

/**
 * Created by yingtan on 12/22/15.
 */
public class PaintHouse {

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
}
