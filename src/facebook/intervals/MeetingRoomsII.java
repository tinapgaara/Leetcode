package facebook.intervals;

import java.util.*;
import java.util.PriorityQueue;
import interval.Interval;
public class MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
        // min meeting room -> maximum overlaps so need at least these rooms
        if (intervals == null || intervals.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(0);
        IntervalComparator comp = new IntervalComparator();
        Arrays.sort(intervals, comp);
        int max = 0;
        // Solution 1: using min meeting room + greedy: if any meeting room finished eariest before mine, just occupy it
        // only used to calculate the count. The left queue will not store the maximum overlapped intervals, but count is the same
        for (Interval  i : intervals) {
            if (!queue.isEmpty() && i.start >= queue.peek()) {
                // find the first finished one and use its room
                queue.poll();// using if instead of while: greedy idea, always pop the eariest finished task and add this task to it: this make sure occupy min meeting room
            }
            queue.offer(i.end); // set this meeting room's end time here
        }
        return queue.size();
        /* Solution 2: using maximum overlap ideas. If want to find all intevals in maximum overlap, need to use this.
        for (Interval i : intervals) {
            while (! queue.isEmpty() && i.start >= queue.peek()) {
                queue.poll(); // get out all old events which are finished before this task starts
            }
            queue.offer(i.end);
            // if you keep polling all finished tasks, then the time you add the new event, is may not be the maximum count of overlap,
            // so we need to record max each time when we add event to queue.: slower
            // Another approach: don't pop all finished ones,
            max = Math.max(max, queue.size());
        }
        return max;
        */
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
}
