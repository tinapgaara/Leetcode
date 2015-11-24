package google.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/21/15.
 */
/*
*
*Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
* */
public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {

        List<String> ranges = new ArrayList<String>();
        if ((nums == null) || (nums.length == 0)) {
            return ranges;
        }
        if (nums.length == 1) {
            String range = nums[0] + "";
            ranges.add(range);
            return ranges;
        }
        int start = 0;
        int end = 0;
        int prevVal = nums[start];

        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] - prevVal == 1) {
                end ++;
            }
            else {
                if (start == end) {
                    ranges.add(start+"");
                }
                else {
                    ranges.add(start + "->" + end);
                }
                start = i;
                end = i;
            }
            prevVal = nums[i];
        }
        if (start == end) {
            ranges.add(start +"");
        }
        else if (start < end) {
            ranges.add(start + "->" + end);
        }
        return ranges;
    }
}
