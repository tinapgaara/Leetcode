package linkedin.array;

/**
 * Created by yingtan on 11/28/17.
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] res =  new int[nums.length];
        if (nums == null || nums.length == 0) return res;

        int productFromStart = 1;
        int productFromEnd = 1;
        for (int i = 0 ; i < nums.length; i ++) {
            res[i] = productFromStart;
            productFromStart = productFromStart * nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i --) {
            res[i] = res[i] * productFromEnd;
            productFromEnd = productFromEnd * nums[i];
        }
        return res;
    }
}
