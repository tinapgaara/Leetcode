package facebook.binarySearch;

/**
 * Created by yingtan on 5/27/17.
 */
public class SqrtX {

    public int mySqrt_iteration(int x) {
        if (x < 0) return -1;
        if (x == 0) return 0;
        if (x == 1) return 1;
        long low = 0;
        long high = x / 2;
        while(low <= high) {
            long med = low + (high - low) / 2;
            if (med * med == x) return (int)med;
            else if (med * med < x) {
                low = med + 1;
            }
            else
                high = med - 1;
        }
        // important!!!! always choose high one as a result
        return (int)high;
    }

    public int mySqrt(int x) {
        if (x < 0) return -1;
        if (x == 0) return 0;

        return recurSqrt(x, 0, x);
    }
    public int recurSqrt(long x, long low, long high) {

        // Important !!!
        // case mySqrt(5)
        if (low > high) return (int)high;

        long med = low + (high - low) / 2;

        if (med * med == x) return (int)med;
        else if (med * med < x) {
            return recurSqrt(x, med + 1, high);
        }
        else
            return recurSqrt(x, low, med - 1);
    }
}
