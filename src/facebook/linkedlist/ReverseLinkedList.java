package facebook.linkedlist;

import linkedlist.ListNode;

/**
 * Created by yingtan on 5/20/17.
 *
 * 206. Reverse Linked List Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 219441
 Total Submissions: 490135
 Difficulty: Easy
 Contributor: LeetCode
 Reverse a singly linked list.
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
