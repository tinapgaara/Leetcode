package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 */
public class SearchRotatedArrayMinNumber {

    public int searchMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low ) /2;
            if (nums[mid] > nums[high]) {
                // min must exist after mid
                low = mid + 1;
            }
            else {
                // min must exist before/include mid
                high = mid;
            }
        }
        if (nums[low] < nums[high]) {
            return nums[low];
        }
        else {
            return nums[high];
        }
    }
}
