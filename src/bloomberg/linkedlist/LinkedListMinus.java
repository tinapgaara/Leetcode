package bloomberg.linkedlist;

import linkedlist.ListNode;

import java.util.LinkedList;

/**
 * Created by yingtan on 11/13/15.
 */
/*
* 两个linked list做减法
* */
public class LinkedListMinus {

    public ListNode minus(ListNode head1, ListNode head2) {

        if (head1 == null) return head2;
        else if (head2 == null) return head1;

        // assume the head is the least important number in linkedlist
        int borrow = 0;
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        ListNode helper = new ListNode(0);
        ListNode resHead = helper;

        while ((cur1 != null) && (cur2 != null)) {
            int val1 = cur1.val;
            int val2 = cur2.val;

            int diff = val1 - val2 - borrow;
            if (diff < 0) {
                borrow = 1;
                diff = diff + 10;
            }
            helper.next = new ListNode(diff);

            helper = helper.next;
            cur1 = cur1.next;
            cur2=  cur2.next;
        }

        while (cur1 != null) {
            int diff = cur1.val - borrow;
            helper.next = new ListNode(diff);

            cur1 = cur1.next;
            helper = helper.next;

            borrow = 0;
        }
        while (cur2 != null) {
            int diff = cur2.val + borrow;
            helper.next = new ListNode(diff);

            cur2 = cur2.next;
            helper = helper.next;

            borrow = 0;
        }
        return resHead.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(8);
        n1.next = n2;
        n2.next = n3;

        ListNode n5 = new ListNode(2);
        ListNode n4 = new ListNode(1);
        n4.next = n5;

        LinkedListMinus ob = new LinkedListMinus();
        ListNode res = ob.minus(n1, n5);
        System.out.println("");

    }
}


