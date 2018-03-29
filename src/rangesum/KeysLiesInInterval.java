package rangesum;

/**
 * Created by yingtan on 3/17/18.
 */

import interval.Interval;
import tree.TreeNode;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/*
* Find if a key lies between an interval[start, end] : using BST
Time complexity: o(h + m) where m is the number of keys lie in [start, end], h is height.
When very few keys lie in the range, this is a better approach than o(n)(n is total number of keys)

* */
public class KeysLiesInInterval {
    // 1. using the java api
    public Set<Integer> queryKeys(int[] nums, Interval interval) {
        TreeSet<Integer> bst = new TreeSet<>();
        for (int num : nums) {
            bst.add(num); // log(n)
        }
        return bst.subSet(interval.start, interval.end); // log(n)
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
