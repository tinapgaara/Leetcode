package array;

/**
 * Created by yingtan on 12/18/17.
 */
public class PartitionArray {

    // move all numbers < pivot to left
    // move all numbers > pivot to right
    // keep equal ones in middle
    // one pointer from low, one pointer from high
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

    // if put [smaller + equals] to left, and [larger] to right. Can use this.
    public void threepartition(int[] nums, int pivotIndex) {
        int pivot = nums[pivotIndex];
        int low = 0;
        int high = nums.length - 1;
        while(low <= high) {
            while(low <= high && nums[low] <= pivot) { // has to use <= pivot when partition <= pivot to left side
                low ++;
            }
            while(low <= high && nums[high] > pivot) {
                high --;
            }
            if (low <= high) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
                low ++;
                high --;
            }
        }
        System.out.println(low + "," + high);
        for (int i = 0 ; i < nums.length; i ++) {
            System.out.println(nums[i]);
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
