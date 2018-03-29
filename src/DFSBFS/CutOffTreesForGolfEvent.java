package DFSBFS;

/**
 * Created by yingtan on 2/25/18.
 *
 * 675. Cut Off Trees for Golf Event
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

 0 represents the obstacle can't be reached.
 1 represents the ground can be walked through.
 The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

 You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

 You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

 Example 1:
 Input:
 [
 [1,2,3],
 [0,0,4],
 [7,6,5]
 ]
 Output: 6
 Example 2:
 Input:
 [
 [1,2,3],
 [0,0,0],
 [7,6,5]
 ]
 Output: -1
 Example 3:
 Input:
 [
 [2,3,4],
 [0,0,5],
 [8,7,6]
 ]
 Output: 6
 Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.

 // 题目十分容易误解, 不是说cut的路径只能从小到大
   1 2 1
   3 4 5
   6 7 8

   不是说1 -> 2 -> 4 increasing所以是可以的,  (这样算出来min是9步)
    而是说
    cut height = 4的tree之前,必须cut height = 3的tree (这样算出来min是10步   1->2(1), 2->3(2), 3->4->5(2), 5->6(3), 6->7->8(2), total=10)

 Step 1: 按照height排序point,
 Step 2: 看每两个point之间是否可达,且shortest path是多少
 */
import java.util.*;
public class CutOffTreesForGolfEvent {
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) {
            return 0;
        }
        PointComparator cmp = new PointComparator();
        PriorityQueue<int[]> points = new PriorityQueue<>(cmp);
        for (int i = 0; i < forest.size(); i ++) {
            List<Integer> tree = forest.get(i);
            for (int j = 0; j < tree.size(); j ++) {
                Integer height = tree.get(j);
                if (height != 0 && height != 1) {
                    points.offer(new int[]{i, j, height});
                }
            }
        }
        int[] minPoint = new int[2];
        int sum = 0;
        while(! points.isEmpty()) {
            int[] p = points.poll();
            int i = p[0];
            int j = p[1];
            int shortestDist = bfs_findShortestDist(forest, minPoint[0], minPoint[1], i, j);
            if (shortestDist == -1) {
                return -1;
            }
            sum = sum + shortestDist;
            minPoint = p;
        }
        return sum;
    }
    public int bfs_findShortestDist(List<List<Integer>> forest, int tree_i, int tree_j, int targetTree_i, int targetTree_j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{tree_i, tree_j, 0});
        boolean[][] vis = new boolean[forest.size()][forest.get(0).size()];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        System.out.println(tree_i + "," + tree_j + " to " + targetTree_i + "," + targetTree_j);
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int i = p[0];
            int j = p[1];
            int dist = p[2];
            if (i == targetTree_i && j == targetTree_j) {
                // reach dest
                return dist;
            }
            for (int[] dir : dirs) {
                int newi = i + dir[0];
                int newj = j + dir[1];
                if (newi >= 0 && newi < forest.size() && newj >= 0 && newj < forest.get(0).size()) {
                    if (! vis[newi][newj] ) {
                        if (forest.get(newi).get(newj) != 0) {
                            // can walk through
                            vis[newi][newj] = true;
                            queue.offer(new int[]{newi, newj, dist + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }
    public class PointComparator implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            return p1[2] - p2[2];
        }
    }
    public static void main(String[] args) {
        CutOffTreesForGolfEvent ob = new CutOffTreesForGolfEvent();
        List<Integer> f = new ArrayList<>();
        f.add(2);
        f.add(3);
        f.add(4);
        List<Integer> f2 = new ArrayList<>();
        f2.add(0);
        f2.add(0);
        f2.add(5);
        List<Integer> f3 = new ArrayList<>();
        f3.add(8);
        f3.add(7);
        f3.add(6);
        List<List<Integer>> l = new ArrayList<>();
        l.add(f);
        l.add(f2);
        l.add(f3);
        System.out.println(ob.cutOffTree(l));
    }
}
