package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 */
public class SearchSortedArrayUnknownLength {

    // for sorted array, even the length is unknow, we still can estimate the length range by testing 2^x - 1
    public int binarySearch(int[] nums, int target) {
        int x = 0;
        int tryIndex;
        while(true) {
            tryIndex = (1 << x) - 1;
            if (nums[tryIndex] == target) {
                return tryIndex;
            }
            else if (nums[tryIndex] > target) {
                break;
            }
            else {
                tryIndex ++;
            }
        }
        int low = (1 << (tryIndex - 1));
        int high = (1 << tryIndex) - 2;
        while(low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] < target) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
