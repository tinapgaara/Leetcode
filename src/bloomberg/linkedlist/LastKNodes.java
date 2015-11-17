package bloomberg.linkedlist;

import linkedlist.ListNode;

/**
 * Created by yingtan on 11/15/15.
 */
/*
* linkedlist输出倒数K个node的值,
* */
public class LastKNodes {

    public void printLastKNodes(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;

        for (int i = 0 ; i < k ; i ++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        while (slow != null) {
            System.out.println(slow.val);
            slow = slow.next;
        }
    }
}
