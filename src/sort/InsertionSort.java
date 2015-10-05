package sort;

import linkedlist.ListNode;

/**
 * Created by yingtan on 10/4/15.
 */
public class InsertionSort {
    /**
     * for j=2 to A.length
     *     key = A[j]
     *     // insert A[j] into the sorted sequence A[1,...,j-1]
     *     i = j - 1
     *     while i > 0 and A[i] > key
     *         A[i+1] = A[i]
     *         i = i -1
     *     A[i+1] = key
     */
//
    // space: in place
    // ascending nearly O(n^2)
    public void insertSortAscend(int[] num) {

        if ((num == null) || (num.length == 0)) return;
        int len = num.length;

        for (int i = 1; i < len; i ++) {
            int key = num[i];
            int j  = i - 1;
            while ( (j >= 0) && (num[j] > key) ) {
                num[j+1] = num[j];
                j --;
            }
            num[j + 1] = key;
        }
    }

    // descending  nearly O(n^2)
    public void insertSortDescend(int[] num) {

        if ((num == null) || (num.length == 0)) return;
        int len = num.length;

        for (int i = 1; i < len ; i ++) {
            int key = num[i];
            int j = i -1;
            while ( (j >= 0) && (num[j] < key) ) {
                num[j+1] = num[j];
                j --;
            }
            num[j+1] = key;
        }
    }

    // should create new list
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;

        ListNode newHead = new ListNode(head.val);
        ListNode helper = new ListNode(Integer.MIN_VALUE);
        helper.next = newHead;

        ListNode prev = helper;
        ListNode cur = helper.next;

        head = head.next;
        while (head != null) {
            int key = head.val;
            if ( (key >= prev.val) && (key < cur.val) ) {
                ListNode copy = new ListNode(key);
                prev.next = copy;
                head = head.next;
                copy.next = cur;

                prev = helper;
                cur = prev.next;
            } else {
                prev = prev.next;
                cur = cur.next;

                if ((prev != null) && (cur == null)) {
                    ListNode copy = new ListNode(key);
                    prev.next = copy;

                    prev = helper;
                    cur = prev.next;

                    head = head.next;
                }
            }
        }
        return helper.next;
    }

    public static void main(String[] args) {
        InsertionSort ob = new InsertionSort();
        int[] num = new int[]{5,2,4,6,1,3};
        ob.insertSortDescend(num);
    }
}
