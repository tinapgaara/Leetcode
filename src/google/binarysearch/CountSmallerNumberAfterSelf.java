package google.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 8/5/17.
 */
public class CountSmallerNumberAfterSelf {

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> sorted = new ArrayList<Integer>();
        // smaller elements to the right of nums[i], need to scan from right to left so make sure when reach a num[i], all elements to the right of it already been scanned
        Integer[] index  = new Integer[nums.length];
        for (int i = nums.length - 1; i >= 0; i --) {
            int insertIndex = binarySearchIndex(sorted, nums[i]);
            index[i] = insertIndex;
            sorted.add(insertIndex, nums[i]);
        }
        return Arrays.asList(index);
    }

    public int binarySearchIndex(List<Integer> sorted, int num) {
        int low = 0;
        int high = sorted.size();


        while (low < high) {
            int mid = low + (high - low) / 2;
            if (sorted.get(mid) < num) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return high;
    }
}
