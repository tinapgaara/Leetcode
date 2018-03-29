package facebook.array;
//Given an almost sorted array where only two elements are swapped, how to sort the array efficiently?
public class TwoElementsSwappedInSortedArray {
    public void swapPair(int[] nums) {
        for (int i = 1 ; i < nums.length; i ++) {
            if (nums[i] < nums[i-1]) {
                // i is the element need to be swapped
                // i-1 i
                // find out where i used to be, find out 1st element which is larger than i, and insert i-1th
                int j = i;
                while(j < nums.length) {
                    if (nums[j] <= nums[i]) {
                        j ++;
                    }
                    else {
                        break;
                    }
                }
                j = j - 1;
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
    }
    // follow up: what if array is big

}
