package google.array.jiuzhang;

/**
 * Created by yingtan on 11/1/17.
 */
public class KLargestElement {

    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        if (nums == null) return -1;
        if (k <= 0) {
            return 0;
        }
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int low, int high, int k) {
        // must use ==
        if (high == low) {
            return nums[low];
        }
        int i = low;
        int j = high;
        int mid = i + (j - i) / 2;
        int pivot = nums[mid];
        while(i <= j) {
            while(i <= j && nums[i] > pivot) {
                i ++;
            }
            while(i <= j && nums[j] < pivot) {
                j --;
            }
            if (i <= j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i ++;
                j --;
            }
        }

        if (low + k - 1 <= j) {
            return quickSelect(nums, low, j, k);
        }
        if (low + k - 1 >= i) {
            return quickSelect(nums, i, high, k - (i - low));
        }
        return nums[j + 1];
    }

    public static void main(String[] args) {
        KLargestElement ob = new KLargestElement();
        int[] data = new int[]{7,7,9,8,6,6,8,7,9,8,6,6};
    }
}
