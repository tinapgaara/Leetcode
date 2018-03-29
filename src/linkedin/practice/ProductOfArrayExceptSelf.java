package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 */
public class ProductOfArrayExceptSelf {

    public int[] product(int[] nums) {
        int[] res = new int[nums.length];
        if (nums == null) return res;
        int[] productBefore = new int[nums.length];
        productBefore[0] = 1;
        // 1 2 3 4
        // 1 1 2 6
        for (int i = 1; i < nums.length; i ++) {
            productBefore[i] = productBefore[i-1] * nums[i-1];
        }
        int productAfter = 1;
        for (int i = nums.length - 1; i>= 0 ; i --) {
            res[i] = productBefore[i] * productAfter;
            productAfter = productAfter * nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf ob = new ProductOfArrayExceptSelf();
        int[] nums = {1,2,3,4};
        ob.product(nums);
    }
}
