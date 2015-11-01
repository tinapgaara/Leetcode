package vmware;

/**
 * Created by yingtan on 10/28/15.
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if (len < k) {
            k = k % len;
        }
        int low = 0;
        int high = len - k - 1;
        // swap first part
        while ((low < high) && (high < len)) {
            int tmp = nums[low];
            nums[low] = nums[high];
            nums[high] = tmp;
            low ++;
            high --;
        }
        high = len -1;
        low =  len - k;
        // swap second part
        while (low < high) {
            int tmp = nums[low];
            nums[low] = nums[high];
            nums[high] = tmp;
            low ++;
            high --;
        }
        // swap whole part
        low = 0;
        high = len - 1;
        while (low < high) {
            int tmp = nums[low];
            nums[low] = nums[high];
            nums[high] = tmp;
            low ++;
            high --;
        }

    }

    public static void main(String[] args) {
        RotateArray ob = new RotateArray();
        int[] nums = new int[]{1,2};
        ob.rotate(nums,1);
    }
}
