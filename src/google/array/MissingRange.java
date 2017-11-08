package google.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/6/15.
 */
/*
* Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*
* */
public class MissingRange {

    public List<String> findMissingRanges_2(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        if(nums == null || nums.length == 0)  {
            if (lower < upper) {
                res.add(lower + "->" + upper);
            }
            else if (lower == upper) {
                res.add(lower + "");
            }
            return res;
        }
        // important: translate to long, in case int + 1 overflow
        /*
        [2147483647]
        0
        2147483647
        */
        long upperLong = (long)upper;
        long lowerLong = (long)lower;

        long missingCandidate = lowerLong;
        for (int i = 0 ; i < nums.length; i ++) {
            long num = (long)nums[i];
            if (num > missingCandidate) {
                // at least missingCandidate is really missing
                long missingRangeEnd = num - 1;
                if (missingRangeEnd == missingCandidate) {
                    res.add(missingCandidate + "");
                }
                else if (missingRangeEnd > missingCandidate) {
                    res.add(missingCandidate + "->" + missingRangeEnd);
                }
            }
            missingCandidate = num + 1;
        }
        // finally deal with tail case
        if (missingCandidate == upperLong) {
            res.add(missingCandidate + "");
        }
        else if (missingCandidate < upperLong) {
            res.add(missingCandidate + "->" + upperLong);
        }
        return res;
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        if (nums == null) return res;
        int len = nums.length;
        if (len == 0) {
            addString(res, (long)lower, (long)upper);
            return res;
        }

        addString(res, (long)lower, ((long)nums[0])-1);

        int i = 0, j = 1;
        while (j < len) {
            addString(res, ((long)nums[i])+1, ((long)nums[j])-1);
            i++;
            j++;
        }
        int lastVal = nums[len - 1];
        addString(res, ((long)lastVal)+1, (long)upper);

        return res;
    }

    public void addString(List<String> res, long start, long end) {
        String str = "";
        if (start < end) {
            str = str + start + "->" + end;
            res.add(str);
        }
        else if (start == end){
            str = str + start;
            res.add(str);
        }
        return;
    }
}
