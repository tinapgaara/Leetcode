package bloomberg.sort;

/**
 * Created by yingtan on 11/16/15.
 */
public class QuickSort {

    public void quickSort(int[] nums, int low, int high) {

        if (low >= high) return; // low>=high return

        int med = (low + high) / 2;
        int i = low;
        int j = high;
        int pivot = nums[med]; // Important !!!! must be saved!!!

        while (i <= j) { // while i<= j
            while ( (nums[j] < pivot)) {
                j --;
            }

            while ((nums[i] > pivot)) {
                i ++;
            }

            if (i <= j) { // Important !!!!! i <= j: or will be dead loop
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;

                i++;
                j--;
            }
        }
        if (i < high) {
            quickSort(nums, i, high);
        }
        if (j > low) {
            quickSort(nums, low, j);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100,0,4,2,8,19,7,4,2,0,9,7};
        QuickSort ob = new QuickSort();
        ob.quickSort(nums, 0, nums.length -1);
        System.out.println("");
    }
}
