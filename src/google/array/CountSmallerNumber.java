package google.array;

import java.util.*;

/**
 * Created by yingtan on 11/8/15.
 */
/*
*
* You are given an integer array nums and you have to return a new counts array.
* The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
* */
public class CountSmallerNumber {

    public class TreeNode {
        public int val;
        public int count;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.count = 1;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if ((nums == null) || (nums.length == 0)) {
            return res;
        }
        int len = nums.length;
        res.add(0);
        TreeNode root = new TreeNode(nums[len - 1]);
        for (int i = len-2; i >= 0 ; i --) {
            res.add(insertNode(root,nums[i]));
        }
        Collections.reverse(res);
        return res;
    }

    public int insertNode(TreeNode root, int val) {
        return recurInsertNode(root, val, 0);
    }

    public int recurInsertNode(TreeNode root, int val, int smallerNum) {

        if (val <= root.val) { // important !!!!
            root.count ++;
            if (root.left == null) {
                root.left = new TreeNode(val);
                return smallerNum;
            }
            return recurInsertNode(root.left, val, smallerNum);
        }
        else {
            smallerNum = smallerNum + root.count;
            if (root.right == null) {
                root.right = new TreeNode(val);
                return smallerNum;
            }
            return recurInsertNode(root.right, val, smallerNum);
        }
    }


    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
        PriorComparator comparator = new PriorComparator();
        PriorityQueue<Integer> highQueue = new PriorityQueue<Integer>();
        PriorityQueue<Integer> lowQueue =
                new PriorityQueue<Integer>(comparator);
        ArrayList<Integer> res = new ArrayList<Integer>();

        if ((A == null) || (A.length == 0)) return res;
        res.add(0);
        for (int i = 1;  i < A.length; i ++) {
            if (A[i] > A[i-1]) {
                lowQueue.offer(A[i-1]);
                while ((!highQueue.isEmpty()) && (highQueue.peek() < A[i])) {
                    lowQueue.offer(highQueue.poll());
                }
            }
            else {
                highQueue.offer(A[i-1]);
                while ((!lowQueue.isEmpty()) && (lowQueue.peek() > A[i])) {
                    highQueue.offer(lowQueue.poll());
                }
            }
            res.add(lowQueue.size());
        }
        return res;
    }

    public class PriorComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            if (i1 > i2) {
                return -1;
            }
            else if (i1 < i2) {
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        CountSmallerNumber ob = new CountSmallerNumber();
        int[] nums = new int[]{1,2,7,8,5};
        System.out.println(ob.countOfSmallerNumberII(nums));
    }
}


