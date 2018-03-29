package facebook.sort;

/**
 * Created by yingtan on 3/13/18.
 */
public class PartitionArray {
    public void partition(int[] nums, int pivotIndex) {
        // move elements less than pivot before pivotIndex, nums equals to pivot, nums larger than pivot
        int pivot = nums[pivotIndex];
        int lessThanPivot = 0; // keep record how many numbers  < pivot
        int largerThanPivot = nums.length - 1;
        // same as partition these to 3 sets -> 3 colors
        int i = 0;
        while(i < largerThanPivot) {
            if (nums[i] < pivot) {
                int tmp = nums[i];
                nums[lessThanPivot] = nums[i];
                nums[i] = tmp;
                lessThanPivot ++;
                i ++;
            }
            else if (nums[i] > pivot) {
                // put to the tail
                int tmp = nums[i];
                nums[i] = nums[largerThanPivot];
                nums[largerThanPivot] = tmp;
                largerThanPivot --;
            }
            else {
                // same as pivot, rightplace
                i ++;
            }
        }
    }
}
