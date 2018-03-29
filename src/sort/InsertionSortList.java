package sort;

import linkedlist.ListNode;

/**
 * Created by yingtan on 12/12/17.
 */
public class InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        if (head == null) return head;
        ListNode helper = new ListNode(Integer.MIN_VALUE);
        ListNode cur = head;
        // try to insert each node starting from cur
        while(cur != null) {
            ListNode tmp = helper;
            while(tmp.next != null && tmp.next.val < cur.val) {
                tmp = tmp.next;
            }
            ListNode next = cur.next;
            cur.next = tmp.next;
            tmp.next = cur;
            cur = next;

        }
        return helper.next;
    }
}
