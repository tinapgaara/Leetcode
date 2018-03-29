package google.binarysearch;

/**
 * Created by yingtan on 11/8/15.
 */
public class MinMissingNum {

    // o(logn)
    public int findMissing(int[] nums, int k) {

        int len = nums.length;
        return binarySearchMissing(nums, k, 0, len - 1);
    }

    public int binarySearchMissing(int[] nums, int missingNumberNo, int low, int high) {
        if (high - low == 1) {
            return nums[low] + missingNumberNo;
        }

        int med = (low + high) / 2;
        int leftMissingNumbers =  nums[med] - nums[low] - (med - low);
        if (missingNumberNo > leftMissingNumbers) {
            // pay attention : is (med, high)
            return binarySearchMissing(nums, missingNumberNo - leftMissingNumbers, med, high);
        }
        else {
            // pay attention : is (low, med)
            return binarySearchMissing(nums, missingNumberNo, low, med);
        }

    }


    public static void main(String[] args) {
        MinMissingNum ob = new MinMissingNum();
        int[] nums = new int[]{0,1,3,4,6,7,8};
        System.out.println(ob.findMissing(nums, 2));
    }

    public void quickSort(int[] arr, int low, int high) {
        if (high >= low) return;
        int pivot = low + (high - low) / 2;
        int i = low;
        int j = high;
        // each while make sure that pivot is at its correct place
        while(i <= j) {
            while(i <=j && arr[i] < arr[pivot]) {
                i ++;
            }
            while(i <=j && arr[j] > arr[pivot]) {
                j --;
            }

            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i ++;
                j --;
            }
        }
        if (j > low) {
            quickSort(arr, low, j);
        }
        if (i < high) {
            quickSort(arr, i, high);
        }
    }
}
