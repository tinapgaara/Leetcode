package tree;

import interval.Interval;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by yingtan on 12/10/17.
 */
public class QueryAKeyInARange {

    // 1. using the java api
    public Set<Integer> queryKeys(int[] nums, Interval interval) {
        TreeSet<Integer> bst = new TreeSet<>();
        for (int num : nums) {
            bst.add(num);
        }
        return bst.subSet(interval.start, interval.end);
    }

    // 2. using own implemented bst tree
    //  time complexity: o(h) + o(m)  where m is number of keys lie in the range
    public Set<Integer> queryKeysBST(int[] nums, Interval interval) {
        TreeNode root = null;
        Set<Integer> res = new HashSet<>();
        for (int num : nums) {
            // insert number to BST tree
        }
        // assume we already had a bst tree(balanced)
        recurQuery(interval, root, res);
        return res;
    }

    public void recurQuery(Interval interval, TreeNode cur, Set<Integer> res) {
        int start = interval.start;
        int end = interval.end;
        if (start > cur.val) {
            // search left side
            recurQuery(interval, cur.left, res);
        }
        else if (end < cur.val) {
            // search right side
            recurQuery(interval, cur.right, res);
        }
        else if (start <= cur.val && cur.val <= end) {
            // this need to be added to result
            res.add(cur.val);
            // in range, need to query left and right
            recurQuery(interval, cur.left, res);
            recurQuery(interval, cur.right, res);
        }
    }


}
