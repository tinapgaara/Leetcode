package lyft.interval;

import interval.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
*
* You are analyzing Lyft driver patterns over one day.
* You are given a list of active Lyft driver sessions in the format [{driver_id: int, start:<time>, end: <time>}, ...],
* write a function to find the hour when the most drivers were active.

example = [[br] {driver_id: 1, start: <you choose>, end: <you choose the format>},
...,

* */
public class MaxHoursActiveDrivers {
    public Interval maxOverlappedHour(Interval[] sessions) {
        // o(n)
        IntervalStartComparator comp = new IntervalStartComparator();
        Arrays.sort(sessions, comp);
        IntervalEndComparator comp2 = new IntervalEndComparator();
        PriorityQueue<Interval> queue = new PriorityQueue<>(comp2);
        int max = 0;
        Interval maxHour = null;
        for (Interval session : sessions) {
            if (queue.isEmpty()) {
                queue.offer(session);
            }
            else {
                while(! queue.isEmpty()) {
                    Interval top = queue.peek();
                    if (top.end < session.start) {
                        // can pop it up
                        queue.poll();
                    }
                    else {
                        break;
                    }
                }
                queue.offer(session);
                // then, all these intervals are overlapped, and can calculate maxcount and hours
                // the cur session is the latest start time, and the queue stored intervals based on endtime.
                // so the queue's peek one will have smallest end.
                // so, their overlap interval = [maxStart, minEnd]
                if (queue.size() >= max) {
                    max = queue.size();
                    maxHour = new Interval(session.start, queue.peek().end);
                }
            }
        }
        return maxHour;
    }
    public class IntervalStartComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            if (i1.start == i2.start) {
                return i1.end - i2.end;
            }
            return i1.start - i2.start;
        }
    }
    public class IntervalEndComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            if (i1.end == i2.end) {
                return i1.start - i2.start;
            }
            return i1.end - i2.end;
        }
    }
    public static void main(String[] args) {
        MaxHoursActiveDrivers ob = new MaxHoursActiveDrivers();
        Interval[] inters = new Interval[4];

        inters[0] = new Interval(2,7);
        inters[1] = new Interval(3,11);
        inters[2] = new Interval(4,10);
        inters[3] = new Interval(1,2);
        ob.maxOverlappedHour(inters);
    }
}
