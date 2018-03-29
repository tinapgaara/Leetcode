package array;

/**
 * Created by yingtan on 12/19/17.
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int lastDistinctIndex = 0;
        for (int i = 1 ; i < nums.length; i ++) {
            if (nums[i] != nums[lastDistinctIndex]) {
                nums[lastDistinctIndex + 1] = nums[i];
                lastDistinctIndex ++;
            }
        }
        return lastDistinctIndex + 1;
    }
}
