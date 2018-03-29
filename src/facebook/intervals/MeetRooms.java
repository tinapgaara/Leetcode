package facebook.intervals;

import interval.Interval;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by yingtan on 5/20/17.
 *
 * 252. Meeting Rooms Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 29344
 Total Submissions: 62918
 Difficulty: Easy
 Contributor: LeetCode
 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

 For example,
 Given [[0, 30],[5, 10],[15, 20]],
 return false.

 */
public class MeetRooms {

    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        IntervalComparator comp = new IntervalComparator();
        Arrays.sort(intervals, comp);
        for (int i = 1; i < intervals.length; i ++) {
            if (overlap(intervals[i-1], intervals[i])) {
                return false;
            }
        }
        return true;
    }
    public class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            if (i1.start == i2.start) {
                return i1.end - i2.end;
            }
            else {
                return i1.start - i2.start;
            }
        }
    }
    public boolean overlap(Interval i1, Interval i2) {
        if (i1.start < i2.end && i2.start < i1.end) {
            return true;
        }
        else {
            return false;
        }
    }
}
