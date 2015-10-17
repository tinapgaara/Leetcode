package bloomberg;

import linkedlist.ListNode;

/**
 * Created by max2 on 10/16/15.
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode node) {
        ListNode slow = node;
        ListNode fast = node;
        while ((slow != null) && (fast != null) && (fast.next != null) ) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }
}
