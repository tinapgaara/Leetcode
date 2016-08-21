package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/30/15.
 */
public class ClosetKValueBST {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<Integer>();
        if ((root == null) || (k == 0)) return res;

        List<Integer> nodes = new ArrayList<Integer>();
        List<Double> diffs = traverseTree(root, target,nodes);

        nodes  = quicksort(diffs, 0, diffs.size()-1, nodes);
        for (int i = 0 ; i < k ; i ++) {
            res.add(nodes.get(i));
        }
        return res;
    }
    public List<Double> traverseTree(TreeNode root, double target, List<Integer> nodes) {
        List<Double> res = new ArrayList<Double>();
        if (root == null) return res;
        List<Double> leftDiffs = traverseTree(root.left, target, nodes);
        leftDiffs.add(Math.abs(root.val-target));

        nodes.add(root.val);

        List<Double> rightDiffs = traverseTree(root.right, target, nodes);
        leftDiffs.addAll(rightDiffs);

        return leftDiffs;
    }
    public List<Integer> quicksort(List<Double> diffs, int low, int high, List<Integer> nodes) {
        int i = low;
        int j = high;
        double midVal = diffs.get(( i + j ) / 2);

        while (i < j) {
            while (diffs.get(j) > midVal) {
                j --;
            }
            while (diffs.get(i) < midVal) {
                i ++;
            }
            if (i < j) {
                double tmp = diffs.get(i);
                diffs.set(i, diffs.get(j));
                diffs.set(j, tmp);

                int index = nodes.get(i);
                nodes.set(i,nodes.get(j));
                nodes.set(j,index);
                i ++;
                j --;
            }
            if (i < high) quicksort(diffs, i+1, high, nodes);
            if (j > low) quicksort(diffs, low, j -1, nodes);
        }
        return nodes;
    }
}
