package facebook.binarySearch;

/**
 * Created by yingtan on 12/21/17.
 */
public class SearchInRoatedSortedArrayII {

    /*
    * __________  increase higher, increase lower
    * low      mid
    *
    * */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int mid = low + (high -low) /2;
            if (nums[low] < nums[mid]) {
                // increasing on left part
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid;
                }
                else {
                    low = mid + 1;
                }
            }
            else if (nums[low] > nums[mid]){
                if (nums[mid] <= target  && target <= nums[high]) {
                    low = mid;
                }
                else {
                    high = mid - 1;
                }
            }
            else {
                // nums[low] == nums[mid] Important case here !!!!
                // skip the same one
                low ++;
            }
        }
        if (nums[low] == target) return true;
        else if (nums[high] == target) return true;
        else return false;
    }
}
