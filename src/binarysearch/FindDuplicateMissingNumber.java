package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 */
public class FindDuplicateMissingNumber {
    public int[] findDuplicateMissing(int[] nums) {
        // solution 1 : using bit
        int xor = 0;
        int i = 0;
        for (; i < nums.length; i ++) {
            xor = xor ^ i ^ nums[i];
        }
        xor = xor ^ i;
        // xor = missing ^ duplicate

        // find out the first bit where missing is diff from duplicate
        // find out all nums which has this bit=1 at this pos
        // find out all 0-n which has this bit=1 at this pos
        // xor them all to get the missing or the duplicate one.
        // TODO
        return new int[]{};
    }
    public int[] findDuplicateMissing_radixSort(int[] nums) {
        int dup = -1;
        int miss = -1;
        for (int i = 0 ; i < nums.length; i ++) {
            // make sure ith place has the corret number
            while(i >= 0 && i < nums.length && i != nums[i]) { // used to find out duplicate numbers
                int supposedPos = nums[i];
                // swap, put num to supposedPos
                // if supposedPos already has num, then, this is duplicate
                if (nums[i] == nums[supposedPos]) { // find out duplicate numbers
                    dup = nums[i];
                    break;
                }
                int tmp = nums[i];
                nums[i] = nums[supposedPos];
                nums[supposedPos] = tmp;
            }
        }
        for (int i = 0 ; i < nums.length; i ++) {
            if (i != nums[i]) {
                // missing one
                miss = i;
            }
        }
        return new int[]{dup, miss};
    }
    public static void main(String[] args) {
        FindDuplicateMissingNumber ob = new FindDuplicateMissingNumber();
        int[] nums = {0, 1, 2, 2,5, 4};
        ob.findDuplicateMissing_radixSort(nums);
    }
}
