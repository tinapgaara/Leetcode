package google.heapPriorityQueue;

import linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 8/19/17.
 * 23. Merge k Sorted Lists
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * A Better solution is to use Min Heap based solution which is discussed here for arrays.
 * Time complexity of this solution would be O(nk Log k)

 */
public class MergeKSortedList {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNodeComparator comparator = new ListNodeComparator();
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(comparator);

        for (int i = 0 ; i < lists.length; i ++ ) {
            if (lists[i] != null)
                queue.offer(lists[i]);
        }

        ListNode helper = new ListNode(0);
        ListNode cur = helper;
        while (! queue.isEmpty()) {
            ListNode top = queue.poll();
            cur.next = top;
            cur = cur.next;
            if (top.next != null) // important, or will report nullpointerexception
                queue.offer(top.next);
        }

        return helper.next;
    }

    public class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode l1, ListNode l2) {
            return (l1.val - l2.val);
        }
    }
}
