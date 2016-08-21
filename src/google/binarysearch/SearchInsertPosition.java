package google.binarysearch;

/**
 * Created by yingtan on 11/27/15.
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        if ((nums == null) || (nums.length == 0))
            return 0;

        return recurBinarySearch(nums, target, 0, nums.length-1);
    }

    public int recurBinarySearch(int[] nums, int target, int low, int high) {
        if (high < low) return -1;
        int med = (low + high) / 2;
        if (nums[med] == target) {
            return med;
        }
        else {
            if ((med-1 >= 0)) {
                if ((target > nums[med-1]) && (target < nums[med]))
                    return med;
            }
            if (target < nums[med]) {
                if ((med == low) && (low == 0))
                    return 0;
                else
                    return recurBinarySearch(nums, target, low, med-1);
            }
            else if (target > nums[med]) {
                if ((high == med) && (high == nums.length -1))
                    return high + 1;
                return recurBinarySearch(nums, target, med+1, high);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInsertPosition ob =new SearchInsertPosition();
        int[] nums = new int[]{1,3};
        System.out.println(ob.searchInsert(nums, 4));
    }

}
