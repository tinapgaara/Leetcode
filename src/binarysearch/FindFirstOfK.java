package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 */
public class FindFirstOfK {
    // in sorted array
    public int findFirstK(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int med = low + (high - low) / 2;
            if (nums[med] < k) {
                low = med + 1;
            }
            else if (nums[med] > k) {
                high = med - 1;
            }
            else {
                high = med;
            }
        }
        if (nums[low] == k) {
            return low;
        }
        else if (nums[high] == k) {
            return high;
        }
        return -1;
    }
}
