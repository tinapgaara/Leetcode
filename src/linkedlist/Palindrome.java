package linkedlist;

import java.util.Stack;

/**
 * Created by yingtan on 8/25/15.
 */
public class Palindrome {

    // solution 1: reverse the linkedlist
    public boolean isPalindrome(ListNode head) {

        if (head == null) {
            return false;
        }

        ListNode cur = head;
        int len = 0;
        while(cur != null) {
            len ++;
            cur = cur.next;
        }

        if (len == 1) {
            return true;
        }

        cur = head;
        ListNode prev = null;
        ListNode nextNode = cur.next;

        if (len == 2) {
            if (cur.val == nextNode.val) {
                return true;
            }
            else
                return false;
        }

        int count = 0;
        cur.next = prev;

        while (count < ( (len - 1) / 2)) {

            prev = cur;
            cur = nextNode;
            nextNode = nextNode.next;

            cur.next = prev;
            count ++;
        }

        ListNode newMiddle = null;
        if ( (len % 2) != 0 ) {
            newMiddle = new ListNode(cur.val);
            newMiddle.next = nextNode;
        }
        else {
            newMiddle = nextNode;
        }

        while((cur != null) && (newMiddle != null) ) {
            System.out.println(cur.val + "," + newMiddle.val);
            if (cur.val != newMiddle.val) {
                return false;
            }
            cur = cur.next;
            newMiddle = newMiddle.next;
        }

        return true;
    }

    // Solution 2: use stack
    public boolean isPalindrome_2(ListNode head) {

        if (head == null) {
            return true;
        }
        Stack<Integer> stack = new Stack<Integer>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Integer number = stack.pop();
            if (cur.val != number.intValue()) {
                return false;
            }
            cur = cur.next;
        }
        return true;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node_1 = new ListNode(2);
        ListNode node_2 = new ListNode(3);
        head.next = node_1;
        node_1.next = node_2;

        Palindrome ob = new Palindrome();
        System.out.println(ob.isPalindrome(head));
    }
}
