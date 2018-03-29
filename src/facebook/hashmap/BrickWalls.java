package facebook.hashmap;

/**
 * Created by yingtan on 12/22/17.
 *
 * 554. Brick Wall
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.

 The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

 If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

 You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

 Example:
 Input:
 [[1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]]
 Output: 2
 Explanation:

 */
import java.util.*;
public class BrickWalls {
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0) return 0;
        HashMap<Integer, Integer> sumToCount = new HashMap<>();
        int rowSize = wall.size();
        int totalsum = 0;
        for (List<Integer> row : wall) {
            int sum = 0;
            totalsum = 0;
            for (int i = 0 ; i < row.size(); i ++) {
                sum = sum + row.get(i);
                if (sumToCount.containsKey(sum)) {
                    sumToCount.put(sum, sumToCount.get(sum) + 1);
                }
                else {
                    sumToCount.put(sum, 1);
                }
            }
            totalsum = sum;
        }
        int min = wall.size();
        for (Map.Entry<Integer, Integer> entry : sumToCount.entrySet()) {
            // skip the last, cut off in the last pos does not count
            if (entry.getKey() == totalsum) continue;
            int acrossBricksNum = rowSize - entry.getValue();
            min = Math.min(min, acrossBricksNum);
        }
        return min;
    }
}
