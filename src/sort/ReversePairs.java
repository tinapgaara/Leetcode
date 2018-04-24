package sort;

/**
 * Created by yingtan on 4/17/18.
 */
public class ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }
    public int mergeSort(int[] nums, int low, int high) {
        int mid = low + (high - low) / 2;
        int leftReverseCount = mergeSort(nums, low, mid);
        int rightReverseCount = mergeSort(nums, mid + 1, high);
        int mergeCount = combine(nums, low, mid, high);
        return leftReverseCount + rightReverseCount + mergeCount;
    }
    public int combine(int[] nums, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int[] tmp = new int[nums.length];
        int k = low;
        int count = 0;
        while(i <= mid && j <= high) {
            // i < j
            if (nums[i] <= nums[j]) {
                tmp[k] = nums[i];
                i ++;
            }
            else {
                // reverse pairs
                tmp[k] = nums[j];
                j ++;
                count = count + mid - i + 1;
            }
            k ++;
        }
        while(i <= mid) {
            tmp[k] = nums[i];
            i ++;
            k ++;
        }
        while(j <= high) {
            tmp[k] = nums[j];
            j ++;
            k ++;
        }
        for (i = low; i<= high; i ++) {
            nums[i] = tmp[i];
        }
        return count;
    }
}
