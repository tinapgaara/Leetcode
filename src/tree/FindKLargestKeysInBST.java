package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 12/9/17.
 */
public class FindKLargestKeysInBST {

    public List<Integer> firstKLargestKeys(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        findKLargetsRecur(root, k, res);
        return res;
    }

    public void findKLargetsRecur(TreeNode cur, int k, List<Integer> res) {
        if (cur == null) return;
        if (res.size() < k) {
            findKLargetsRecur(cur.right, k, res);
            res.add(cur.val);
            findKLargetsRecur(cur.left, k, res);
        }
    }
}
