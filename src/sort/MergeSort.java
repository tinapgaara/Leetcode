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
public class MergeSort {

    // time : O(nlogn)
    // space; not in place
    /*
    * Merge-Sort (int[] num, int low, int high) {
    * if (low < high) {
    *   int mid = (low + high) / 2
    *   Merge-Sort (num, low, mid)
    *   Merge-Sort (num, mid+1, high)
    *   Merge(num, low, mid, high)
    * }
    *
    * Merge (int[] num, int low, int mid, int high) {
    * int len1 = mid - low + 1
    * int len2 = high - mid
    * int[] left = new int[len1]
    * int[] right = new int[len2]
    * for (int i = 0 ; i < len1; i ++) left[i] = num[low + i]
    * for (int i = 0 ; i < len2; i ++) right[i] = num[mid + i]
    * int i = 0;
    * int j = 0;
    * for (int k = low; k <= high; k ++) {
    *   if (left[i] <= right[j]) {
    *       num[k] = left[i]
    *       i ++;
    *   }
    *   else {
    *       num[k] = right[j]
    *       j ++
    *   }
    * }
    * }
    * */

    public void mergeSortnew(int[] nums, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSortnew(nums, low, mid);
            mergeSortnew(nums, mid + 1, high);
            combine(nums, low, mid, high);
        }
    }
    public void combine(int[] nums, int low, int mid, int high) {
        int[] helper = new int[nums.length];
        for(int i = 0; i < nums.length; i ++) {
            helper[i] = nums[i];
        }
        int i = low;
        int j = mid + 1;
        int k = low;
        while(i <= mid && j <= high) {
            if (helper[i] < helper[j]) {
                nums[k] = helper[i];
                i ++;
            }
            else {
                nums[k] = helper[j];
                j ++;
            }
            k ++;
        }
        while(i <= mid) {
            nums[k] = helper[i];
            i ++;
            k ++;
        }
        while(j <= high) {
            nums[k] = helper[j];
            j ++;
            k ++;
        }
    }

    public void mergeSort(int[] num, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(num, low, mid);
            mergeSort(num, mid+1, high);
            merge(num, low, mid, high);
        }
    }
    public void merge(int[] num, int low, int mid, int high) {
        int leftLen = mid - low + 1;
        int rightLen = high - mid;
        int[] left = new int[leftLen + 1];
        int[] right = new int[rightLen + 1];

        for (int i = 0 ; i < leftLen; i ++) {
            left[i] = num[low + i]; // pay attention
        }
        for (int i = 0 ; i < rightLen; i ++) {
            right[i] = num[mid + i + 1]; // pay attention
        }

        int i = 0 ;
        int j = 0 ;
        left[leftLen] = Integer.MAX_VALUE; // in case i ++  or j ++ out of bound
        right[rightLen] = Integer.MAX_VALUE; // pay attention

        for (int k = low; k <= high; k ++) {
            if (left[i] <= right[j]) {
                num[k] = left[i];
                i ++;
            }
            else {
                num[k] = right[j];
                j ++;
            }
        }
    }
    /*  Leetcode: Sort List
    *   Sort a linked list in O(n log n) time using constant space complexity.
    * */
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
        frontIterator.next = null; // cut off the whole list to front and end: set tail to frontIterator as null

        ListNode frontHead = mergeSort(frontCopy, frontLen);
        ListNode endHead = mergeSort(endCopy, endLen);

        return merge(frontHead, endHead);
    }

    public ListNode merge(ListNode frontHead, ListNode endHead) {

        ListNode helper = new ListNode(0); // always keep helper a new list's head
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

    /*
    * Leetcode: Merge Intervals
    *
    * Given a collection of intervals, merge all overlapping intervals.

        For example,
        Given [1,3],[2,6],[8,10],[15,18],
        return [1,6],[8,10],[15,18].
    *
    * */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if ( (intervals == null) || (intervals.size() == 0) ) return res;
        if (intervals.size() == 1) return intervals;

        IntervalComparator comparator = new IntervalComparator();
        Collections.sort(intervals, comparator);

        Interval i1 = intervals.get(0);
        int j = 1;
        while (j < intervals.size()) {
            Interval i2 = intervals.get(j);
            if (overLap(i1, i2)) {
                i1 = mergeInterval(i1, i2);
            }
            else {
                res.add(i1);
                i1 = i2;
            }
            j ++;
        }
        if (i1 != null) {
            res.add(i1);
        }
        return res;
    }

    public boolean overLap(Interval i1, Interval i2) {
        if ( (i2.start <= i1.end) && (i2.end >= i1.start) ) return true;
        else return false;
    }

    public Interval mergeInterval(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }

    public class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) { // default : i2, i1
            if (i1.start > i2.start) return 1;
            else if (i1.start < i2.start) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        MergeSort ob = new MergeSort();
        int[] num = new int[]{3,5,1,4,6,0,-2,7,-1,0};
        ob.mergeSortnew(num, 0, num.length-1);
        for (int i = 0 ; i < num.length; i ++) {
            System.out.println(num[i]);
        }
    }
}
