package linkedin.practice;

/**
 * Created by yingtan on 11/23/17.
 */
public class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        int low = 1;
        int high = num;
        if (num == 1) return true;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (mid * mid == num) return true;
            else if (mid * mid < num) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        if (low * low == num) return true;
        if (high * high == num) return true;
        else return false;
    }
}
