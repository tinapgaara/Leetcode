package linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yingtan on 10/7/15.
 */
public class MergeKSortList {

    public ListNode mergeKLists(ListNode[] lists) {
        if ( (lists == null) || (lists.length == 0) ) return null;
        List<Integer> res = new ArrayList<Integer>();

        for (int i = 0 ; i < lists.length ; i ++) {
            ListNode node = lists[i];
            while (node != null) {
                res.add(node.val);
                node = node.next;
            }
        }
        if (res.size() == 0) return null;
        Collections.sort(res);
        ListNode head = new ListNode(res.get(0));
        ListNode cur = head;
        for (int i = 1; i < res.size(); i ++) {
            ListNode nextNode = new ListNode(res.get(i));
            cur.next = nextNode;
            cur = cur.next;

        }
        return head;
    }
}
