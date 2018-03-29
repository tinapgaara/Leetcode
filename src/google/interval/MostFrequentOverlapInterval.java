package google.interval;

import interval.Interval;

import java.util.*;

/**
 * Created by yingtan on 11/26/15.
 * http://www.zrzahid.com/maximum-number-of-overlapping-intervals/
 *
 * Given a set of intervals, how do we find the maximum number of intervals overlapping
 * at any point of time.
 */
public class MostFrequentOverlapInterval {

    private List<Interval> maxOverLapIntervals(Interval[] intervals) {
        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);
        // sort intervals

        int max = 0;
        List<Interval> res = new ArrayList<>();
        // min heap
        PriorityQueue<Interval> queue = new PriorityQueue<>(new IntervalQueueComparator());
        for (Interval interval : intervals) {
            int start = interval.start;
            //int end = interval.end;

            if (! queue.isEmpty() && start > queue.peek().end) {
                // all intervals in queue are overlapped
                int size = queue.size();
                if (size > max) {
                    max = size;
                    res = new ArrayList<>();
                    List<Interval> tmp = new ArrayList<>();
                    while(! queue.isEmpty()) {
                        Interval i = queue.poll();
                        tmp.add(i);
                        res.add(i);
                    }
                    // push intervals back to queue
                    for (int i = 0 ; i < tmp.size(); i ++) {
                        if (tmp.get(i).end >= start) queue.offer(tmp.get(i));
                    }
                }
            }
            queue.offer(interval);
        }
        return res;
    }

    public Interval overLap(Interval[] intervals) {

        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);
        Interval i1 = intervals[0];
        Interval prev = i1; // keep track of merged interval
        int high = 1;
        int max = 1;
        Interval maxInterval = i1;
        int overlapTime = 1;

        while (high < intervals.length) {
            Interval i2 = intervals[high];
            if (overlap(i1, i2)) { // use intersect interval to overlap with current interval
                overlapTime ++;
                Interval intersect = intersect(i1, i2);
                if (overlapTime > max) {
                    max = overlapTime;
                    maxInterval = intersect;
                }
                prev = merge(i1, i2);
                i1 = intersect;
            }
            else { // if is not overlap, then choose merged interval to overlap with current interval
                overlapTime = 1;
                if (prev != i1) {
                    if (overlap(prev, i2)) {
                        overlapTime++;
                        Interval intersect = intersect(prev, i2);
                        prev = merge(prev, i2);
                        i1 = intersect;
                        if (overlapTime > max) {
                            max = overlapTime;
                            maxInterval = intersect;
                        }
                    } // not overlap, then current i2 is separate from previous merge interval, move pointer next
                    else {
                        prev = i2;
                        i1 = i2;
                    }
                } // current prev ==i1, which means move pointer next
                else {
                    prev = i2;
                    i1 = i2;
                }
            }
            high ++;
        }
        return maxInterval;
    }

    public Interval intersect (Interval i1, Interval i2) {
        return new Interval(Math.max(i1.start, i2.start) , Math.min(i1.end, i2.end));
    }

    public Interval merge(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }

    public boolean overlap(Interval i1, Interval i2) {
        if ((i1.start <= i2.end) && (i1.end >= i2.start)) {
            return true;
        }
        else
            return false;
    }

    public class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.start > i2.start) return 1;
            else if (i1.start < i2.start) return -1;
            else {
                if (i1.end < i2.end) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }
    }

    public class IntervalQueueComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.end < i2.end) {
                    return -1;
                }
                else {
                    return 1;
                }
        }
    }

    public static void main(String[] args) {
        Interval i1 = new Interval(0,10);
        Interval i2 = new Interval(5,7);
        Interval i3 = new Interval(3,4);
        Interval i4 = new Interval(5,7);
        Interval i5 = new Interval(9,10);
        Interval i6 = new Interval(5,8);

        Interval[] is = new Interval[]{i1,i2,i3,i4,i5, i6};
        MostFrequentOverlapInterval ob =new MostFrequentOverlapInterval();
        Interval i = ob.overLap(is);
        List<Interval> res = ob.maxOverLapIntervals(is);
        for (Interval ii : res) {
            System.out.println(ii.start + "," + ii.end);
        }
        System.out.println();
    }
}
