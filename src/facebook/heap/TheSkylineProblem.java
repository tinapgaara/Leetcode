package facebook.heap;

/**
 * Created by yingtan on 2/19/18.
 * 218. The Skyline Problem
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
 The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

 For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

 The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

 For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

 Notes:
 The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 The input list is already sorted in ascending order by the left x position Li.
 The output list must be sorted by the x position.
 There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */
import java.util.*;
public class TheSkylineProblem {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0) return res;
        // need to sort queue from larger to smaller
        Queue<Integer> heights = new PriorityQueue<Integer>((a, b) -> (b - a));
        // need to sort list from smaller x to larger x
        List<int[]> points = new ArrayList<>();
        int prevHeight = -1;
        for (int[] building : buildings) {
            int x1 = building[0];
            int x2 = building[1];
            int height = building[2];
            points.add(new int[]{x1, height});
            points.add(new int[]{x2, -1 * height});
        }
        HeightComparator comp = new HeightComparator();
        Collections.sort(points, comp);
        heights.offer(0); // important !!! when queue is empty, we return x=0 as boundary
        for (int[] point : points) {
            int x = point[0];
            int h = point[1];
            if (h > 0) {
                heights.offer(h);
            }
            else {  // this is remove
                heights.remove(-1 * h); // o(n)
            }
            if (prevHeight != heights.peek()) {
                res.add(new int[]{x, heights.peek()});
                prevHeight = heights.peek();
            }
        }
        return res;
    }
    public class HeightComparator implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            if (p1[0] != p2[0]) {
                return p1[0] - p2[0];
            }
            else {
                return p2[1] - p1[1]; //important !!!!! firstly add height(>0), then remove height(<0), so sort reversely
            }
        }
    }
}
