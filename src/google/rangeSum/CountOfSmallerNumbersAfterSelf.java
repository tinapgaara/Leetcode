package google.rangeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 11/12/17.
 *
 * 315. Count of Smaller Numbers After Self
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 Example:

 Given nums = [5, 2, 6, 1]

 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.
 Return the array [2, 1, 1, 0].
 */
public class CountOfSmallerNumbersAfterSelf {

    public class Node {
        Node left;
        Node right;
        int countSmaller;
        int countSelf;
        int val;

        public Node(int countSmaller, int countSelf, int val) {
            this.countSmaller = countSmaller;
            this.countSelf = countSelf;
            this.val = val;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Node cur = null;
        Integer[] smallerArray = new Integer[nums.length];
        for (int i = nums.length - 1 ; i >= 0; i --) {
            cur = insert(cur, nums[i], smallerArray, i, 0);
        }
        return Arrays.asList(smallerArray);
    }

    public Node insert(Node cur, int num, Integer[] res, int i, int smallerNumber) {
        if (cur == null) {
            cur = new Node(0, 1, num);
            res[i] = smallerNumber;
        }
        else if (num == cur.val) {
            cur.countSelf ++;
            res[i] = smallerNumber + cur.countSmaller;
        }
        else if (num < cur.val) {
            // insert left
            cur.countSmaller ++;
            cur.left = insert(cur.left, num, res, i, smallerNumber);
        }
        else {
            // insert right;
            smallerNumber = smallerNumber + cur.countSmaller + cur.countSelf;
            cur.right = insert(cur.right, num, res, i, smallerNumber);
        }
        return cur;
    }

    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf ob = new CountOfSmallerNumbersAfterSelf();
        ob.countSmaller(new int[]{5,2,6,1});
    }
}
