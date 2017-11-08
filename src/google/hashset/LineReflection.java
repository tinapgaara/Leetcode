package google.hashset;

import java.util.HashSet;

/**
 * Created by yingtan on 8/29/17.
 *
 * 356. Line Reflection
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

 Example 1:
 Given points = [[1,1],[-1,1]], return true.

 Example 2:
 Given points = [[1,1],[-1,-1]], return false.

 Follow up:
 Could you do better than O(n2)?
 */
public class LineReflection {

    /*
    这道题给了我们一堆点，问我们存不存在一条平行于y轴的直线，使得所有的点关于该直线对称。
    题目中的提示给的相当充分，我们只要按照提示的步骤来做就可以解题了。
    首先我们找到所有点的横坐标的最大值和最小值，那么二者的平均值就是中间直线的横坐标，
    然后我们遍历每个点，如果都能找到直线对称的另一个点，则返回true，反之返回false
    */
    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<String> set = new HashSet<>();
        for(int[] p : points){
            max = Math.max(max , p[0]);
            min = Math.min(min , p[0]);
            String str = p[0] + "_" + p[1];
            set.add(str);
        }

        int sum = min + max;
        for(int[] p : points){
            int x = p[0];
            int y = p[1];
            int reflection_x = sum - x;
            String key = reflection_x + "_" + y;
            if (! set.contains(key)) return false;
        }
        return true;
    }
}
