package square;

import interval.Interval;

import java.util.*;

/**
 * Created by yingtan on 10/16/17.
 *
 * 每个人上班作息不一样，找可以一起开会的时间: find all overlap intervals fin ?????
 *
 * Given a set of busy time intervals of two people as in a calendar, find the free time intervals of both the people so as to arrange a new meeting
 input: increasing sequence of pair of numbers
 per1: (1,5) (10, 14) (19,20) (27,30)
 per2: (3,5) (12,15) (18, 21) (23, 24)
 ouput: (6,9) (16,17) (22,22) (25,26)
 https://www.careercup.com/question?id=5739192933941248

 */
public class MinMeetingRoomII {

    // given k people have n intervals, find common overlapped intervals ????

    // busy intervals -> try to find free intervals
    public List<Interval> findMeetingIntervals(Interval[] i1, Interval[] i2) {
        IntervalComparator comparator = new IntervalComparator();
        List<Interval> res = new ArrayList<>();
        Interval[] is = null ; //= merge(i1, i2);
        Arrays.sort(is, comparator);
        Queue<Integer> queue = new PriorityQueue<Integer>();
        queue.offer(is[0].end);
        for (int i = 1; i < is.length; i ++) {
            Interval cur = is[i];
            if (cur.start > queue.peek()) {
                // then is this a meeting time
                int end = queue.poll();
                Interval meetingTime = new Interval(end + 1, cur.start - 1);
                res.add(meetingTime);
            }
            queue.offer(cur.end);
        }
        return res;
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);
        Queue<Integer> queue = new PriorityQueue<Integer>();
        queue.offer(0);
        for (Interval i : intervals) {
            if (i.start >= queue.peek()) {
                queue.poll();
            }
            queue.offer(i.end);
        }
        return queue.size();
    }

    public class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if(i1.start != i2.start) {
                return i1.start - i2.start;
            }
            else {
                return i1.end - i2.end;
            }
        }
    }
}
