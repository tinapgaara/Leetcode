package bloomberg.linkedlist;

import linkedlist.ListNode;

/**
 * Created by yingtan on 11/13/15.
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;

            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return head;
    }
}
