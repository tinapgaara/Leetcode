package facebook.linkedlist;

import linkedlist.ListNode;

/**
 * Created by yingtan on 5/29/17.
 *
 * 234. Palindrome Linked List Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 101255
 Total Submissions: 313365
 Difficulty: Easy
 Contributor: LeetCode
 Given a singly linked list, determine if it is a palindrome.

 Follow up:
 Could you do it in O(n) time and O(1) space?
 */
public class PalindromLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        ListNode slowPrev = null;

        while (fast != null && fast.next != null) {
            slowPrev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if(slowPrev == null) {
            // only one node
            return true;
        }
        slowPrev.next = null;

        ListNode secondList = null;
        if (fast == null) {
            // even number
            secondList = slow;
        }
        else {
            // odd number
            secondList = slow.next;
        }

        secondList = reverse(secondList);

        ListNode newHead = head;

        ListNode newSecondHead = secondList;
        while (newHead != null && newSecondHead != null) {
            if (newHead.val != newSecondHead.val) return false;
            newHead = newHead.next;
            newSecondHead = newSecondHead.next;
        }
        return true;
    }

    public ListNode reverse(ListNode list) {
        ListNode prev = null;
        ListNode cur = list;
        ListNode next = null;

        while (cur != null) {
            next =  cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}


