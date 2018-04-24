package lyft.sort;

/**
 * Created by yingtan on 4/11/18.
 */
public class MergeSort {
    public void mergeSort(int[] nums, int low, int high) {
        // t(n) = 2 * t(n/2) + o(n) = nlogn
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(nums, low, mid);
            mergeSort(nums, mid + 1, high);
            combine(nums, low, mid, high);
        }
    }
    public void combine(int[] nums, int low, int mid, int high) {
        int[] newnum = new int[nums.length] ;
        for (int i = 0; i < nums.length; i ++) {
            newnum[i] = nums[i];
        }
        int i = low;
        int j = mid + 1;
        int k = low;
        while(i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                newnum[k] = nums[i];
                i ++;
                k ++;
            }
            else {
                newnum[k] = nums[j];
                j ++;
                k ++;
            }
        }
        while (i <= mid) {
            newnum[k] = nums[i];
            i ++;
            k ++;
        }
        while(j <= high) {
            newnum[k] = nums[j];
            j ++;
            k ++;
        }
    }
}
