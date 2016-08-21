package array;

/**
 * Created by yingtan on 9/28/15.
 */
public class ProductArrExceptSelf {

    // without division
    // O(n)
    // constant space

    // except self: keep all products until self (not multiple self's value)
    // scan to right once, and scan to left once

    public int[] productExceptSelf(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) {
            return null;
        }
        int[] product = new int[nums.length];
        product[0] = 1;
        for (int i = 1; i < nums.length; i ++) {
            product[i] = product[i-1] * nums[i-1]; // the product until (i-1)
        }
        int leftProduct = 1;
        for (int j = (nums.length - 1); j >=0 ; j --) {
            product[j] = product[j] * leftProduct;
            leftProduct = leftProduct * nums[j];
        }
        return product;
    }
}
