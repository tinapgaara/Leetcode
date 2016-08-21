package google.tree;

import java.util.List;

/**
 * Created by yingtan on 11/5/15.
 */
public class MultiChildrenTreeLongestConsecutiveSequ {

    public class MultiTreeNode {
        public int val;
        public List<MultiTreeNode> nodes;
        public MultiTreeNode(int x) { val = x; }
    }


    /*
    * 多叉树，每个节点是⼀一个整数，求书中最⻓长递增路径⽐比如
        5,6,7,8,9
    *
    * */
    public int longestConsecutive(MultiTreeNode root) {
        // Pay attention !! must do left side and right side in main function
        if (root == null) return 0;

        int max = 1;
        for (MultiTreeNode node : root.nodes) {
            max = Math.max(max, recurLongestConsequence(root, node, 1));
        }
        return max;
    }

    public int recurLongestConsequence(MultiTreeNode parent, MultiTreeNode cur, int depth) {

        if (cur  == null) return depth;

        int parentVal = parent.val;
        int curVal = cur.val;
        int maxLen = depth;
        if (parentVal + 1 == curVal ) {
            for (MultiTreeNode child : cur.nodes) {
                maxLen = Math.max(maxLen, recurLongestConsequence(cur, child, depth + 1));
            }
        }
        else {
            for (MultiTreeNode child : cur.nodes) {
                maxLen = Math.max(maxLen,recurLongestConsequence(cur, child, 1) );
            }
        }
        return maxLen;
    }
}
