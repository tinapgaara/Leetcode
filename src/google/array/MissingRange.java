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

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        if (nums == null) return res;
        int len = nums.length;
        if (len == 0) {
            addString(res, lower, upper);
            return res;
        }

        addString(res, lower, nums[0]-1);

        int i = 0, j = 1;
        while (j < len) {
            addString(res, nums[i]+1, nums[j]-1);
            i++;
            j++;
        }
        int lastVal = nums[len - 1];
        addString(res, lastVal+1, upper);

        return res;
    }

    public void addString(List<String> res, int start, int end) {
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
