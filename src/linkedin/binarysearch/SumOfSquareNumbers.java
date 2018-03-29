package linkedin.binarysearch;

/**
 * Created by yingtan on 11/20/17.
 *
 * 633. Sum of Square Numbers
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

 Example 1:
 Input: 5
 Output: True
 Explanation: 1 * 1 + 2 * 2 = 5
 Example 2:
 Input: 3
 Output: False
 */
public class SumOfSquareNumbers {

    public boolean judgeSquareSum(int c) {
        int low = 0;
        int high = (int)Math.sqrt(c);
        if (c < 0) return false;
        while(low <= high) {
            if (low * low + high * high == c) return true;
            else if (low * low + high * high < c) {
                low ++;
            }
            else {
                high --;
            }
        }
        return false;
    }
}
