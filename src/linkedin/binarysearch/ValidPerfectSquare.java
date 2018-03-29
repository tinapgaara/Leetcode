package linkedin.binarysearch;

/**
 * Created by yingtan on 11/20/17.
 *
 * 367. Valid Perfect Square
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a positive integer num, write a function which returns True if num is a perfect square else False.

 Note: Do not use any built-in library function such as sqrt.

 Example 1:

 Input: 16
 Returns: True
 Example 2:

 Input: 14
 Returns: False
 */
public class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        // must use long instead of integer
        long low = 1;
        long high = num;
        if (num == 1) return true;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid == num) return true;
            else if (mid * mid < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}