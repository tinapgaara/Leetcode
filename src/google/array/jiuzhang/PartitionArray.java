package google.array.jiuzhang;

/**
 * Created by yingtan on 11/2/17.
 *
 * artition Array

 Description
 Notes
 Testcase
 Judge
 Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

 All elements < k are moved to the left
 All elements >= k are moved to the right
 Return the partitioning index, i.e the first index i nums[i] >= k.
 */
public class PartitionArray {

    public int partitionArray(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) return 0;
        int low = 0;
        int high = nums.length -1;
        while(low < high) {
            while (low < high && nums[low] < k) {
                low ++;
            }
            while (low < high && nums[high] >= k) {
                high --;
            }

            //swap nums[low] and nums[high]
            if (low < high) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;

                low ++;
                high --;
            }
        }
        // low == high
        if (nums[low] < k) {
            return low + 1;
        }

        return low;
    }
}
