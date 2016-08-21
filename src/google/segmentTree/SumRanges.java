package google.segmentTree;

/**
 * Created by yingtan on 11/27/15.
 */
/*

http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/

*We have an array arr[0 . . . n-1]. We should be able to
1 Find the sum of elements from index l to r where 0 <= l <= r <= n-1 2
Change value of a specified element of the array arr[i] = x where 0 <= i <= n-1.

int getSum(node, l, r)
{
   if range of node is within l and r
        return value in node
   else if range of node is completely outside l and r
        return 0
   else
    return getSum(node's left child, l, r) +
           getSum(node's right child, l, r)
}
*
* */
public class SumRanges {

    public class SegmentTreeNode {

        private int low;
        private int high;
        private int val;

        private SegmentTreeNode left;
        private SegmentTreeNode right;

        public SegmentTreeNode(int low, int high) {
            this.high = high;
            this.low = low;
        }

        public void addLeftChild(SegmentTreeNode left) {
            this.left = left;
        }

        public void addRightChild(SegmentTreeNode right) {
            this.right = right;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public int sumInRanges(int[] nums, int low, int high) {
        SegmentTreeNode root = buildSegmentTree(nums);
        System.out.println(recurSum(root, low, high));

        update(root, nums, low, -3);
        return (recurSum(root, low, high));

    }

    // Solution 1: use segmentTreeNode data structure
    public SegmentTreeNode buildSegmentTree(int[] nums) {
        int len = nums.length;
        SegmentTreeNode root = new SegmentTreeNode(0, nums.length-1);

        root = recurBuildSegmentTree(root, nums, 0, len -1, 0);
        return root;
    }

    public SegmentTreeNode recurBuildSegmentTree(SegmentTreeNode root,
                                                 int[] nums, int numsLow, int numsHigh, int curIndexInTree) {

        // if use(low, med) && (med+1, high), just need to judge low== high. Must be in the moment where low == high
        if (numsLow == numsHigh) {
            SegmentTreeNode leaf = new SegmentTreeNode(numsLow, numsHigh);
            leaf.setVal(nums[numsLow]);
            return leaf;
        }
        int med  = (numsLow + numsHigh) / 2;

        SegmentTreeNode curNode = new SegmentTreeNode(numsLow, numsHigh);

        SegmentTreeNode leftNode = recurBuildSegmentTree(curNode, nums, numsLow, med, 2*curIndexInTree+1);
        SegmentTreeNode rightNode = recurBuildSegmentTree(curNode, nums, med+1, numsHigh, 2*curIndexInTree+2);;

        int curVal = leftNode.val + rightNode.val;
        curNode.setVal(curVal);

        curNode.addLeftChild(leftNode);
        curNode.addRightChild(rightNode);

        return curNode;
    }

    public int recurSum(SegmentTreeNode root, int searchLow, int searchHigh) {
        if ((searchHigh < root.low) || (searchLow > root.high)) {
            return 0;
        }
        if ((searchLow <= root.low) && (searchHigh >= root.high)) {
            return root.val;
        }
        int leftSum = 0;
        int rightSum = 0;
        if (root.left != null) {
            leftSum = recurSum(root.left, searchLow, searchHigh);
        }
        if (root.right != null) {
            rightSum = recurSum(root.right, searchLow, searchHigh);
        }
        return leftSum + rightSum;
    }

    public void update(SegmentTreeNode root, int[] nums, int updateIndex, int newval) {
        int diff = newval - nums[updateIndex];
        nums[updateIndex] = newval;

        recurUpdate(root, diff, updateIndex);
    }

    public void recurUpdate(SegmentTreeNode root, int diff, int updateIndex) {
        if ((updateIndex > root.high) || (updateIndex < root.low)) {
            return;
        }
        root.setVal(root.val + diff);
        if (root.left != null) {
            recurUpdate(root.left, diff, updateIndex);
        }
        if (root.right != null) {
            recurUpdate(root.right, diff, updateIndex);
        }
    }

    // Approach 2: use array to represent a tree

    public int[] buildSegmentTree_2(int[] nums) {
        int len = nums.length;
        int nodeNum = (int)Math.pow(2, len) - 1;
        int[] res = new int[nodeNum];

        int rootNum = recurBuildSegmentTree(res, nums, 0, len -1, 0);
        return res;
    }

    public int recurBuildSegmentTree(int[] tree, int[] nums, int numsLow, int numsHigh, int curIndexInTree) {

        if (numsLow == numsHigh) { // if use(low, med) && (med+1, high), just need to judge low== high. Must be in the moment where low == high
            tree[curIndexInTree] = nums[numsLow]; // at this point, cur will never be out of boundary
            return tree[curIndexInTree];
        }
        int med  = (numsLow + numsHigh) / 2;
        tree[curIndexInTree] = recurBuildSegmentTree(tree, nums, numsLow, med, 2*curIndexInTree+1) // Imporatant !!! use [low, med]
                + recurBuildSegmentTree(tree, nums, med+1, numsHigh, 2*curIndexInTree+2); // Important !!!
        return tree[curIndexInTree];
    }

    public int recurSum(int[] tree, int curLow, int curHigh, int searchLow, int searchHigh, int curNodeIndex) {
        // curNodeIndex will never be out of bound
        // find a range in segment tree, where the range is smaller than current search range
        if ((curLow >= searchLow) && (curHigh <= searchHigh)) {
            return tree[curNodeIndex];
        }
        if ((searchLow > curHigh) || (searchHigh < curLow)) {
            return 0 ; // can not find
        }
        int med = (curLow + curHigh) / 2;
        return recurSum(tree, curLow, med, searchLow, searchHigh, 2 * curNodeIndex +1) +
                recurSum(tree, med+1, curHigh, searchLow, searchHigh, 2 * curNodeIndex +2);
    }


    public void update(int[] nums, int updateIndex, int newval, int[] tree) {
        int diff = newval - nums[updateIndex];
        nums[updateIndex] = newval;
        recurUpdate(tree, 0, nums.length-1, updateIndex, diff, 0);
    }

    public void recurUpdate(int[] tree, int numsLow, int numsHigh, int updateIndex, int diff, int curIndexInTree) {
        if ((updateIndex < numsLow) || (updateIndex > numsHigh)) {
            return;
        }
        tree[curIndexInTree]  = tree[curIndexInTree] + diff;
        if (numsLow == numsHigh) return; // for boundary case

        int med = (numsLow + numsHigh) / 2;
        recurUpdate(tree, numsLow, med, updateIndex, diff, 2*curIndexInTree + 1);
        recurUpdate(tree, med+1, numsHigh, updateIndex, diff, 2*curIndexInTree + 2);
    }

    public static void main(String args[]) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        SumRanges ob = new SumRanges();
        System.out.println(ob.sumInRanges(arr, 0, 4));
    }
}
