package lyft.tree;
//Find the most frequent element in a binary search tree 不用任何数据结构，求大神指导
// left child <= root <= right child
import tree.TreeNode;

import java.util.*;
public class MostFrequentNumBinaryTree {
    public int mostFrequent(TreeNode root) {
        int[] max = new int[1];
        int[] count = new int[1];
        int[] maxnode = new int[1];
        mostFrequentRecur(root, count, maxnode, max);
        return max[0];
    }
    public int mostFrequent_iter(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int max = 0;
        Integer largest = null;
        int count = 0;
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
        while(! stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (largest == null) {
                largest = cur.val;
                count = 1;
            }
            else {
                if (cur.val == largest) {
                    count ++;
                }
                else {
                    max = Math.max(max, count);
                    largest = cur.val;
                    count = 1;
                }
            }
            if (cur.right != null) {
                cur  = cur.right;
                while(cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        max = Math.max(max, count);
        return max;
    }
    public void mostFrequentRecur(TreeNode root, int[] count, int[] maxNode, int[] max) { // [largestNum, countOfLargetsNum]
        if (root == null) return;
        mostFrequentRecur(root.left, count, maxNode, max);
        if (maxNode.length > 0 && root.val == maxNode[0]) {
            count[0] ++;
        }
        else {
            max[0] = Math.max(max[0], count[0]);
            count[0] = 1;
            maxNode[0] = root.val;
        }
        mostFrequentRecur(root.right, count, maxNode, max);
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode l11 = new TreeNode(3);
        TreeNode l12 = new TreeNode(3);
        TreeNode l21 = new TreeNode(1);
        TreeNode l22 = new TreeNode(3);
        TreeNode l23 = new TreeNode(3);
        TreeNode l24 = new TreeNode(5);
        root.left = l11;
        root.right = l12;
        l11.left = l21;
        l11.right = l22;
        l12.left = l23;
        l12.right = l24;
        MostFrequentNumBinaryTree ob = new MostFrequentNumBinaryTree();
        System.out.println(ob.mostFrequent_iter(root));
        System.out.println(ob.mostFrequent(root));
    }
}
