package linkedlist;

import java.util.List;

/**
 * Created by yingtan on 9/19/15.
 */
public class Reverse {

    // Solution 1: Iteration, use 3 pointers: prev, cur, next
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;

        while(cur != null) {
            next = cur.next;
            cur.next = prev;

            prev = cur;
            cur = next;
        }

        return prev;
    }

    // Solution 2: recursive
    public ListNode reverseList_2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode returnHead = null;
        ListNode[] res = recurReverseList(head, returnHead);
        return res[1];
    }
    public ListNode[] recurReverseList(ListNode head, ListNode returnHead) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        if (cur.next == null) {
            returnHead = cur;
            ListNode[] res = new ListNode[2];
            res[0] = cur;
            res[1] = returnHead;
            return res;
        }
        else {
            ListNode[] nextNodes = recurReverseList(cur.next, returnHead);
            nextNodes[0].next = cur;

            cur.next = null;
            ListNode[] res = new ListNode[2];
            res[0] = cur;
            res[1] = nextNodes[1];
            return res;
        }
    }

    public static void main(String[] args) {
        Reverse ob = new Reverse();
        ListNode node = new ListNode(1);
        ListNode node_2 = new ListNode(2);
        node.next = node_2;
        ob.reverseList_2(node);
    }
}
