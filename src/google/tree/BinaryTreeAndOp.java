package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/6/15.
 */
/*
*Binary Tree求and操作。例如：
 *         *         *
/ \       / \       / \
1 * and   * 0 =    * 0
 / \     / \      / \
 0 1     1 0      1 0


这道题的意思是，每个树包含中间node和leaf node，其中
leaf node可以是0或者1, internal node没有具体值，只是⽤用
于构成tree的结构。当两个tree求AND关系的时候，从root
开始，如果都是internal node，那么结果的数也是internal
node；如果其中⼀一个是leaf node并且值为1，那么结果为
另外⼀一棵树的相同位置的subtree；如果其中⼀一个节点是
leaf并且值为0，那么结果对应的位置也为leaf并且值也为0.
*
* */
public class BinaryTreeAndOp {

    public TreeNode AndOp(TreeNode root1, TreeNode root2) {

        if ((root1 == null) && (root2 == null)) return null;
        else if ((root1 == null) && (root2 != null)) return root2;
        else if ((root1 != null) && (root2 == null)) return root1;

        else {
            TreeNode curNode = null;
            if (root1.val == root2.val) { // 0, 0  1,1
                curNode = new TreeNode(root1.val);
                TreeNode left = AndOp(root1.left, root2.left);
                TreeNode right = AndOp(root1.right, root2.right);

                curNode.left = left;
                curNode.right = right;
            }
            else {
                if ((root1.val == 0) || (root2.val == 0)) { // *,0 ; 0,1
                    curNode = new TreeNode(0);
                }
                else { // * 1
                    curNode = new TreeNode('*');
                    TreeNode left = AndOp(root1.left, root2.left);
                    TreeNode right = AndOp(root1.right, root2.right);

                    curNode.left = left;
                    curNode.right = right;
                }
            }
            return curNode;
        }
    }
}
