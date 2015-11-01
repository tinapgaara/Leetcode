package bloomberg;

import linkedlist.ListNode;

/**
 * Created by yingtan on 10/26/15.
 */
public class MergeLinkedList {

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
}
