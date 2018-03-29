package linkedin.linkedlist;

import linkedlist.ListNode;

/**
 * Created by yingtan on 11/19/17.
 *
 * 21. Merge Two Sorted Lists
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.


 */
public class MergeTwoSortedList {

    // Sol1: iteration
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if ( (l1 == null) && (l2 == null )) return null;
        ListNode helper = new ListNode(0);

        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode curNew = helper;

        while ( (cur1 != null)  && (cur2 != null) ) {
            if (cur1.val < cur2.val) {
                curNew.next = cur1;
                cur1 = cur1.next;
            } else {
                curNew.next = cur2;
                cur2 = cur2.next;
            }
            curNew = curNew.next;
        }

        if (cur1 != null) {
            curNew.next = cur1;
        }
        else {
            curNew.next = cur2;
        }
        return helper.next;
    }

    // Sol2 : recursion
    public ListNode mergeTwoListsRecur(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoListsRecur(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoListsRecur(l1, l2.next);
            return l2;
        }
    }

}
