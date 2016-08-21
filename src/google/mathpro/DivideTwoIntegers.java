package google.mathpro;

/**
 * Created by yingtan on 12/22/15.
 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        boolean isNeg = false;
        long gDividend = (long)dividend;
        long gDivisor = (long)divisor;

        if ((gDividend < 0) && (gDivisor > 0)) {
            gDividend = gDividend * (-1);
            isNeg = true;
        }
        else if ((gDividend > 0) && (gDivisor < 0)) {
            gDivisor = gDivisor * (-1);
            isNeg = true;
        }
        else if ((gDividend < 0) && (gDivisor < 0)) {
            gDividend = gDividend * -1;
            gDivisor = gDivisor * -1;
        }
        long res = 0;
        long moveNum = gDivisor;
        while (gDividend >= moveNum) {
            long moveBits = 1;
            while ((moveNum << 1) <= gDividend) {
                moveNum = moveNum << 1;
                moveBits = moveBits << 1;
            }
            gDividend = gDividend - moveNum;
            moveNum = gDivisor;
            res = res + moveBits;
        }
        if (isNeg) res = res * (-1);
        if (res > Integer.MAX_VALUE) return Integer.MAX_VALUE;

        return (int)res;
    }
}
