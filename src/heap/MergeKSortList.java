package heap;


import linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 10/7/15.
 */
public class MergeKSortList {

    public class MergeKSortLists {

        public ListNode mergeKLists(ListNode[] lists) {
            ListNode root = new ListNode(0);
            if (lists == null || lists.length == 0)
                return null;

            ListNodeComparator comparator = new ListNodeComparator();
            PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, comparator);
            for (int i = 0; i < lists.length; i ++) queue.offer(lists[i]);

            ListNode helper = new ListNode(0);
            ListNode current = helper;
            while ( ! queue.isEmpty()) {
                ListNode node = queue.poll();
                current.next = node;
                current = current.next;
                if (current.next != null)
                    queue.offer(current.next);
            }
            return helper.next;
        }

        public class ListNodeComparator implements Comparator<ListNode> {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                if (n1.val > n2.val) {
                    return 1;
                }
                else if (n1.val < n2.val)
                    return -1;
                return 0;
            }

        }
    }
}
