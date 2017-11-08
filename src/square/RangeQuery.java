package square;

/**
 * Created by yingtan on 10/15/17.
 *
 * 写一个range query 用bst function整体就是写一个class, 实现range query, 用BST，写一堆function，tree   traversal， add，range query啥的链接: https://instant.1point3acres.com/thread/139740

 */
public class RangeQuery {

    TreeNode root;

    public class TreeNode {
        int sum;
        TreeNode left;
        TreeNode right;
        int start;
        int end;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public RangeQuery(int[] nums) {
        // construct a segment tree
        root = buildTree(nums, 0, nums.length - 1);
    }

    public TreeNode buildTree(int[] nums, int low, int high) {
        if (low > high) return null;
        if (low == high) {
            TreeNode leaf = new TreeNode(low, high);
            leaf.sum = nums[low];
            return leaf;
        }
        int med = low + (high - low) / 2;
        TreeNode left = buildTree(nums, low , med);
        TreeNode right = buildTree(nums, med + 1 , high);
        TreeNode cur = new TreeNode(low, high);
        cur.sum = left.sum + right.sum;
        cur.left = left;
        cur.right = right;
        return cur;
    }

    public void update(int i, int val) {
        updateTree(root, i, val);
    }

    public void updateTree(TreeNode cur, int index, int val) {
        // base case, this is leaf node
        if (cur.start == cur.end) {
            cur.sum = val;
            // important !!!
            return;
        }
        int med = cur.start + (cur.end - cur.start) / 2;
        if (index <= med) {
            // update left subtree
            updateTree(cur.left, index, val);
        }
        else {
            // update right subtree
            updateTree(cur.right, index, val);
        }
        // after updating subtrees, updateing the current tree
        cur.sum = cur.right.sum + cur.left.sum;
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    public int sumRange(TreeNode cur, int start, int end) {
        // base case, this is the leaf node, and leaf node boundary == [start, end]
        if (cur.start == start && cur.end == end) {
            return cur.sum;
        }
        else {
            int nodeMed = cur.start + (cur.end - cur.start)/ 2;
            // [cur.start...... nodeMed] [nodeMed+1 .... cur.end]
            if (end <= nodeMed) {
                // query range is on left children of node cur
                return sumRange(cur.left, start, end);
            }
            else if (start >= nodeMed + 1) {
                return sumRange(cur.right, start, end);
            }
            else {
                return sumRange(cur.left, start, nodeMed) + sumRange(cur.right, nodeMed + 1, end);
            }
        }
    }

    public static void main(String[] args) {
        RangeQuery ob = new RangeQuery(new int[]{1,3,5});
        System.out.println(ob.sumRange(0, 2));
        ob.update(0, 2);
    }
}
