package uber.interval;

import interval.Interval;

import java.util.List;

/**
 * Created by yingtan on 11/9/17.
 */
public class MaxChainInRide {

    private int maxChains(Interval[] intervals) {
        int maxEnd = -1;
        int maxChains = 0;
        int max = 1;
        for (Interval i : intervals) {
            if (i.start > maxEnd) {
                maxChains = Math.max(maxChains, max);
                max = 0;
                maxEnd = i.end;
            }
            else {
                max ++;
            }
            if (i.end > maxEnd) {
                maxEnd = i.end;
            }
        }

        maxChains = Math.max(maxChains, max);
        return maxChains;
    }
}
