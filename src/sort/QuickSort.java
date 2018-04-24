package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/30/15.
 */
public class QuickSort {

    public void quicksort(int[] nums, int low, int high) {
        if (low > high) return;
        int mid = partition(nums, low, high);
        quicksort(nums, low, mid - 1);
        quicksort(nums, mid + 1, high);
    }
    public int partition(int[] nums, int low, int high) {
        int pivotIndex = low + (high - low) / 2;
        // exchange pivot with high elements
        int tmp = nums[high];
        nums[high] = nums[pivotIndex];
        nums[pivotIndex] = tmp;

        int pivot = nums[high];
        int firstHalf = low;
        for (int i = low; i < high; i ++) {
            if (nums[i] <= pivot) {
                tmp = nums[firstHalf];
                nums[firstHalf] = nums[i];
                nums[i] = tmp;
                firstHalf ++;
            }
        }
        tmp = nums[firstHalf];
        nums[firstHalf] = nums[high];
        nums[high] = tmp;
        return firstHalf;

    }
}
