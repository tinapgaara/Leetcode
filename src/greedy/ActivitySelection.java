package greedy;

import interval.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by max2 on 10/8/15.
 */
public class ActivitySelection {

    // Solution 1: using recur
    public int maxNumOfCompatibleAcitivities(Interval[] activities, int n) {
        int finishedTime = 0;
        Arrays.sort(activities);

        return recurMaxNumOfCompatibleAcitivities(activities, finishedTime, 0, n);
    }
    public int recurMaxNumOfCompatibleAcitivities(Interval[] activities, int finishTime, int index, int n) {
        for (int i = index ; i < n; i ++) {
            int startTime = activities[i].start;
            if (startTime >= finishTime) {
                finishTime = activities[i].end;
                return (1 + recurMaxNumOfCompatibleAcitivities(activities, finishTime, i, n));
            }
        }
        return 0;
    }

    public class ActivityComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) { // default: (i2 , i1)  i2 is first, return 1
            if (i1.end > i2.end) return 1;
            else if (i1.end < i2.end) return -1;
            else return 0;
        }
    }

    // Solution 2: use while
    public int maxNumOfCompatibleAcitivities_2(Interval[] activities, int n) {
        int finishedTime = 0;
        int i = 0;
        int count = 0;
        while (i < n) {
            if (activities[i].start > finishedTime) {
                finishedTime = activities[i].end;
                count ++;
            }
            i ++;
        }
        return count;
    }
}
