package array;

/**
 * Created by yingtan on 12/18/17.
 */
public class PartitionArray {
    // move all numbers < pivot to left
    // move all numbers > pivot to right
    // keep equal ones in middle
    // one pointer from low, one pointer from high
    public void partition(int[] nums, int pivotIndex) {
        int low = 0;
        int high = nums.length - 1;
        int pivot = nums[pivotIndex];
        while(low < high) {
            while(nums[low] < pivot) {
                low ++;
            }
            while(nums[high] > pivot) {
                high --;
            }
            //swap
            if (low < high) {
                int tmp = nums[high];
                nums[high] = nums[low];
                nums[low] = tmp;
                low ++;
                high --;
            }
        }
    }

    // sol2 : [p1 p2 p3]
    public void partition_3pointers(int[] nums, int pivotIndex) {
        int p1 = 0;
        int p2 = 0;
        int p3 = nums.length - 1;
        while(p2 < p3) {
            if (nums[p2] < nums[pivotIndex]) {
                int tmp = nums[p1];
                nums[p1] = nums[p2];
                nums[p2] = tmp;
                p1 ++;
                p2 ++;
            }
            else if (nums[p2] == nums[pivotIndex]) {
                p2 ++;
            }
            else {
                int tmp = nums[p2];
                nums[p2] = nums[p3];
                nums[p3] = tmp;
                p3 --;
            }
        }
    }

    public static void main(String[] args) {
        PartitionArray ob = new PartitionArray();
        int[] nums = {4,2,3,5,7};
        ob.partition_3pointers(nums, 2);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
