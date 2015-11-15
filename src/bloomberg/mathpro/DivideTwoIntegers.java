package bloomberg.mathpro;

/**
 * Created by yingtan on 11/15/15.
 */
/*
* 11 / 6
*
* = 1011 / (2^2 + 2^1)
* = 1011 / 110
*
*
* */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {

        if (divisor == 0) return Integer.MAX_VALUE;
        long res = 0 ;
        long gDividend = (long)dividend;
        long gDivisor = (long)divisor;

        boolean isNeg = false;
        if ( ((dividend < 0) && (divisor > 0)) || ((dividend > 0) && (divisor < 0)) ) {
            isNeg = true;
        }

        if (dividend == Integer.MIN_VALUE) { // need to judge MIN_VALUE / 1  and MIN_VALUE/-1 especially
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            else if (divisor == -1){
                return Integer.MAX_VALUE; // for overflow,
            }
        }

        gDividend = Math.abs(gDividend);
        gDivisor = Math.abs(gDivisor);

        int digits = 0;
        while (gDivisor <= (gDividend >> 1)) { // must be in this way: divisor <= (dividend >> 1)
            gDivisor = gDivisor << 1;
            digits ++;
        }

        while (digits >= 0) { // must be larger than 0

            if (gDividend >= gDivisor) {
                gDividend = gDividend - gDivisor;
                res = res  + (1 << digits);
            }
            digits --;
            gDivisor = gDivisor >> 1;
        }

        if (isNeg) {
            res = (-1) * res;
        }
        if ((res >= Integer.MIN_VALUE) && (res <= Integer.MAX_VALUE))
            return (int)res;
        else
            return Integer.MAX_VALUE;

    }

    public static void main(String[] args) {
        DivideTwoIntegers ob = new DivideTwoIntegers();
        System.out.println(ob.divide(Integer.MIN_VALUE, 1));
    }
}
