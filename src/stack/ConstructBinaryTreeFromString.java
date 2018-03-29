package stack;

/**
 * Created by yingtan on 3/6/18.
 *
 * 536. Construct Binary Tree from String
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 You need to construct a binary tree from a string consisting of parenthesis and integers.

 The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

 You always start to construct the left child node of the parent first if it exists.

 Example:
 Input: "4(2(3)(1))(6(5))"
 Output: return the tree root node representing the following tree:

 4
 /   \
 2     6
 / \   /
 3   1 5
 Note:
 There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 An empty tree is represented by "" instead of "()".
 */

import tree.TreeNode;

import java.util.Stack;

public class ConstructBinaryTreeFromString {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = null;
        for (int i = 0 ; i < s.length(); i ++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                String str = s.charAt(i) + "";
                while(i+1 < s.length() && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9') {
                    str = str + s.charAt(i+1);
                    i ++;
                }
                cur = new TreeNode(Integer.parseInt(str));
            }
            else if (s.charAt(i) == '-') {
                String str = "";
                while(i+1 < s.length() && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9') {
                    str = str + s.charAt(i+1);
                    i ++;
                }
                cur = new TreeNode(-1 * Integer.parseInt(str));
            }
            else if (s.charAt(i) == '(') {
                stack.push(cur);
            }
            else {
                // ')'
                // already formed cur
                TreeNode peek = stack.pop();
                if (peek.left != null) {
                    peek.right = cur;
                }
                else {
                    peek.left = cur;
                }
                cur = peek;
            }
        }
        return cur;
    }
    public TreeNode recurBuild(String s, int[] index) {
        int i = index[0];
        if (i >= s.length()) return null;
        char ch = s.charAt(i);
        TreeNode cur;
        System.out.println(ch);
        // deal with - number
        if (ch =='-') {
            index[0] ++;
            ch = s.charAt(index[0]);
            cur = new TreeNode(-1 * (ch - '0'));
        }
        else {
            cur = new TreeNode((ch - '0'));
        }
        index[0] ++;
        if (index[0] >= s.length()) return cur; // for "4"
        // if the next ch is (, then, start building children
        // if the next ch is ), then return the cur
        char nextch = s.charAt(index[0]);
        if (nextch == ')') {
            index[0] ++;
            return cur;
        }
        else if (nextch == '(') {
            index[0] ++;
            cur.left = recurBuild(s, index);
            if (index[0]< s.length() && s.charAt(index[0]) == '(') {
                // has right tree
                index[0] ++;
                cur.right = recurBuild(s, index);
                index[0] ++;
            }
            else {
                // don't has right tree, only )
                index[0] ++; // skip )
            }
        }
        return cur;
    }
    public static void main(String[] args) {
        ConstructBinaryTreeFromString ob = new ConstructBinaryTreeFromString();
        ob.str2tree("4(2(3)(1))(6(5))");
    }
}
