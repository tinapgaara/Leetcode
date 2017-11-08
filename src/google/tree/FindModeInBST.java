package google.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 2/26/17.
 *
 * 501. Find Mode in Binary Search Tree
 *
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 Both the left and right subtrees must also be binary search trees.
 For example:
 Given BST [1,null,2,2],
 1
 \
 2
 /
 2
 return [2]

 */
public class FindModeInBST {

    // inorder traverse

    // organize as a consecutive array, [0, 1, 1, 2, 2, 2, 3, 3], find max len of consecu arr

    Integer prev = null;
    Integer count = 0; // how many same elements
    Integer maxCount = 0;

    public int[] findMode(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        recurFind(root, res);

        int[] resArr = new int[res.size()];
        for (int i = 0; i < res.size(); i ++) resArr[i] = res.get(i);
        return resArr;

    }

    public void recurFind(TreeNode root, List<Integer> res) {
        if (root == null) return;
        recurFind(root.left, res);
        if ( (prev == null) || (root.val == prev.intValue()) ) {
            count ++;
        }
        else {
            count = 1;
        }
        if (count > maxCount) {
            maxCount = count;
            res.clear();
            res.add(root.val);
        }
        else if (count == maxCount) {
            res.add(root.val);
        }
        prev = root.val;
        recurFind(root.right, res);
    }
}
