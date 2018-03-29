package facebook.math;

/**
 * Created by yingtan on 2/19/18.
 */
public class Sqrt {
    public int mySqrt(int x) {
        if (x < 0) return -1;
        if (x == 0) return 0;
        if (x == 1) return 1;
        long low = 1;
        long high = x / 2; // must use long, because we do mid + 1, use int will overflow
        while(low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid == x) return (int)mid;
            else if (mid * mid > x) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return (int)high; // important !!! use high to be closet to x.
    }
}
