package interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import interval.Interval;

/**
 * Created by yingtan on 9/23/15.
 */
public class MeetRooms {

    public boolean canAttendMeetings(Interval[] intervals) {
        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);
        Interval prev  = null;
        for (int i = 0 ; i < intervals.length; i ++) {
            Interval cur = intervals[i];
            if (prev != null) {
                if (prev.end > cur.start) {
                    return false;
                }
            }
            prev = cur;
        }
        return true;
    }

    public int minMeetingRooms(Interval[] intervals) {
        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);

        if ((intervals == null) || (intervals.length == 0)) return 0;
        List<Integer> endTimes = new ArrayList<Integer>();
        for (int i = 0 ; i < intervals.length ; i ++) {
            int time = intervals[i].start;
            int index = findCompatibleActivity(endTimes, time);
            if (index != -1) {
                // merge this interval with finded compatible interval
                // if a new interval want to merge with it, its start time need to be larger than this                  //  interval's end time
                endTimes.set(index, intervals[i].end);
            }
            else {
                endTimes.add(intervals[i].end);
            }
        }
        return endTimes.size();

    }

    public int findCompatibleActivity(List<Integer> endTimes, int time) {
        for (int i = 0 ; i < endTimes.size() ; i ++) {
            if (endTimes.get(i) <= time) {
                return i;
            }
        }
        return -1;
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
