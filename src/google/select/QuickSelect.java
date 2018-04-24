package google.select;

/**
 * Created by yingtan on 11/27/15.
 */
public class QuickSelect {

    public int selectK(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;
        while(low < high) {
            int index = partition(nums, low, high);
            if (index == k) {
                return nums[index];
            }
            else if (index > k) {
                high = index - 1;
            }
            else {
                low = index + 1;
            }
        }
        return -1;
    }

    public int partition(int[] nums, int low, int high) {
        int pivotindex = low + (high - low) / 2;
        int pivot = nums[pivotindex];
        int tmp = nums[high];
        nums[high] = pivot;
        nums[pivotindex] = tmp;

        int firstHalf = low;
        pivot = nums[high];
        for (int i = low; i < high; i++) {
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
        QuickSelect ob = new QuickSelect();
        int[] nums = new int[]{4,3,1,2};
        System.out.println(ob.selectK(nums, 1));
    }
}
