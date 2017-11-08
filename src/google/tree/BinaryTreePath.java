package google.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 2/26/17.
 *
 * 257. Binary Tree Paths
 */
public class BinaryTreePath {

    // aggregate down to top

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();

        List<List<Integer>> res2 = new ArrayList<List<Integer>>();
        if (root == null) return res;

        if ( (root.left == null) && (root.right == null) ) {
            res.add(root.val + "");
            return res;
        }
        if (root.right != null){
            List<String> rights = binaryTreePaths(root.right);
            for (String right : rights) {
                res.add(root.val + "->" + right);
            }
        }
        if (root.left != null) {
            List<String> lefts = binaryTreePaths(root.left);
            for (String left : lefts) {
                res.add(root.val + "->" + left);
            }
        }
        return res;
    }
}
