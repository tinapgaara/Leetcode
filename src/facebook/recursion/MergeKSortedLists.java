package facebook.recursion;

import linkedlist.ListNode;

/**
 * Created by yingtan on 2/13/18.
 *
 * 23. Merge k Sorted Lists
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.


 */
import java.util.*;
public class MergeKSortedLists {
    // Solution 1: heap
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null ) return null;
        /// Solution 2: priority Queue
        NodeComparator comp = new NodeComparator();
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(comp);
        for (ListNode node : lists) {
            if (node != null)
                queue.offer(node);
        }
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode prev = head;
        while(! queue.isEmpty()) {
            ListNode cur = queue.poll();
            prev.next = cur;
            if (cur.next != null) // important !!!!
                queue.offer(cur.next);
            prev = cur;
        }
        return head.next;
    }
    public class NodeComparator implements Comparator<ListNode> {
        public int compare(ListNode l1, ListNode l2) {
            return l1.val - l2.val;
        }
    }
    // Solution 2: merge sort
    public ListNode mergeKLists_mergsort(ListNode[] lists) {
        if (lists == null ) return null;
        return mergeSort(lists, 0, lists.length - 1);
    }
    public ListNode mergeSort(ListNode[] lists, int low, int high) {
        if (low > high) return null;
        if (low == high) return lists[low];
        int mid = low + (high - low) / 2;
        ListNode l1 = mergeSort(lists, low, mid);
        ListNode l2 = mergeSort(lists, mid + 1, high);
        return combine(l1, l2);
    }
    public ListNode combine(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l1.next = combine(l1.next, l2);
                return l1;
            }
            else {
                l2.next = combine(l1, l2.next);
                return l2;
            }
        }
        return null;
    }


}
