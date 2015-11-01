package vmware;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yingtan on 10/28/15.
 */
public class PostOrderTreeTraversal {

    // Solution 1: use recursive
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;

        List<Integer> left = postorderTraversal(root.left);
        List<Integer> right = postorderTraversal(root.right);

        res.addAll(left);
        res.addAll(right);
        res.add(root.val);

        return res;
    }

    // Solution 2: use iterative way
    // first push right branch, and then push left branch
    public List<Integer> postorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root == null) return res;
        stack.push(root); // judge null
        TreeNode prev = null;
        while(!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            // go down the tree.
            //check if current node is leaf, if so, process it and pop stack,
            //otherwise, keep going down
            if(prev == null || prev.left == peek || prev.right == peek){
                //prev == null is the situation for the root node
                if(peek.left != null){
                    stack.push(peek.left);
                }else if(peek.right != null){
                    stack.push(peek.right);
                }else{
                    stack.pop();
                    res.add(peek.val);
                }
            }
            //go up the tree from left node
            //need to check if there is a right child
            //if yes, push it to stack
            //otherwise, process parent and pop stack
            else if (prev == peek.left){
                if (peek.right != null) {
                    stack.push(peek.right);
                }
                else {
                    res.add(stack.pop().val);
                }
            //go up the tree from right node
            //after coming back from right node, process parent node and pop stack.
            } else if  (prev == peek.right) {
                res.add(stack.pop().val);
            }
            prev = peek;
        }

        return res;
    }

    public static void main(String[] args) {
        PostOrderTreeTraversal ob = new PostOrderTreeTraversal();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n1.left = n2;
        ob.postorderTraversal_2(n1);
    }

}
