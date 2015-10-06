package sort;

import interval.Interval;
import linkedlist.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    /* Leetcode: Insertion Sort List
    *  Sort a linked list using insertion sort.
    *   1) can not scan from back to front
    *   2) can not use num[j+1] = num[j], j --, can not walk back
    *  So, should create a new list, and copy ListNode to the new list
    * */

    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;

        ListNode newHead = new ListNode(head.val);
        ListNode helper = new ListNode(Integer.MIN_VALUE);
        helper.next = newHead;

        // should keep prev and cur pointer
        ListNode prev = helper;
        ListNode cur = helper.next;

        head = head.next;
        while (head != null) { // scan all elements need to be inserted
            int key = head.val;
            // If key > prev and key < cur, can copy and insert to new list
            if ( (key >= prev.val) && (key < cur.val) ) {
                ListNode copy = new ListNode(key);
                prev.next = copy;
                head = head.next; // prepare to insert the next element to new list
                copy.next = cur;

                prev = helper; //everytime when insert a new element, prev starts from first element in newList
                cur = prev.next; // cur follows prev
            } else {
                prev = prev.next;
                cur = cur.next;

                // deal with the last element, when cur pointer is NULL, and cur.val will return error.
                if ((prev != null) && (cur == null)) {
                    ListNode copy = new ListNode(key);
                    prev.next = copy;

                    prev = helper; // everytime when insert a new element, prev starts from first element in newList
                    cur = prev.next; // cur follows prev

                    head = head.next;
                }
            }
        }
        return helper.next;
    }

    /*
    * Leetcode:
    * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

        You may assume that the intervals were initially sorted according to their start times.

        Example 1:
        Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

        Example 2:
        Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

        This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
    *
    * */

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if  (intervals == null) return res;
        else if (intervals.size() == 0) {
            res.add(newInterval);
            return res;
        }
        if (newInterval == null) return intervals;

        int i = 0;
        Interval interval1 = intervals.get(0);
        Interval interval2 = newInterval;
        i ++;
        while (i < intervals.size()) {
            if (isOverLap(interval1, interval2)) {
                interval1 = mergeInterval(interval1, interval2);
                interval2 = intervals.get(i);
            }
            else {
                res.add(interval1);
                interval1 = intervals.get(i); // important, ensure interval2 is newInterval
            }
            i ++;
        }
        if ( (interval1 != null) && (interval2 != null) ) {
            if (isOverLap(interval1, interval2)) {
                res.add(mergeInterval(interval1, interval2));
            }
            else {
                res.add(interval1);
                res.add(interval2);
            }
        }

        IntervalComparator comparator = new IntervalComparator(); // Pay attention: Do not need <Interval> in intialization
        Collections.sort(res, comparator); // return ascending result
        return res;
    }

    public boolean isOverLap(Interval i1, Interval i2) {
        int low1 = i1.start;
        int high1 = i1.end;
        int low2 = i2.start;
        int high2 = i2.end;
        if ( (low2 <= high1) && (high2 >= low1) ) {
            return true;
        }
        else return false;
    }

    public Interval mergeInterval(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }

    public class IntervalComparator implements Comparator<Interval> { // Pay attention, must have <Interval>
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.start > i2.start) return 1; // Pay attention
            else if (i1.start < i2.start) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        InsertionSort ob = new InsertionSort();
        int[] num = new int[]{5,2,4,6,1,3};
        ob.insertSortDescend(num);

        Interval i1 = new Interval(1,5);
        Interval i2= new Interval(0,0);
        List<Interval> list = new ArrayList<>();
        list.add(i1);
        list.add(i2);
        ob.insert(list, i2);

    }
}
