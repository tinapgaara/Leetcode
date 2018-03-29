package linkedin.practice;

/**
 * Created by yingtan on 11/23/17.
 */
public class SumOfSquareNumbers {

    public boolean judgeSquareSum(int c) {
        int low = 1;
        int high = (int)Math.sqrt(c);
        if (c < 0) return false;
        while(low < high) {
            if (low * low + high * high == c) {
                return true;
            }
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
