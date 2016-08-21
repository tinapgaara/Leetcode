package spotify;

/**
 * Created by yingtan on 11/18/15.
 */
public class InsertNumberInSortedArray {

    public int findIndexToInsert(int[] nums, int val) {
        if ((nums == null) || (nums.length == 0)) return -1;

        return recurFindIndex(nums, val, 0, nums.length -1);
    }

    public int recurFindIndex(int[] nums, int val, int low, int high) {
        if (low == high) {
            if (nums[low] < val) {
                return low + 1;
            }
            else {
                return low;
            }
        }
        else if (low > high) return 0;
        else {
            int med = (low + high) / 2;
            if (val > nums[med]) {
                return recurFindIndex(nums, val, med+1, high);
            }
            else {
                return recurFindIndex(nums, val, low, med-1);
            }
        }
    }

    public static void main(String[] args) {
        InsertNumberInSortedArray ob = new InsertNumberInSortedArray();
        int[] nums = new int[]{4,5,6,7,9,10};
        System.out.println(ob.findIndexToInsert(nums, 6));
    }
}
