package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 */
public class SearchForARange {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if (nums == null || nums.length == 0) return res;
        int lowerBoundary = findFirstEquals(nums, target);
        int higherBoundary = findLastEquals(nums, target);

        res[0] = lowerBoundary;
        res[1] = higherBoundary;
        return res;
    }

    public int findFirstEquals(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            }
            else if (nums[mid] < target){
                low = mid  + 1;
            }
            else {
                high = mid;
            }
        }
        if (nums[low] == target) return low;
        if (nums[high] == target) return high;
        return -1;
    }

    public int findLastEquals(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            }
            else if (nums[mid] < target){
                low = mid  + 1;
            }
            else {
                low = mid;
            }
        }
        if (nums[high] == target) return high;
        if (nums[low] == target) return low;
        return -1;
    }

}
