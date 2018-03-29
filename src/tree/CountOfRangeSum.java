package tree;

/**
 * Created by yingtan on 12/12/17.
 */
public class CountOfRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        TreeNode root = new TreeNode(0);
        for(int i = 0 ; i < nums.length ; i++) {
            sum = sum + nums[i];
            //root.insert()
        }

        return -1;
    }

    public class TreeNode {
        public TreeNode left;
        public TreeNode right;
        int val;
        int selfcount;
        int smallercount;
        int largercount;

        public TreeNode(int val) {
            this.val = val;
            this.selfcount = 1;
        }
    }
}
