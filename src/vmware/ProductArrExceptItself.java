package vmware;

/**
 * Created by yingtan on 10/28/15.
 */
/*
*
* Given an array of n integers where n > 1, nums,
* return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

    Solve it without division and in O(n).

    For example, given [1,2,3,4], return [24,12,8,6].

    Follow up:
    Could you solve it with constant space complexity?
    (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
*
* */
public class ProductArrExceptItself {

    public int[] productExceptSelf(int[] nums) {

        // scan from right to left
        // 1  2   3   4
        // 1  1   2   6   (the product from multipling form left before multiple current value)

        //scan from left to right
        // 1   2   3  4
        // 24  12  4  1   (the product from multipling from right before multiple current value)


        // multiple two values -> product except itself

        int[] products = new int[nums.length];
        products[0] = 1;
        for (int i = 1 ; i < nums.length; i ++) {
            products[i] = products[i-1] * nums[i-1];
        }
        int leftProduct = 1;
        for (int i = nums.length -1; i >= 0 ; i --) {
            products[i] = products[i] * leftProduct;
            leftProduct = leftProduct * nums[i];
        }
        return products;
    }
}
