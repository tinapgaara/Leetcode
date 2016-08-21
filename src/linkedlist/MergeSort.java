package linkedlist;

/**
 * Created by yingtan on 10/5/15.
 */
public class MergeSort {

    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode copy = head;
        while (copy != null) {
            len ++;
            copy = copy.next;
        }

        if (len == 0) return head;
        return mergeSort(head, len);
    }

    public ListNode mergeSort(ListNode head, int len) {

        if ((len == 1) && (head != null)) return head;

        int frontLen = len / 2;
        int endLen = len - len / 2; // can not write as len/2, must be len - len / 2

        ListNode frontCopy = head;
        ListNode frontIterator = frontCopy; // save frontCopyHead
        /*
        * For linkedlist, if iterate x rounds,
        * Then, the element will arrive at index = x elements (which iterates over x+1 elements)
        * So, should only iterate (x-1) rounds.
        * */
        for (int i = 0 ; i < frontLen - 1; i ++) {
            frontIterator = frontIterator.next; // IMPORTANT!!!!!, may count one more element, so should iterate only frontLen-1 rounds
        }
        ListNode endCopy = frontIterator.next;
        frontIterator.next = null; // set tail to frontIterator as null

        ListNode frontHead = mergeSort(frontCopy, frontLen);
        ListNode endHead = mergeSort(endCopy, endLen);

        return merge(frontHead, endHead);
    }

    public ListNode merge(ListNode frontHead, ListNode endHead) {

        ListNode helper = new ListNode(0);
        ListNode helperIterator = helper; // save helper Iterator

        while ((frontHead != null) && (endHead != null) && (helperIterator != null)) {
            int front = frontHead.val;
            int end = endHead.val;
            if (front <= end) {
                ListNode newNode = new ListNode(front);
                helperIterator.next = newNode;
                frontHead = frontHead.next;
            }
            else {
                ListNode newNode = new ListNode(end);
                helperIterator.next = newNode;
                endHead = endHead.next;
            }
            helperIterator = helperIterator.next;
        }
        if (frontHead != null) {
            helperIterator.next = frontHead;
        }
        else if (endHead != null) {
            helperIterator.next = endHead;
        }
        return helper.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(2);
        ListNode node_2 = new ListNode(1);
        node.next = node_2;
        MergeSort ob = new MergeSort();
        ob.sortList(node);
    }
}
