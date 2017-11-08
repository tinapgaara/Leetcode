package google.tree.jiuzhang;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yingtan on 10/29/17.
 *
 * 145. Binary Tree Postorder Traversal
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, return the postorder traversal of its nodes' values.

 For example:
 Given binary tree {1,#,2,3},
 1
 \
 2
 /
 3
 return [3,2,1].
 */
public class PostorderTraversal {

        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res= new ArrayList<Integer>();
            if (root == null) return res;
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            while(! stack.isEmpty()) {
                TreeNode cur = stack.peek();
                if (cur.left == null && cur.right == null) {
                    res.add(stack.pop().val);
                }
                else {
                    if (cur.right != null) {
                        stack.push(cur.right);
                        // important !!!
                        cur.right = null;
                    }
                    if (cur.left != null) {
                        stack.push(cur.left);
                        // important !
                        cur.left = null;
                    }
                }
            }
            return res;
        }
}
