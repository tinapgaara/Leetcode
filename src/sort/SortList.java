package sort;

import linkedlist.ListNode;

/**
 * Created by yingtan on 12/12/17.
 *
 * 148. Sort List
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Sort a linked list in O(n log n) time using constant space complexity.


 */
public class SortList {

    public ListNode sortList(ListNode head) {
        // important !!! base case
        if (head == null || head.next == null) return head;
        // firstly, find the mid pointer using slow pointer and fast pointer
        // when fast pointer reaches the end, slow pointer is at middle
        ListNode slow = head;
        ListNode fast = head;
        ListNode prevSlow = slow;
        while(fast != null && fast.next != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // split equally
        prevSlow.next = null;
        // combine
        return mergeTwoLists(sortList(head), sortList(slow));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode helper = new ListNode(Integer.MIN_VALUE);
        ListNode cur = helper;
        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }
            else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        while(l1 != null) {
            cur.next = l1;
            l1 = l1.next;
            cur = cur.next;
        }
        while(l2 != null) {
            cur.next = l2;
            l2 = l2.next;
            cur = cur.next;
        }
        return helper.next;
    }
}
