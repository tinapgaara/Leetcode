package bloomberg.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by max2 on 11/13/15.
 */
/*
* Given a binary tree, imagine yourself standing on the right side of it,
* return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
* */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        rightRecurSide(root, res, 0);

        return res;
    }

    public void rightRecurSide(TreeNode root, List<Integer> res, int depth) {
        if (root == null) return;

        if (res.size() == depth) {
            res.add(root.val);
        }
        rightRecurSide(root.right, res, depth +1);
        rightRecurSide(root.left, res,depth + 1);
    }
}
