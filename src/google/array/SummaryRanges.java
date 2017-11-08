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
        List<String> res= new ArrayList<String>();
        if (nums == null || nums.length == 0)
            return res;
        if (nums.length == 1) {
            res.add("" + nums[0]);
            return res;
        }

        int start = 0;
        int i = 1;
        while (i < nums.length) {
            if (nums[i] != nums[i - 1] + 1) {
                if (start == (i-1)) {
                    res.add("" + nums[start]);
                }
                else if (start < (i-1)) {
                    res.add(nums[start] + "->" + nums[i-1]);
                }
                start = i;
            }
            i ++;
        }
        if (start == (i-1)) {
            res.add(nums[start] + "");
        }
        else if (start < (i-1)) {
            res.add(nums[start] + "->" + nums[i-1]);
        }
        return res;
    }
}
