package sort;

import linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 12/13/17.
 *
 * 23. Merge k Sorted Lists
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeSortedStreamLinkedList {

    public ListNode mergeKLists(ListNode[] lists) {
        // Sol1 : using heap nlogk
        ListNode helper = new ListNode(Integer.MIN_VALUE);
        ListNode cur = helper;
        ListNodeComparator comparator = new ListNodeComparator();
        PriorityQueue<ListNode> queue = new PriorityQueue<>(comparator);
        for (ListNode head : lists) {
            if (head != null) {
                queue.offer(head);
            }
        }
        while(! queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }
        return helper.next;
    }

     // sol 2: using recursion: nlogk
     public ListNode mergeKLists_mergsort(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
     }

     public ListNode merge(ListNode[] lists, int low, int high) {
         if (low > high) return null;
         if (low == high) return lists[low];
         int mid = low + (high - low) / 2;
         ListNode l1 = merge(lists, low, mid);
         ListNode l2 = merge(lists, mid + 1, high);
         return combine(l1, l2);
     }

    public ListNode combine(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        else if (l1 != null && l2 == null) return l1;
        else if (l1 == null && l2 != null) return l2;
        else {
            if (l1.val < l2.val) {
                l1.next = combine(l1.next, l2);
                return l1;
            }
            else {
                l2.next = combine(l1, l2.next);
                return l2;
            }
        }
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
