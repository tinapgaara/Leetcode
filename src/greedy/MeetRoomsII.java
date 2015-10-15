package greedy;

import interval.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 10/10/15.
 */
public class MeetRoomsII {
    /*
    * Leetcode: Meeting Rooms II
    *
    * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

        For example,
        Given [[0, 30],[5, 10],[15, 20]],
        return 2.
    *
    * */

    public int minMeetingRooms(Interval[] intervals) {
        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);

        if ((intervals == null) || (intervals.length == 0)) return 0;

        int numRooms = 1;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(intervals[0].end);

        for (int i = 1 ; i < intervals.length; i ++) {
            Interval interval = intervals[i];
            if (interval.start >= queue.peek()) { // compatible, can use same room
                queue.poll();
                queue.offer(interval.end);
            }
            else { // not compatible, need a new room
                numRooms ++;
                queue.offer(interval.end);
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
}
