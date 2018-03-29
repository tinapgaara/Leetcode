package linkedin.practice;

import linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 11/22/17.
 */
public class MergeKSortedList {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNodeComparator comp = new ListNodeComparator();
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(comp);
        for (ListNode cur : lists) {
            queue.offer(cur);
        }
        ListNode helper = new ListNode(0);
        ListNode cur = helper;
        while(! queue.isEmpty()) {
            ListNode top = queue.poll();
            cur.next = top;
            cur = cur.next;
            // important !!!
            if (top.next != null) {
                queue.offer(top.next);
            }
        }
        return helper.next;
    }


    public class ListNodeComparator implements Comparator<ListNode> {
        public int compare(ListNode l1, ListNode l2) {
            return l1.val - l2.val;
        }
    }
}
