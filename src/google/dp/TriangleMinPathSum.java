package google.dp;

import java.util.List;

/**
 * Created by yingtan on 11/21/15.
 */
/*
* Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
*
* */
public class TriangleMinPathSum {

    // Down to Top // More efficient and more simple
    // space: o()
    public int minimumTotal_Down2Top(List<List<Integer>> triangle) {
        // one dimensional : space: o(row)
        if ((triangle == null) || (triangle.size() == 0))
            return 0;
        int row = triangle.size();
        int[] dp = new int[row];
        for (int i = 0 ; i < triangle.get(row-1).size(); i ++) {
            dp[i] = triangle.get(row-1).get(i);
        }

        for (int i = row-2; i>= 0 ; i --) {
            for (int j = 0 ; j < triangle.get(i).size(); j ++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    // Top to Down
    public int minimumTotal_Top2Down(List<List<Integer>> triangle) {
        // one dimensional : space: o(n)
        if ((triangle == null) || (triangle.size() == 0))
            return 0;
        int row = triangle.size();
        int col = triangle.get(row-1).size();

        int[] dp = new int[1];
        dp[0] = triangle.get(0).get(0);

        int curRow = 1;
        int minNum = dp[0];
        while (curRow < row) {
            int levelSize = triangle.get(curRow).size();
            int[] newDp = new int[levelSize];
            newDp[0] = dp[0] + triangle.get(curRow).get(0);
            minNum = newDp[0];
            int prevLen = dp.length;

            newDp[levelSize-1] = dp[prevLen-1] + triangle.get(curRow).get(levelSize-1);
            minNum = Math.min(minNum, newDp[levelSize-1]);

            for (int i = 1 ; i < levelSize-1; i ++) {
                newDp[i] = Math.min(dp[i-1], dp[i]) + triangle.get(curRow).get(i);
                minNum = Math.min(minNum, newDp[i]);
            }
            dp = newDp;
            curRow ++;
        }
        return minNum;
    }

    // how to do this in o(row) ???
    // Down to Top
}
