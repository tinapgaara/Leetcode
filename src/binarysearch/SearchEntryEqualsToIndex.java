package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 */
public class SearchEntryEqualsToIndex {
    public int searchEntryEqualsToIndex(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == mid) {
                return mid;
            }
            else if (nums[mid] > mid) {
                high = mid - 1;
            }
            else {
                // nums[mid] < mid
                low = mid + 1;
            }
        }
        return -1;
    }
}
