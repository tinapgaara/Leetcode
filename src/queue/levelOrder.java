package queue;

import linkedlist.ListNode;
import tree.TreeNode;

import java.util.*;

/**
 * Created by yingtan on 9/19/15.
 */
public class levelOrder {

    // Solution 1: substract two linked list length
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> level = new LinkedList<Integer>();
        queue.offer(root);
        level.offer(1);

        int prevLevel = 1;
        List<Integer> prevList = new ArrayList<Integer>();
        List<Integer> curList = new ArrayList<Integer>();

        while( ! queue.isEmpty()) { // must be empty
            TreeNode prevNode = queue.poll();
            System.out.println(prevNode.val);
            int curLevel = level.poll();
            if (prevLevel != curLevel) {
                List<Integer> copyList = new ArrayList<Integer>(prevList);
                res.add(copyList);
                prevLevel = curLevel;
                prevList = new ArrayList<Integer>();
            }
            prevList.add(prevNode.val);

            TreeNode left = prevNode.left;
            TreeNode right = prevNode.right;
            if (left != null) {
                queue.offer(left);
                level.offer(curLevel + 1);
            }
            if (right != null) {
                queue.offer(right);
                level.offer(curLevel + 1);
            }
        }
        if (prevList.size() > 0) {
            res.add(prevList);
        }
        return res;
    }

    // Solution 2: make a cycle, and slow and fast pointer
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if ( (headA == null) || (headB == null) ) {
            return null;
        }

        ListNode tmpA = headA;
        while(tmpA.next != null) {
            tmpA = tmpA.next;
        }
        tmpA.next = headA;
        ListNode slow = headB;
        ListNode fast = headB;
        boolean ifIntersect = false;
        while ( (slow != null) && (fast != null) && (fast.next != null) ) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                ifIntersect = true;
                break;
            }
        }
        if (! ifIntersect) {
            tmpA.next = null;
            return null;
        }
        else {
            ListNode cur = headB;
            while ( (cur != null) && (slow != null) ) {
                if (cur == slow) {
                    tmpA.next = null;
                    return cur;
                }
                else {
                    cur = cur.next;
                    slow = slow.next;
                }
            }
            tmpA.next = null;
            return null;
        }
    }

    public static void reverseWords(char[] s) {
        if (s.length == 0) {
            return;
        }

        String str = new String(s);
        StringTokenizer tokenier = new StringTokenizer(str, " ");
        List<String> newstr = new ArrayList<String>();
        while(tokenier.hasMoreTokens()) {
            String tmp = tokenier.nextToken();
            newstr.add(tmp);
        }
        int len = newstr.size();
        String res = "";
        for (int i = len-1 ; i >0; i --) {
            res = res + newstr.get(i) + " ";
            System.out.println(res);
        }
        res = res  + newstr.get(0);
        System.out.println(res);
        s = res.toCharArray();
        System.out.println(s);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        //levelOrder.levelOrder(node);
        String s = "a b";
        char[] chs = s.toCharArray();
        levelOrder.reverseWords(chs);
    }
}
