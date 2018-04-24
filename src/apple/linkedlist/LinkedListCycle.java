package apple.linkedlist;

import java.util.ListIterator;

public class LinkedListCycle {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public boolean hasCycle(ListNode head) {
        if (head == null ) return false;
        ListNode slow = head;
        ListNode fast = head;
        while(slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
