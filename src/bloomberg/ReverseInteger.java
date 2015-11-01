package bloomberg;

/**
 * Created by yingtan on 10/15/15.
 */
/*
* Leetcode: reverse integer
*
*
* */
public class ReverseInteger {

    // Use long
    public int reverse(int x) {
        boolean isNeg = false;
        if (x < 0) {
            isNeg = true;
            x = x * -1;
        }
        // Important !!!!  res must use Long
        long res = 0;
        // Important !!!! Once the > 32bits, can not use > Integer.MAX_VALUE to judge, the sign has been changed, and
        // value is randomly.
        while ((res < Integer.MAX_VALUE) && (x > 0)) {
            int dividend = x / 10;
            int remain = x % 10;
            res = res * 10 + remain;
            x = dividend;
        }

        if (res >= Integer.MAX_VALUE) return 0;
        if (isNeg) res =  res * -1;
        return (int)res;
    }

    public static void main(String[] args) {
        ReverseInteger ob = new ReverseInteger();
        ob.reverse(1534236469);
    }
}
