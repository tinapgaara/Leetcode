package DFSBFS;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 9/19/15.
 */
public class RightSideView {

    // Solution 1: BFS
    public List<Integer> rightSideView_BFS(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Queue<Integer> dist = new LinkedList<Integer>();
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();

        int prevDist = 1;

        dist.offer(1);
        nodes.offer(root);

        TreeNode prevNode = null;

        while( ! nodes.isEmpty()) {
            int curDist = dist.poll();
            TreeNode curNode = nodes.poll();

            if (curDist != prevDist) {
                res.add(prevNode.val);
                prevDist = curDist;
            }
            prevNode = curNode;
            TreeNode left = curNode.left;
            TreeNode right = curNode.right;

            if(left != null) {
                nodes.offer(left);
                dist.offer(curDist + 1);
            }
            if (right != null) {
                nodes.offer(right);
                dist.offer(curDist + 1);
            }
        }
        if (prevNode != null)
            res.add(prevNode.val);
        return res;
    }

    //Solution 2: DFS
    public List<Integer> rightSideView_DFS(TreeNode root) {

        List<Integer> res = new ArrayList<Integer>();
        int depth = 1;
        if(root == null)
            return res;
        return recurRightSide(root,res,depth);


    }
    public List<Integer> recurRightSide(TreeNode root, List<Integer> res,int depth)
    {
        if(res.size() == depth -1)
            res.add(root.val);

        if(root.right != null)
            res = recurRightSide(root.right,res,depth+1);
        if(root.left != null)
            res = recurRightSide(root.left,res,depth+1);

        return res;
    }
}
