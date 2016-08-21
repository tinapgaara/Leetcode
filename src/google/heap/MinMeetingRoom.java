package google.heap;

import interval.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 11/21/15.
 */
/*
* Given an array of meeting time intervals consisting of start and end times
* [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
*
* */
public class MinMeetingRoom {

    public int minMeetingRooms(Interval[] intervals) {
        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);

        if ((intervals == null) || (intervals.length == 0)) return 0;

        int numRooms = 1;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(intervals[0].end);

        for (int i = 1; i < intervals.length; i ++) {
            Interval cur = intervals[i];
            if (cur.start >= queue.peek()) {
                queue.poll();
                queue.offer(cur.end);
            }
            else {
                numRooms ++;
                queue.offer(cur.end);
            }
        }
        return numRooms;
    }

    public class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) { // TODO ?????
            if (i1.start < i2.start) {
                return -1;
            }
            else if (i1.start > i2.start) {
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        MinMeetingRoom ob= new MinMeetingRoom();
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(7,10);
        intervals[1] = new Interval(2,4);
        intervals[2] = new Interval(8,9);
        ob.minMeetingRooms(intervals);
    }
}
