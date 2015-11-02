package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/1/15.
 */
public class BSTTwoSum {

    // Solution 1: traverse the tree to an increasing list
    // use low and high two pointers

    // Solution 2: create two iterator
    // 1) return next smallest number in tree
    // 2) return next largest number in tree
    // use two pointers in two iterators

    public int numPairsOfTwoSum(TreeNode node, int sum) {
        int res = 0;
        BSTInorderDecreaseIterator decreaseIter = new BSTInorderDecreaseIterator(node);
        BSTInorderIncreaseIterator increaseIter = new BSTInorderIncreaseIterator(node);

        int lowVal = 0;
        int highVal = 0;
        if (increaseIter.hasNext()) {
            lowVal = increaseIter.next();
        }
        if (decreaseIter.hasNext()) {
            highVal = decreaseIter.next();
        }
        while (lowVal < highVal) {
            if (lowVal + highVal == sum) {
                System.out.println(lowVal + "," + highVal);
                res ++;
                if (increaseIter.hasNext()) {
                    lowVal = increaseIter.next();
                }
                else break;
                if (decreaseIter.hasNext()) {
                    highVal = decreaseIter.next();
                }
                else break;
            }
            else if (lowVal + highVal < sum) {
                if (increaseIter.hasNext()) {
                    lowVal = increaseIter.next();
                }
                else break;
            }
            else {
                if (decreaseIter.hasNext()) {
                    highVal = decreaseIter.next();
                }
                else break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BSTTwoSum ob = new BSTTwoSum();
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;

        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(3);
        n2.left = n4;
        n2.right = n5;

        TreeNode n6 = new TreeNode(5);
        TreeNode n7 = new TreeNode(7);
        n3.left = n6;
        n3.right = n7;

        System.out.println(ob.numPairsOfTwoSum(n1, 5));
    }
}
