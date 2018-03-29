package tree;

import java.util.HashMap;

/**
 * Created by yingtan on 12/7/17.
 *
 * 106. Construct Binary Tree from Inorder and Postorder BinaryTreeVerticalOrderTraversal
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given inorder and postorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.
 */
public class ConstrctBinaryTreeFromInorderAndPostorderTraversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer,Integer> map = new HashMap<>();
        if (inorder == null || postorder == null) return null;
        for (int i = 0 ; i < inorder.length; i ++) {
            map.put(inorder[i], i);
        }
        return recurBuild(inorder, 0, inorder.length - 1, postorder, postorder.length - 1, map);
    }
    public TreeNode recurBuild(int[] inorder, int low, int high, int[] postorder, int pIndex, HashMap<Integer, Integer> map) {
        if (low > high || pIndex < 0 ) return null;
        TreeNode cur = new TreeNode(postorder[pIndex]);
        int med = map.get(cur.val);
        int rightnum = high - med;
        cur.right = recurBuild(inorder, med + 1, high, postorder, pIndex - 1, map);
        cur.left = recurBuild(inorder, low, med - 1, postorder, pIndex -1 - rightnum, map);
        return cur;
    }
}
