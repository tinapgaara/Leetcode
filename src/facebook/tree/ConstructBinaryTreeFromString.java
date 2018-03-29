package facebook.tree;
//Input: "4(2(3)(1))(6(5))": preorder
import tree.TreeNode;

import java.util.*;
public class ConstructBinaryTreeFromString {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) return null;
        int[] index = new int[1];
        index[0] = 0;
        return iteration(s);
    }
    public TreeNode iteration(String s) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = null;
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            boolean isneg = false;
            if (c == '(') {
                stack.push(cur);
                continue;
            }
            else if (c == ')') {
                cur = stack.pop();
                continue;
            }
            else if (c == '-') {
                i ++;
                isneg = true;
            }
            int num = 0;
            while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + (s.charAt(i) - '0');
                i ++;
            }
            if (isneg) {
                num = num * -1;
            }
            if (i >= s.length()) {
                return new TreeNode(num);
            }
            i --;
            cur = new TreeNode(num);
            if (! stack.isEmpty()) {
                TreeNode peek = stack.peek();
                if (peek.left == null) {
                    peek.left = cur;
                }
                else if (peek.right == null) {
                    peek.right = cur;
                }
            }
        }
        return cur;
    }
    public static void main(String[] args) {
        ConstructBinaryTreeFromString ob = new ConstructBinaryTreeFromString();
        ob.str2tree("4(2(3))(6(5)(7))");
    }
}
