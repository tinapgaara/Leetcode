package tree;

import java.util.List;

/**
 * Created by yingtan on 12/9/17.
 */
public class UniqueBinarySearchTreePreorder {

    public TreeNode constructBSTFromPreorder(List<Integer> preorder) {
        int[] rootIndex = new int[1];
        return recurBuildValue(preorder, rootIndex, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    // preorder traversal, always get the first value as root, and keeping getting the next value as left subtree root,
    // until hit a value which is not in the range, this must be the right subtree's node, so return null
    public TreeNode recurBuildValue(List<Integer> preorder, int[] rootIndex, int low, int high) {
        int curRootIndex = rootIndex[0];
        if (curRootIndex >= preorder.size()) return null;
        int curVal = preorder.get(curRootIndex);
        if (curVal < low || curVal > high) {
            return null;
        }
        TreeNode cur = new TreeNode(curVal);
        rootIndex[0] ++;
        cur.left = recurBuildValue(preorder, rootIndex, low, cur.val);
        cur.right = recurBuildValue(preorder, rootIndex, cur.val, high);
        return cur;
    }
}
