package google.heapPriorityQueue;

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

这种方法先把所有的时间区间按照起始时间排序，然后新建一个最小堆，开始遍历时间区间，如果堆不为空，且首元素小于等于当前区间的起始时间，我们去掉堆中的首元素，把当前区间的结束时间压入堆，由于最小堆是小的在前面，那么假如首元素小于等于起始时间，说明上一个会议已经结束，可以用该会议室开始下一个会议了，所以不用分配新的会议室，遍历完成后堆中元素的个数即为需要的会议室的个数
*
* */
public class MinMeetingRoom {

    public int minMeetingRooms(Interval[] intervals) {
        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);

        if ((intervals == null) || (intervals.length == 0)) return 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(intervals[0].end);

        for (int i = 1; i < intervals.length; i ++) {
            Interval cur = intervals[i];
            if (cur.start >= queue.peek()) {
                queue.poll();
                queue.offer(cur.end);
            }
            queue.offer(cur.end);
        }
        return queue.size();
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
