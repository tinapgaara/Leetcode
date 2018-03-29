package linkedin.interval;

import interval.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/28/17.
 */
public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<Interval>();
        for (Interval i : intervals) {
            if (newInterval == null || i.end < newInterval.start)
                result.add(i);
            else if (i.start > newInterval.end) {
                result.add(newInterval);
                result.add(i);
                newInterval = null;
            } else {
                newInterval.start = Math.min(newInterval.start, i.start);
                newInterval.end = Math.max(newInterval.end, i.end);
            }
        }
        if (newInterval != null)
            result.add(newInterval);
        return result;
    }

    /*
    * Add Interval
    * Get Total Coverage
    * */

    List<Interval> lists = new ArrayList<>();
    public void addInterval(int start, int end) {
        List<Interval> res = new ArrayList<>();
        Interval newInterval = new Interval(start, end);
        for (Interval interval : lists) {
            if (newInterval == null || newInterval.start > interval.end) {
                res.add(interval);
            }
            else if (newInterval.end < interval.start) {
                res.add(newInterval);
                res.add(interval);
                newInterval = null;

            }
            else {
                newInterval.start = Math.min(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            }
        }
        if (newInterval != null) {
            res.add(newInterval);
        }
        lists = res;
    }

    public List<Interval> getTotalCoverage() {
        return lists;
    }

    public static void main(String[] args) {
        InsertInterval ob = new InsertInterval();
        ob.addInterval(7,8);
        ob.addInterval(0,2);
        ob.addInterval(3,4);
        ob.addInterval(0,6);


        List<Interval> intervals = ob.getTotalCoverage();
        for (Interval in : intervals) {
            System.out.println("start:" + in.start + ", end:" + in.end);
        }
    }

}
