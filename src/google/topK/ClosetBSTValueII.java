package google.topK;

import tree.TreeNode;

import java.util.*;

/**
 * Created by yingtan on 1/19/17.
 *
 *
 * 272. Closest Binary Search Tree Value II   Add to List QuestionEditorial Solution  My Submissions
 Total Accepted: 13632
 Total Submissions: 36221
 Difficulty: Hard
 Contributors: Admin
 Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

 Note:
 Given target value is a floating point.
 You may assume k is always valid, that is: k ≤ total nodes.
 You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 Follow up:
 Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

 Show Hint
 Show Company Tags
 Show Tags
 Show Similar Problems


 */
public class ClosetBSTValueII {

    // Sol 1: PriorityQueue nlogn
     //在来看一种利用最大堆来解题的方法，堆里保存的一个差值diff和节点值的pair，我们中序遍历二叉树(也可以用其他遍历方法)，然后对于每个节点值都计算一下和目标值之差的绝对值，由于最大堆的性质，diff大的自动拍到最前面，我们维护k个pair，如果超过了k个，就把堆前面大的pair删掉，最后留下的k个pair，我们将pair中的节点值取出存入res中返回即可

    public class Node {
        double absDiff;
        int value;

        public Node(double absDiff, int value) {
            this.absDiff = absDiff;
            this.value = value;
        }
    }

    public class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            if (n1.absDiff > n2.absDiff) return 1;
            else if (n1.absDiff < n2.absDiff) return -1;
            else return 0;
        }
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        NodeComparator comparator = new NodeComparator();
        PriorityQueue<Node> queue = new PriorityQueue<Node>(comparator);

        recurNodes(root, target, queue);
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0 ; i < k ; i ++) {
            res.add(queue.poll().value);
        }
        return res;
    }

    public void recurNodes(TreeNode root, double target, PriorityQueue<Node> queue) {
        if (root == null) return;
        double absDiff = Math.abs(root.val - target);
        Node newNode = new Node(absDiff, root.val);
        queue.offer(newNode);

        recurNodes(root.left, target, queue);
        recurNodes(root.right, target, queue);
    }


    // Sol2 : using double linkedlist双向链表
    //还有一种解法是直接在中序遍历的过程中完成比较，当遍历到一个节点时，如果此时结果数组不到k个，我们直接将此节点值加入res中，如果该节点值和目标值的差值的绝对值小于res的首元素和目标值差值的绝对值，说明当前值更靠近目标值，则将首元素删除，末尾加上当前节点值，反之的话说明当前值比res中所有的值都更偏离目标值，由于中序遍历的特性，之后的值会更加的遍历，所以此时直接返回最终结果即可

    public List<Integer> closestKValues_2(TreeNode root, double target, int k) {
        LinkedList<Integer> list = new LinkedList<Integer>();// double linkedlist, tail stores the closet value to target
        inOrderRecur(root, target, k, list);
        return list;
    }
    // o(nlogn): inorder traversal
    /*

    */
    public void inOrderRecur(TreeNode root, double target, int k, LinkedList<Integer> list) {
        if (root == null) {
            return;
        }

        // left side
        inOrderRecur(root.left, target, k, list);


        if (list.size() == k) {
            Integer unClosetOne = list.getFirst();
            // add one more will need to elimintae
            if (Math.abs(root.val - target) < Math.abs(unClosetOne - target)) {
                // need to eliminate the first one
                list.removeFirst();
                list.addLast(root.val);
            }
        }
        else {
            list.addLast(root.val);
        }


        inOrderRecur(root.right, target, k, list);
    }

    // Sol 3: two stacks,
    //下面的这种方法用了两个栈，pre和suc，其中pre存小于目标值的数，suc存大于目标值的数，开始初始化pre和suc的时候，要分别将最接近目标值的稍小值和稍大值压入pre和suc，然后我们循环k次，每次比较pre和suc的栈顶元素，看谁更接近目标值，将其存入结果res中，然后更新取出元素的栈，依次类推直至取完k个数返回即可，参见代码如下：

    public List<Integer> closestKValues_best(TreeNode root, double target, int k) {
        Stack<TreeNode> smaller = new Stack<TreeNode>();
        Stack<TreeNode> larger = new Stack<TreeNode>();
        List<Integer> res = new ArrayList<Integer>();
        while(root != null) {
            if (root.val < target) {
                smaller.push(root);
                root = root.right;
            }
            else {
                larger.push(root);
                root = root.left;
            }
        }

        for (int i = 0; i < k; i ++) {
            // when i = 0 , must be the closet one
            if (larger.isEmpty() || (! smaller.isEmpty() && (Math.abs(smaller.peek().val - target) < Math.abs(larger.peek().val - target)))) {
                TreeNode cur = smaller.peek();
                res.add(cur.val);
                updateSmaller(smaller, cur);
            }
            else {
                TreeNode cur = larger.peek();
                res.add(cur.val);
                updateLarger(larger, cur);
            }

        }
        return res;
    }

    public void updateSmaller(Stack<TreeNode> smaller, TreeNode cur) {
        // find smaller ones
        smaller.pop();
        if(cur.left != null) {
            smaller.push(cur.left);
            cur = cur.left;
            while(cur.right != null) {
                // Important !!!!
                smaller.push(cur.right);
                cur = cur.right;
            }
        }
    }

    public void updateLarger(Stack<TreeNode> larger, TreeNode cur) {
        // find larger ones
        larger.pop();
        if(cur.right != null) {
            larger.push(cur.right);
            cur = cur.right;
            while(cur.left != null) {
                // Important !!!!
                larger.push(cur.left);
                cur = cur.left;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(5);
        root.left = left;
        root.right = right;

        ClosetBSTValueII ob = new ClosetBSTValueII();
        System.out.println(ob.closestKValues(root, 5, 2));

    }
}
