package google.array;

/**
 * Created by yingtan on 5/7/17.
 *
 * 41. First Missing Positive
 *
 * Given an unsorted integer array, find the first missing positive integer.

 For example,
 Given [1,2,0] return 3,
 and [3,4,-1,1] return 2.

 Your algorithm should run in O(n) time and uses constant space.

 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        // form this arr:[1, 2, 3] ; do not take care of all 0s and minus number
        // put number 1 to pos 0, make sure pos+1 = num: pos = num - 1
        // put number 2 to pos 1, make sure pos+1 = num: pos = num - 1
        for (int i = 0 ; i < nums.length; i ++) {
            // use while here, because maybe the swapped value is not at its supposed index number
            while (nums[i] != (i + 1)) {
                int supposedPosOfI = ( nums[i] - 1 );

                if ( (supposedPosOfI >= nums.length) || (nums[i] <= 0) ) break;

                // put nums[i] to the pos (nums[i] - 1)
                // but swapped one may not ensure: nums[j] is at pos: nums[j] - 1
                int swappedNum = nums[supposedPosOfI];

                // this means, even current nums[i] is not at correct pos, but swappedNum already equals to it
                // so, even swap again, the number is still the same, can not satify this requir, so no need to swap anymore
                if (nums[i] == swappedNum) break;

                nums[supposedPosOfI] = nums[i];
                nums[i] = swappedNum;
            }
        }


        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] != (i + 1) ) return (i + 1);
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        FirstMissingPositive ob = new FirstMissingPositive();
        int[] nums = new int[]{3,4,-1,1};
        ob.firstMissingPositive(nums);
    }
}
