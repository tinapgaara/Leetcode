package tree;

import java.util.HashMap;

/**
 * Created by yingtan on 12/6/17.
 *
 *

 105. Construct Binary Tree from Preorder and Inorder BinaryTreeVerticalOrderTraversal
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given preorder and inorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    // better one, use hashmap to store the value -> index
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0 ; i < inorder.length; i ++) {
            map.put(inorder[i], i);
        }
        return recurBuildTree(preorder, 0, inorder, 0, inorder.length - 1, map);
    }

    public TreeNode recurBuildTree(int[] preorder, int plow, int[] inorder, int ilow, int ihigh, HashMap<Integer,Integer> map) {
        if (plow >= preorder.length || ilow > ihigh) return null;
        int rootVal = preorder[plow];
        TreeNode root = new TreeNode(rootVal);
        int med = map.get(rootVal);
        int leftNum = med - ilow + 1;
        root.left = recurBuildTree(preorder, plow + 1, inorder, ilow, med - 1, map);
        root.right = recurBuildTree(preorder, plow + leftNum, inorder, med + 1, ihigh, map);
        return root;
    }

    // time: o(n) * n
    public TreeNode recurBuildTree(int[] preorder, int plow, int[] inorder, int ilow, int ihigh) {
        if (plow >= preorder.length || ilow > ihigh) return null;
        int rootVal = preorder[plow];
        TreeNode root = new TreeNode(rootVal);
        for (int i = ilow; i <= ihigh; i ++) {
            if (inorder[i] == rootVal) {
                int leftNum = i - ilow + 1;
                root.left = recurBuildTree(preorder, plow + 1, inorder, ilow, i - 1);
                root.right = recurBuildTree(preorder, plow + leftNum, inorder, i + 1, ihigh);
                return root;
            }
        }
        return null;
    }
}
