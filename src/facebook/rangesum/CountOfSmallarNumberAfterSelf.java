package facebook.rangesum;

/**
 * Created by yingtan on 3/17/18.
 */
import java.util.*;
public class CountOfSmallarNumberAfterSelf {
    public class TreeNode {
        TreeNode left;
        TreeNode right;
        int selfcount;
        int smallercount;
        int val;

        public TreeNode(int val) {
            this.val = val;
            this.selfcount = 1;
            this.smallercount = 0;
        }
        // return smaller count
        public int insert(int num) {
            TreeNode cur = this;
            int cursmallerCount = 0;
            while(cur != null) {
                if (num < cur.val) {
                    cur.smallercount ++;
                    // insert left
                    if (cur.left != null) {
                        cur = cur.left;
                    }
                    else {
                        cur.left = new TreeNode(num);
                        break;
                    }
                }
                else if (num > cur.val) {
                    cursmallerCount = cursmallerCount + cur.smallercount + cur.selfcount;
                    // insert left
                    if (cur.right != null) {
                        cur = cur.right;
                    }
                    else {
                        cur.right = new TreeNode(num);
                        break;
                    }
                }
                else {
                    cursmallerCount = cursmallerCount + cur.selfcount;
                    cur.selfcount ++;
                    break;
                }
            }
            return cursmallerCount;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        Integer[] resarr = new Integer[nums.length];
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        resarr[nums.length - 1] = 0;
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        for (int i = nums.length - 2; i >= 0; i --) {
            int num = nums[i];
            int smallerCount = root.insert(num);
            resarr[i] = smallerCount;
        }
        return Arrays.asList(resarr);
    }

}
