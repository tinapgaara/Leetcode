package google.binarysearch.jiuzhang;

/**
 * Created by yingtan on 10/29/17.
 */
public class SearchInRoatedtArrayII {

    public boolean search(int[] A, int target) {
        for (int i = 0; i < A.length; i ++) {
            if (A[i] == target) {
                return true;
            }
        }
        return false;
    }
    /*
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[start] < nums[mid]) {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                }
                else {
                    start = mid + 1;
                }
            }
            else if (nums[start] > nums[mid]){
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                }
                else {
                    end = mid - 1;
                }
            }
            // important !!!! here
            else {
                start ++;
            }
        }
        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }
        return false;
    }
    */


}
