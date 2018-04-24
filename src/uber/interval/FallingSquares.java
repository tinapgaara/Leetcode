package uber.interval;

/**
 * Created by yingtan on 3/28/18.
 *
 * 699. Falling Squares
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 On an infinite number line (x-axis), we drop given squares in the order they are given.

 The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and sidelength positions[i][1].

 The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares. We wait for each square to stick before dropping the next.

 The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they touch (either the number line or another square). Squares dropped adjacent to each other will not stick together prematurely.


 Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped, after dropping squares represented by positions[0], positions[1], ..., positions[i].

 Example 1:
 Input: [[1, 2], [2, 3], [6, 1]]
 Output: [2, 5, 5]
 Explanation:

 After the first drop of positions[0] = [1, 2]:
 _aa
 _aa
 -------
 The maximum height of any square is 2.


 After the second drop of positions[1] = [2, 3]:
 __aaa
 __aaa
 __aaa
 _aa__
 _aa__
 --------------
 The maximum height of any square is 5.
 The larger square stays on top of the smaller square despite where its center
 of gravity is, because squares are infinitely sticky on their bottom edge.


 After the third drop of positions[1] = [6, 1]:
 __aaa
 __aaa
 __aaa
 _aa
 _aa___a
 --------------
 The maximum height of any square is still 5.

 Thus, we return an answer of [2, 5, 5].


 Example 2:
 Input: [[100, 100], [200, 100]]
 Output: [100, 100]
 Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfac
 */
import interval.Interval;

import java.net.InterfaceAddress;
import java.util.*;
public class FallingSquares {
    public class TreeNode {
        TreeNode left;
        TreeNode right;
        int start;
        int end;
        int height;
        public TreeNode(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions == null || positions.length == 0) return res;
        TreeNode root = new TreeNode(positions[0][0], positions[0][0] + positions[0][1], positions[0][1]);
        res.add(positions[0][1]);
        int max = positions[0][1];
        for (int i = 1; i < positions.length; i ++) {
            int[] pos = positions[i];
            int start = pos[0];
            int end = start + pos[1];
            int height = pos[1];
            int maxHeight = insert(root, start, end, height);
            max = Math.max(max, maxHeight);
            res.add(max);
        }
        return res;
    }
    public int insert(TreeNode cur, int start, int end, int height) {
        int res = 0;
        // if [start, end] less than root
        if (end <= cur.end) {
            if (end <= cur.start) {
                // not overlap
                if (cur.left != null) {
                    return insert(cur.left, start, end, height);
                }
                else {
                    cur.left = new TreeNode(start, end, height);
                    return cur.left.height;
                }
            }
            else {
                // overlap
                if (cur.left != null) {
                    return insert(cur.left, start, end, height);
                }
                else {
                    cur.left = new TreeNode(start, end, cur.height + height);
                    return cur.left.height;
                }
            }
        }
        else if (start >= cur.start) {
            if (start >= cur.end) {
                // not overlap
                if (cur.right != null) {
                    return insert(cur.right, start, end, height);
                }
                else {
                    cur.right = new TreeNode(start, end, height);
                    return cur.right.height;
                }
            }
            else {
                // overlap
                if (cur.right != null) {
                    return insert(cur.right, start, end, height);
                }
                else {
                    cur.right = new TreeNode(start, end, cur.height + height);
                    return cur.right.height;
                }
            }
        }
        else {
            //wrap [node]
            int max = 0;
            if (cur.right != null) {
                max = Math.max(max, insert(cur.right, start, end, height));
            }
            else {
                cur.right = new TreeNode(start, end, cur.height + height);
                max = Math.max(max,cur.right.height);
            }
            if (cur.left != null) {
                max = Math.max(max,insert(cur.left, start, end, height));
            }
            else {
                cur.left = new TreeNode(start, end, cur.height + height);
                max = Math.max(max, cur.left.height);
            }
            res = max;
        }
        return res;
    }
    public class EndComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] i1, int[] i2) {
            return i2[0] - i1[0];
        }
    }
    public class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] i1, int[] i2) {
            if (i1[0] != i2[0]) {
                return i1[0] - i2[0];
            }
            else {
                return i1[1] - i2[1];
            }
        }
    }
    public static void main(String[] args) {
        FallingSquares ob = new FallingSquares();
        int[][] s = {{9,7}, {1,9}, {3,1}};
        Set<int[]> set = new HashSet<>();
        set.add(new int[]{1,2});
        //System.out.println(set.contains(new int[]{1,2}));
        System.out.println(ob.fallingSquares(s));
    }
}
