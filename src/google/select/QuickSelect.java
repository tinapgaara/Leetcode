package google.select;

/**
 * Created by yingtan on 11/27/15.
 */
public class QuickSelect {

    public int select(int[] nums, int k, int low, int high) {
        if (low > high) {
            return -1;
        }

        int pivot = partition(nums, low, high);
        if (pivot == k) { // pivot has been put to the correct position
            return nums[pivot];
        }
        else if (k > pivot) {
            if (pivot < high)
                return select(nums, k, pivot, high);

        }
        else {
            if (low < pivot - 1) // ensure that gap >= 1 between low and high
                return select(nums, k, low, pivot-1);
        }
        return -1;// can not find
    }

    public int partition(int[] nums, int low, int high) {
        int med = (low + high) /2;
        int i = low;
        int j = high;
        int pivot = nums[med];
        while (i <= j) {
            while (nums[i] < pivot) {
                i ++;
            }
            while (nums[j] > pivot) {
                j --;
            }
            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        QuickSelect ob = new QuickSelect();
        int[] nums = new int[]{4,3,1,2};
        System.out.println(ob.select(nums, 1, 0, nums.length-1));
    }
}
