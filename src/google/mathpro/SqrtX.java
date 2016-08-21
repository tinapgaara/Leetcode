package google.mathpro;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by max2 on 11/2/15.
 */
/*
* Sqrt(x)
*
* Implement int sqrt(int x).
Compute and return the square root of x.
* */
public class SqrtX {

    public int mySqrt(int x) {
        if (x < 0) return -1;
        if (x == 0) return 0;

        return (int)recurMySqrt(x, 0, x);
    }

    public long recurMySqrt(int target, long low, long high) {
        if (low > high) return high;

        long med = (low + high) / 2;
        if (med * med == target) {
            return med;
        }
        else if (med * med < target) {
            //prevMed = med;
            return recurMySqrt(target, med+1, high);
        }
        else {
            // prevMed = med;
            return recurMySqrt(target, low, med-1);
        }
    }

    public float floatSqrt(float x, int precision) {
        return recurFloatSqrt(x, 1, x, precision, 0);
    }

    public float recurFloatSqrt(float target, float low, float high, int precision, float prevMed) {
        String str = prevMed+"";
        int index = str.indexOf('.');
        int decimalLen = str.length() - index - 1;
        if (decimalLen > (precision+ 3)) { // judge if has reached precision
            BigDecimal a = new BigDecimal(prevMed);
            BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            return roundOff.floatValue();
        }

        float med = (low + high) / 2;

        // for integer: sqrt(4) = 2
        BigDecimal a = new BigDecimal(med);
        BigDecimal roundOff = a.setScale(0, BigDecimal.ROUND_HALF_EVEN);
        float nMed = roundOff.floatValue();

        if (nMed * nMed == target) {
            return nMed;
        }
        else if (med * med < target) {
            prevMed = med;
            return recurFloatSqrt(target, med, high, precision, prevMed);
        }
        else {
            return recurFloatSqrt(target, low, med, precision, prevMed);
        }
    }

    public static void main(String[] args) {
        SqrtX ob = new SqrtX();
        System.out.println(ob.floatSqrt(2, 2));
    }
}