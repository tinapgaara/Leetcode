package bloomberg;

import linkedlist.ListNode;

/**
 * Created by max2 on 10/16/15.
 */
/* Leetcode: Linked List Cycle II
* Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
*
* */
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while ((slow != null) && (fast != null) && (fast.next != null) ) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)  break;
        }

        if (( fast == null) || (fast.next == null)) return null; // Important !!! must judge if fast == null, cannot be slow == fase
        else {
            ListNode slow_2 = head;
            while ((slow_2 != null) && (slow != null)) {
                if (slow == slow_2) return slow; // Important!! must put judge == before move to the next
                slow = slow.next;
                slow_2 = slow_2.next;

            }
            return null;
        }
    }
}
