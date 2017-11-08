package google.linkedlist;

import linkedlist.ListNode;

/**
 * Created by yingtan on 8/15/17.
 *
 * 369. Plus One Linked List
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

 You may assume the integer do not contain any leading zero, except the number 0 itself.

 The digits are stored such that the most significant digit is at the head of the list.

 Example:
 Input:
 1->2->3

 Output:
 1->2->4
 */
public class PlusOneLinkedList {

    public ListNode plusOne(ListNode head) {
        ListNode helper = reverse(head);

        ListNode cur = helper;
        ListNode prev = null;
        int carry = 1;
        while (cur != null) {
            prev = cur;
            int val = (cur.val + carry) % 10;
            carry = (cur.val + carry) / 10;

            cur.val = val;
            if (carry == 0) break;
            else cur = cur.next;
        }
        if (carry > 0) {
            prev.next = new ListNode(1);
        }
        return reverse(helper);
    }

    public ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
