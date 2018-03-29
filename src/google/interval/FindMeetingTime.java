package google.interval;

import interval.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yingtan on 11/11/17.
 *
 *  * 每个人上班作息不一样，找可以一起开会的时间: find all overlap intervals fin ?????
 *
 * Given a set of busy time intervals of two people as in a calendar, find the free time intervals of both the people so as to arrange a new meeting
 input: increasing sequence of pair of numbers
 per1: (1,5) (10, 14) (19,20) (27,30)
 per2: (3,5) (12,15) (18, 21) (23, 24)
 ouput: (6,9) (16,17) (22,22) (25,26)
 */
public class FindMeetingTime {
    // exactly same as merge interval

    public List<Interval> findMeetingTime(List<Interval> p1, List<Interval> p2) {
        List<Interval> busyTimes = new ArrayList<>();
        if (p1 == null || p2 == null) return busyTimes;
        p1.addAll(p2);
        IntervalComparator comp = new IntervalComparator();
        Collections.sort(p1, comp);
        Interval prev = p1.get(0);
        for(int i = 1 ; i < p1.size(); i ++) {
            Interval cur = p1.get(i);
            if (! overlap(prev, cur)) {
                busyTimes.add(prev);
                prev = cur;
            }
            else {
                prev = new Interval(Math.min(prev.start, cur.start),
                        Math.max(prev.end, cur.end));

            }
        }
        busyTimes.add(prev);

        // find bu ji
        List<Interval> freeTimes = new ArrayList<>();
        for (int i = 0 ; i < busyTimes.size() - 1; i ++) {
            Interval cur = busyTimes.get(i);
            Interval next = busyTimes.get(i + 1);
            if (cur.end + 1 < next.start) {
                freeTimes.add(new Interval(cur.end + 1, next.start - 1));
            }
        }
        return freeTimes;
    }

    private boolean overlap(Interval i1, Interval i2) {
        if (i1.start <= i2.end && i2.start <= i1.end) {
            return true;
        }
        else {
            return false;
        }
    }

    public class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            if (i1.start != i2.start) {
                return i1.start - i2.start;
            }
            else {
                return i1.end - i2.end;
            }
        }
    }
}
