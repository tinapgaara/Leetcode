package google.rangeSum;

/**
 * Created by yingtan on 11/12/17.
 */
public class CountOfLargerNumberAfterSelf {

    public class Node {
        Node left;
        Node right;
        int countLarger;
        int countSelf;
        int val;

        public Node (int countLarger, int countSelf, int val) {
            this.countLarger = countLarger;
            this.countSelf = countSelf;
            this.val = val;
        }
    }
    public Integer[] countLarger(int[] nums) {
        Integer[] res = new Integer[nums.length];
        Node root = null;
        for (int i = nums.length - 1; i >= 0; i --) {
            root = insert(nums[i], root, i, 0, res);
        }
        return res;
    }

    public Node insert(int num, Node root, int i, int largerCountSoFar, Integer[] res) {
        // base case
        if (root == null) {
            Node leaf = new Node(0, 1, num);
            res[i] = largerCountSoFar;
            return leaf;
        }
        else if (num == root.val) {
            root.countSelf ++;
            res[i] = largerCountSoFar + root.countLarger;
        }
        else if (num > root.val) {
            // insert right
            root.countLarger ++;
            root.right = insert(num, root.right, i, largerCountSoFar, res);
        }
        else {
            // insert left
            largerCountSoFar = largerCountSoFar + root.countLarger + root.countSelf;
            root.left = insert(num, root.left, i, largerCountSoFar, res);
        }
        return root;
    }

    public static void main(String[] args) {
        CountOfLargerNumberAfterSelf ob = new CountOfLargerNumberAfterSelf();
        Integer[] res = ob.countLarger(new int[]{1,4,2,5});
        for (int i = 0 ; i <res.length; i ++) {
            System.out.println(res[i]);
        }
    }
}
