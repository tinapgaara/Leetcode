package google.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 10/28/17.
 *
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

 Two trees are duplicate if they have the same structure with same node values.

 Example 1:
 1
 / \
 2   3
 /   / \
 4   2   4
 /
 4
 The following are two duplicate subtrees:
 2
 /
 4
 and
 4
 Therefore, you need to return above trees' root in the form of a list.
 */
public class FindDuplicateSubtrees {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        Map<String, Integer> dupCount = new HashMap<String, Integer>();
        serialize(root, dupCount, res);
        return res;
    }

    public String serialize(TreeNode cur, Map<String, Integer> dupCount, List<TreeNode> res) {
        if (cur == null) {
            return "#";
        }
        // serialize, must use preorder
        String nodeStr =  cur.val + "," + serialize(cur.left, dupCount, res) +  serialize(cur.right, dupCount, res);
        if (dupCount.containsKey(nodeStr)) {
            if (dupCount.get(nodeStr) == 1) {
                res.add(cur);
            }
            dupCount.put(nodeStr, dupCount.get(nodeStr) + 1);
        }
        else {
            dupCount.put(nodeStr, 1);
        }
        return nodeStr;
    }
}
