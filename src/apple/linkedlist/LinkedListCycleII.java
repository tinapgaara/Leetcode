package apple.linkedlist;

public class LinkedListCycleII {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    // assume  x -> cycle: (y, z)
    // assume they meet at y, z
    // slow pointer: x + y; faster pointer: x + y + z + y = x + 2y + z
    // use same time:
    // 1 * t = x + y
    // 2 * t = x + 2y + z
    // =>  2x + 2y = x + 2y + z  => x = z
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        // find out where they meet
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        while(slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (hasCycle) {
            slow = head;
            while(slow  != null && fast != null) {
                if (slow == fast) return slow;
                slow = slow.next;
                fast = fast.next;
            }
        }
        return null;
    }
}
