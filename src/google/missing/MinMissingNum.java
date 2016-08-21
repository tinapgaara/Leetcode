package google.missing;

/**
 * Created by yingtan on 11/8/15.
 */
public class MinMissingNum {

    // o(logn)
    public int findMissing(int[] nums, int k) {

        int len = nums.length;
        return binarySearchMissing(nums, k, 0, len - 1);
    }

    public int binarySearchMissing(int[] nums, int missingNumberNo, int low, int high) {
        if (high - low == 1) {
            return nums[low] + missingNumberNo;
        }

        int med = (low + high) / 2;
        int leftMissingNumbers =  nums[med] - nums[low] - (med - low);
        if (missingNumberNo > leftMissingNumbers) {
            // pay attention : is (med, high)
            return binarySearchMissing(nums, missingNumberNo - leftMissingNumbers, med, high);
        }
        else {
            // pay attention : is (low, med)
            return binarySearchMissing(nums, missingNumberNo, low, med);
        }

    }


    public static void main(String[] args) {
        MinMissingNum ob = new MinMissingNum();
        int[] nums = new int[]{0,1,3,4,6,7,8};
        System.out.println(ob.findMissing(nums, 2));
    }
}
