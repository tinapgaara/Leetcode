package vmware;

import linkedlist.ListNode;

/**
 * Created by yingtan on 10/28/15.
 */
public class RemoveKthLastNode {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        for (int i= 0 ; i < n ; i ++) {
            if (second != null) {
                second = second.next;
            }
        }
        if (second == null) {
            return first.next;
        }

        ListNode prev = null;
        while ( (second != null)) {
            prev = first;
            first = first.next;

            second = second.next;
        }
        if ((prev != null) && (first != null)) {
            prev.next = first.next;
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveKthLastNode ob = new RemoveKthLastNode();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;

        ob.removeNthFromEnd(n1, 1);
    }
}
