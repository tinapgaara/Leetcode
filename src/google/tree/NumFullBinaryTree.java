package google.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 11/7/15.
 */
public class NumFullBinaryTree {

    public int countFullBinaryTree(int height) {
        return 0;
    }

    /*
    *
    * given number N which represents total number of leaves in
    tree. you need to generate all possible tree, such that each
    node in tree has zero child or two children. The return type
    should be a list of such kind of trees. Only tree structure
    matters, tree node doesn't have any value initially.
    My solution: N = 1 and N = 2 are base cases.
    For example, N = 3.
       1     1
      / \   / \
     1  1   1 1
    / \      / \
    1 1      1 1
    * */
    public int buildFullBinaryTrees(int leafNum) {
        // build from two children to a parent

        LinkedList<TreeNode> firstList = new LinkedList<>();
        for (int i = 0; i < leafNum; i++) {
            firstList.add(new TreeNode(1));
        }
        List<LinkedList<TreeNode>> res = new LinkedList<>();
        res.add(firstList);
        for (int i = 0 ; i < leafNum -1; i ++) {
            List<LinkedList<TreeNode>> levelRes = new LinkedList<>();
            for (LinkedList<TreeNode> list : res) {
                int first = 0;
                int second = 1;
                while (second < list.size()) {
                    int insertionIndex = first;

                    LinkedList<TreeNode> copy = new LinkedList<>(list);
                    TreeNode leftChild = copy.remove(first);
                    TreeNode rightChild = copy.remove(second-1);

                    TreeNode newParent = new TreeNode(1);
                    newParent.left = leftChild;
                    newParent.right = rightChild;

                    copy.add(insertionIndex, newParent);
                    first ++;
                    second ++;

                    levelRes.add(copy);
                }
            }
            res = levelRes;
        }

        return res.size();
        // return res;
    }

    public static void main(String[] args) {
        NumFullBinaryTree ob = new NumFullBinaryTree();
        System.out.println(ob.buildFullBinaryTrees(3));
    }
}
