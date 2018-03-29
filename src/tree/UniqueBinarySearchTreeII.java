package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 12/9/17.
 */
public class UniqueBinarySearchTreeII {

    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }
    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start == end) {
            res.add(new TreeNode(start));
            return res;
        }
        if (start > end) return res;
        for (int i = start; i <= end; i ++) {
            List<TreeNode> lefts = generateTrees(start, i - 1);
            List<TreeNode> rights = generateTrees(i+1, end);
            if (lefts.size() > 0 && rights.size() > 0) {
                for (TreeNode left: lefts) {
                    for (TreeNode right : rights) {
                        // important !!! need to copy here
                        TreeNode cur = new TreeNode(i);
                        cur.left = left;
                        cur.right = right;
                        res.add(cur);
                    }
                }
            }
            else if (lefts.size() > 0) {
                for (TreeNode left : lefts) {
                    // important !!! need to copy here
                    TreeNode cur = new TreeNode(i);
                    cur.left = left;
                    res.add(cur);
                }
            }
            else if (rights.size() > 0) {
                for (TreeNode right : rights) {
                    // important !!! need to copy here
                    TreeNode cur = new TreeNode(i);
                    cur.right = right;
                    res.add(cur);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTreeII ob = new UniqueBinarySearchTreeII();
        ob.generateTrees(3);
    }
}
