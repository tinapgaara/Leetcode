package google.math;

/**
 * Created by yingtan on 7/23/17.
 *
 *
 Add to List
 66. Plus One
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

 You may assume the integer do not contain any leading zero, except the number 0 itself.

 The digits are stored such that the most significant digit is at the head of the list.
 */
public class PlusOne {
    /*
    * 这道题就是实现题。

先对原数组进行处理。从数组最后一位开始往前检查，如果当前数字是<9的，说明你加1无需进位，从循环跳出即可，如果当前数字等于9，说明加1涉及进位，且加1后当前数字应记为0，继续循环处理。

当对原数组处理完后，还需要判断当前第0位是不是已经变为0了，如果已经变为0了说明是类似99+1这种，需要进位。其他则不需要。

一般对数字进行操作的题都要考虑边界，尤其是溢出问题。
    *
    * */

    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) return null;
        for (int i = digits.length -1 ; i >= 0; i --) {
            if (digits[i] < 9) {
                digits[i] ++;
                break;
            }
            else {
                digits[i] = 0;
            }
        }

        int[] res;
        if (digits[0] == 0) {
            // case:  99 + 1   = ? 00  , has carry, need to increase digits size + 1
            res = new int[digits.length + 1];
            res[0] = 1;
            for (int i = 0; i < digits.length; i ++) {
                res[i + 1] = digits[i];
            }
        }
        else {
            // case: 89 +1 = 90
            res = new int[digits.length];
            for (int i = 0 ; i < digits.length; i ++) {
                res[i] = digits[i];
            }
        }
        return res;
    }
}
