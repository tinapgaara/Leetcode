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
        ListNode slow = head;
        ListNode fast = head;
        while(slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverse(slow);
        ListNode cur = head;
        while(slow != null && cur != null) {
            if (slow.val != cur.val) return false;
            slow = slow.next;
            cur = cur.next;
        }
        return true;
    }
    public ListNode reverse(ListNode cur) {
        ListNode prev = null;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}


