package google.sort;

/**
 * Created by yingtan on 11/8/15.
 */
public class QuickSort {

    public void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        // pick the pivot
        int middle = (low + high) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {

            while (arr[i] > pivot) {
                i++;
            }

            while (arr[j] < pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100,0,4,2,8,19,7,4,2,0,9,7};
        QuickSort ob = new QuickSort();
        ob.quickSort(nums, 0, nums.length -1);
        System.out.println("");
    }
}
