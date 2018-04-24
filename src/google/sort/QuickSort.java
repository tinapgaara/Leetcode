package google.sort;

/**
 * Created by yingtan on 11/8/15.
 */
/*
* http://java67.blogspot.com/2014/07/quicksort-algorithm-in-java-in-place-example.html
*
* */
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


    public static void main(String[] args) {
        int[] nums = new int[]{3, 7,8,5,2,1,9,5,4};
        QuickSort ob = new QuickSort();
        ob.quicksort(nums, 0, nums.length - 1);
        System.out.println("");
    }
}
