package facebook.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 5/27/17.
 *
 * 572. Subtree of Another Tree Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 6533
 Total Submissions: 15471
 Difficulty: Easy
 Contributors:
 xljob
 Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

 Example 1:
 Given tree s:

 3
 / \
 4   5
 / \
 1   2
 Given tree t:
 4
 / \
 1   2


 Return true, because t has the same structure and node values with a subtree of s.
 Example 2:
 Given tree s:

 3
 / \
 4   5
 / \
 1   2
 /
 0
 Given tree t:
 4
 / \
 1   2
 Return false.

 */
public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if ( (s != null) && (t != null) ) {

            // check if two tree looks the same
            if (s.val == t.val) {
                if (isSame(s.left, t.left) && isSame(s.right, t.right)) return true;
            }
            // Important !!! boundary case : [1, 1] [1]
            // if two tree are not the same, must check subtree
            if (isSubtree(s.left, t) || (isSubtree(s.right, t))) return true;
        }
        return false;
    }

    public boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if ( (s != null) && (t != null) ) {
            if (s.val == t.val) {
                if(isSame(s.left, t.left) && (isSame(s.right, t.right))) return true;
            }
        }
        return false;
    }
}

