package bloomberg;

import linkedlist.ListNode;

/**
 * Created by yingtan on 10/26/15.
 */
/*
* slow, fast pointer to find middle point
*
* reverse the second part of list
*
* compare second part and first part
*
* */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {

        if (head == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        ListNode slwoPrev = null;

        while ((fast != null) && (fast.next != null)) {
            slwoPrev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }


        if (slwoPrev == null) return true;
        slwoPrev.next = null;

        ListNode secondList = null;
        if (fast == null) {
            // even number
             secondList = slow;
        } else {
            // odd number, ignore middle node
             secondList = slow.next;
        }

        ListNode firstList = head;

        ListNode newList = reverseLinkedList(secondList);

        while ( (firstList != null) && (newList != null) ) {
            if (firstList.val != newList.val) return false;
            else {
                firstList = firstList.next;
                newList = newList.next;
            }
        }
        return true;
    }

    public ListNode reverseLinkedList(ListNode list) {
        ListNode prev = list;
        ListNode newHead = prev;
        if (prev.next != null) {
            ListNode cur = prev.next;


            while ((prev != null) && (cur != null)){
                ListNode nextNode = cur.next;
                cur.next = prev;

                prev = cur;
                cur = nextNode;
            }
        }
        newHead = prev;

        list.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        PalindromeLinkedList ob = new PalindromeLinkedList();
        ListNode node = new ListNode(2);
        ListNode node_2 = new ListNode(3);
        ListNode node_3 = new ListNode(3);
        ListNode node_4 = new ListNode(2);

        node.next = node_2;
        node_2.next = node_3;
        node_3.next = node_4;
        System.out.println(ob.isPalindrome(node));
    }
}
