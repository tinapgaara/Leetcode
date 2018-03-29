package facebook.linkedlist;

import linkedlist.ListNode;

/**
 * Created by yingtan on 1/6/18.
 *
 * 25. Reverse Nodes in k-Group
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 You may not alter the values in the nodes, only nodes itself may be changed.

 Only constant memory is allowed.

 For example,
 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5


 */
public class ReverseNodesInKGroups {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        if (len < k) return head;
        int time = len / k;
        cur = head;
        ListNode curtail = null;
        ListNode prev = null;
        for (int i = 0; i < time; i++) {
            // reverse part of linkedlist
            ListNode prevtail = curtail;
            curtail = cur;// cur will become to the tail
            prev = null;
            for (int j = 0; j < k; j++) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;

            }
            if (prevtail != null) {
                System.out.println(prevtail.val);
                prevtail.next = prev;
            }
            else {
                // first time to reverse the first part
                head = prev;
            }
        }
        if (len % k != 0) {
            curtail.next = cur;
        }
        return head;
    }

    public ListNode reverse(ListNode cur, ListNode prev, int k) {
        prev = null;
        ListNode tail = cur;
        for (int i = 0; i < k; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;

        }
        //System.out.println("**********" + tail.val);
        return tail;
    }

    public void test(ListNode i) {
        ListNode j = new ListNode(2);
        i = j;
        return;
    }

    public static void main(String[] args) {
        ReverseNodesInKGroups ob = new ReverseNodesInKGroups();
        ListNode head = new ListNode(1);
        //ob.test(head);

        head.next = new ListNode(2);
        ob.reverseKGroup(head, 2);
    }
}