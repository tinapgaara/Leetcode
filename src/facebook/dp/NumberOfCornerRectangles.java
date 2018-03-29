package facebook.dp;

/**
 * Created by yingtan on 1/7/18.
 *
 * 750. Number Of Corner Rectangles
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

 A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

 Example 1:
 Input: grid =
 [[1, 0, 0, 1, 0],
 [0, 0, 1, 0, 1],
 [0, 0, 0, 1, 0],
 [1, 0, 1, 0, 1]]
 Output: 1
 Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
 Example 2:
 Input: grid =
 [[1, 1, 1],
 [1, 1, 1],
 [1, 1, 1]]
 Output: 9
 Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
 Example 3:
 Input: grid =
 [[1, 1, 1, 1]]
 Output: 0
 Explanation: Rectangles must have four distinct corners.

 To find an axis-aligned rectangle, my idea is to fix two rows (or two columns) first, then check column by column to find “1” on both rows. Say you find n pairs, then just pick any 2 of those to form an axis-aligned rectangle (calculating how many in total is just high school math, hint: combination).

 */
public class NumberOfCornerRectangles {

    // time complexity: o(row * row * col)
    public int countCornerRectangles(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int res = 0;
        for (int i = 0 ; i < grid.length; i ++) {
            for (int j = i + 1 ; j < grid.length; j ++) {
                // fixed two rows
                // loop all columns, find out how many cols is 1 in ith row and jth row
                int count = 0;
                for (int k = 0 ; k < grid[0].length; k ++) {
                    if (grid[i][k] == 1 && grid[j][k] == 1) {
                        count ++;
                    }
                }
                // then C(count, 2) choose 2 from count and do combinations
                if (count > 0) {
                    res = res + count * (count-1) / 2;
                }
            }
        }
        return res;
    }
}
