package iterator;

import interval.Interval;

import java.util.*;
/*
*
* input [4, 10, 12, 16, 20, 25, 30] : means step function
* ->
* [4, 10) -> 4
* [10, 12) -> 10
* [12, 16) -> 12
* [16, 20) -> 16
* [20, 25) -> 20
* [25, 30) -> 30
*
* input: [start, end, step_size]
*
*
*
*
* */
public class SampleStepIterator {
    Interval interval;
    Iterator<Integer> iter;
    int start;
    int end;
    int step;

    Integer cur = null;
    public SampleStepIterator(Iterator<Integer> iter, int start, int end, int step) {
        this.iter = iter;
        this.start = start;
        this.end = end;
        this.step = step;
        cur = null;
    }
    public int next() {
        return interval.start;
    }
    public boolean hasNext() {
        if (cur == null) {
            cur = start;
        }
        else {
            cur = cur + step;
            if (cur > end) return false;
        }
        if (interval != null) {
            if (cur < interval.end) {
                return true;
            }
            else {
                // loop the next available interval
                while(iter.hasNext()) {
                    Integer next = iter.next();
                    if (cur < next) {
                        interval = new Interval(interval.end, next);
                        return true;
                    }
                }
                return false;
            }
        }
        else {
            Integer prev = null;
            while(iter.hasNext()) {
                Integer next = iter.next();
                if (cur  < next) {
                    if (prev == null) {
                        interval = new Interval(Integer.MIN_VALUE, next);
                    }
                    else {
                        interval = new Interval(prev, next);
                    }
                    return true;
                }
                prev = next;
            }
            return false;
        }
    }
    public static void main(String[] args) {
        Integer[] arr = {4, 10, 12, 16, 20, 25, 30};
        List<Integer> list = Arrays.asList(arr);
        SampleStepIterator ob = new SampleStepIterator(list.iterator(), 5, 30, 5);
        while(ob.hasNext()) {
            System.out.println(ob.next());
        }
    }
}
