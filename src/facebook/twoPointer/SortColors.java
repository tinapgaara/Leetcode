package facebook.twoPointer;

/**
 * Created by yingtan on 5/20/17.
 */
public class SortColors {

    public void sortColors(int[] nums) {
        // keep three pointers
        // red pointer: point to the next index of future red, all elements before this pointer are red
        // blue pointer: point to the next index of future blue, all elements after this pointer are blue

        // i: scan array once
        /*
          if nums[i] is red, swap nums[i] and nums[redPointer], redPointer ++, i ++;
          if nums[i] is blue, swap nums[i] and nums[bluePointer], bluePointer --;
          else i ++;
        */
        /*
        Important !!!
        Because i scans from start to end, so it make sures all elements before i are 0 - 1
        But this can not make sure numbers swapped from bluePointer will be 1 because those elements are after i, sometimes could be 0, and this need to be swapped again and i can't move
        */

        if (nums == null || nums.length == 0) return;
        int redPointer = 0;
        int bluePointer = nums.length - 1;
        int i = 0;
        while (i <= bluePointer) { // important !!!!
            if (nums[i] == 0) {
                // swap nums[i] and redPointer
                int tmp = nums[i];
                nums[i] = nums[redPointer];
                nums[redPointer] = tmp;
                i ++;  // // important !!!!The element of redPointer must be 0, or 1. couldn't be 2. so, we can make sure   nums[i] here will not be the wrong number
                redPointer ++;
            }
            else if (nums[i] == 2) {
                // swap nums[i] and bluePointer
                int tmp = nums[i];
                nums[i] = nums[bluePointer];
                nums[bluePointer] = tmp;

                bluePointer --; // do not do i++ here since elements from tail(of bluePointer) could be 0, which is a wrong element, boundary case: [1,2,0]
            }
            else
                i ++;
        }
    }

    public static void main(String[] args) {
        SortColors ob = new SortColors();
        int[] nums = new int[]{1,1,0,2,1,2,2,1};
        ob.sortColors(nums);
    }
}
