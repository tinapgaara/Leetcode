package lyft.interval;
import interval.Interval;

import java.util.*;
// 第一轮就是给很多interval 让你判断一个数是否在interval内，如果在 返回这个interval
public class LieInInterval {
    // binary search
    public Interval isNumInInteval(Interval[] intervals, int num) {
        IntervalComparator comp = new IntervalComparator();
        Arrays.sort(intervals, comp);
        int low = 0;
        int high = intervals.length - 1;
        while(low < high) {
            int mid = low + (high - low) / 2;
            Interval inter = intervals[mid];
            if (num >= inter.start && num <= inter.end) {
                return inter;
            }
            else if (num < inter.start) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return null;
    }
    public class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            if (i1.start == i2.start) {
                return i1.end - i2.end;
            } else {
                return i1.start - i2.start;
            }
        }
    }
    public static void main(String[] args) {
        Interval i1 = new Interval(0,3);
        Interval i2 = new Interval(10,11);
        Interval i3 = new Interval(2, 10);
        Interval i4 = new Interval(4, 7);
        Interval[] arr = {i1, i2, i3, i4};
        LieInInterval ob = new LieInInterval();
        Interval res = ob.isNumInInteval(arr, 5);
        System.out.println(res.start + "," + res.end);
    }
}
