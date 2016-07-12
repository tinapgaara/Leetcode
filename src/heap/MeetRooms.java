package heap;

import interval.Interval;

import java.util.*;

/**
 * Created by yingtan on 9/23/15.
 */
public class MeetRooms {

    /*
    * Leetcode: Meeting Rooms II
    *   Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
    *   find the minimum number of conference rooms required.

        For example,
        Given [[0, 30],[5, 10],[15, 20]],
        return 2.

    * */

    /*
    * 1) Firstly, sort the interval as start time
    * 2) Keep a minHeap, keep track of min end-time interval :
    * 3) If a activity's time < min end-time
    *       heap.insertKey()
    *   else {
    *       extract-min()
    *       insertKey() // and increaseKey
    *   }
    *
    *   Greedy Alg :
    *       choose the earlist ending activity, to see if current activity is compatible with it
    * */

    // Solution 1: use java's priority queue
    public int minMeetingRooms(Interval[] intervals) {
        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);

        if ((intervals == null) || (intervals.length == 0)) return 0;

        int numRooms = 1;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        queue.offer(intervals[0].end);
        for (int i = 1; i < intervals.length; i ++) {
            if (intervals[i].start < queue.peek()) {
                numRooms ++;
                queue.offer(intervals[i].end);
            }
            else {
                queue.poll();
                queue.offer(intervals[i].end);
            }
        }
        return numRooms;
    }

    // Solution 2: construct PriorityQueue, however can not pass some cases
    public int heapSize = 0;

    public int minMeetingRooms_2(Interval[] intervals) {
        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);

        if ((intervals == null) || (intervals.length == 0)) return 0;

        Interval[] newintervals = new Interval[1];
        newintervals[0] = intervals[0];
        int numRooms  = 1;

        buildMinHeap(newintervals);
        for (int i = 1; i < intervals.length; i ++) {
            if (intervals[i].start < getMin(newintervals).end) {
                numRooms ++;
                newintervals = insertKey(newintervals, intervals[i]);
            }
            else {
                extractMin(newintervals);
                newintervals = insertKey(newintervals, intervals[i]);
            }
        }
        return numRooms;
    }

    public class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.start > i2.start) {
                return 1;
            }
            else if (i1.start < i2.start) {
                return -1;
            }
            return 0;
        }
    }

    public void buildMinHeap(Interval[] intervals) {
        heapSize = intervals.length;
        for (int i = heapSize /2; i >=0 ; i --) {
            minHeap(intervals, i);
        }
    }

    public void minHeap(Interval[] intervals, int i) {
        int left = left(i);
        int right = right(i);
        int minNo;
        if ( (left < heapSize) && (intervals[left].end < intervals[i].end) ) {
            minNo = left;
        }
        else minNo = i;

        if ( (right < heapSize) && (intervals[right].end < intervals[minNo].end)) {
            minNo = right;
        }
        if (minNo != i) {
            Interval tmp = intervals[i];
            intervals[i] = intervals[minNo];
            intervals[minNo] = tmp;

            minHeap(intervals, minNo);
        }
    }

    public Interval extractMin(Interval[] intervals) {
        Interval res  = intervals[0];
        intervals[0] = intervals[heapSize -1];
        heapSize --;
        minHeap(intervals, 0);

        return res;
    }

    public Interval getMin(Interval[] intervals) {
        return intervals[0];
    }

    public Interval[] insertKey(Interval[] intervals, Interval interval) {
        Interval[] newInterval = new Interval[heapSize + 1];
        for (int i = 0 ; i < heapSize ; i ++) {
            newInterval[i] = intervals[i];
        }

        newInterval[heapSize] = new Interval(interval.start , Integer.MAX_VALUE);
        intervals = newInterval;
        decreaseKey(intervals, heapSize, interval.end);
        heapSize ++;

        return intervals;
    }

    public void decreaseKey(Interval[] intervals, int i, int newKey) {
        intervals[i].end = newKey;
        while ((i >= 0) && (intervals[i].end < intervals[parent(i)].end)) {
            int parent = parent(i);
            Interval tmp = intervals[parent];
            intervals[parent] = intervals[i];
            intervals[i] = tmp;

            i = parent;
        }
    }

    public int parent(int i) {
        return (i /2);
    }
    public int left(int i) {
        return 2 * i + 1;
    }
    public int right(int i) {
        return 2 * i + 2;
    }

    public static void main(String[] args) {
        MeetRooms ob= new MeetRooms();
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(1,5);
        intervals[1] = new Interval(8,9);
        intervals[2] = new Interval(8,9);
        ob.minMeetingRooms(intervals);
    }
}
