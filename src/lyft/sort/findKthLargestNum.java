package lyft.sort;

/**
 * Created by yingtan on 4/11/18.
 */
public class findKthLargestNum {
    // quick-select
    // t(n) = 2 * t(n/2) + o(n) nlogn
    public int findKthLargetsNum_quickselect(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;
        while(low < high) {
            int pivot = partition(nums, low, high);
            if (pivot == k) {
                return nums[pivot];
            }
            else if (pivot < k) {
                low = pivot + 1;
            }
            else {
                high = pivot - 1;
            }
        }
        return -1;
    }
    public int partition(int[] nums, int low, int high) {
        int pivotIndex = high;
        int lowerPivot = low;
        for (int i = low; i< high ; i ++) {
            if (nums[i] < nums[pivotIndex]) {
                int tmp = nums[i];
                nums[i] = nums[lowerPivot];
                nums[lowerPivot] = tmp;
                lowerPivot ++;
            }
        }
        int tmp = nums[high];
        nums[high] = nums[lowerPivot];
        nums[lowerPivot] = tmp;
        return lowerPivot;
    }
}
