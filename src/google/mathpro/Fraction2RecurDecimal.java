package google.mathpro;

import java.util.HashMap;

/**
 * Created by yingtan on 11/7/15.
 */
/*
* boundary case:
* Integer.MAX_VALUE / 1
*  - Integer.MAX_VALUE / 1
*  -1 / Integer.MAX_VALUE
*  1 / Integer.MIN_VALUE
*
*  复杂度
时间 O(N) 空间 O(N)
*
* */
public class Fraction2RecurDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        long dividend = 0;
        boolean neg = false;
        long remainder = 0;
        long gNumerator = (long)numerator; // Important !!!!! first translate to long
        long gDenominator = (long)denominator; // Important !!!!! first translate to long
        if (gNumerator * gDenominator < 0) {
            neg = true;
            if (gNumerator < 0) {
                gNumerator = gNumerator * -1;
            }
            else {
                gDenominator = -1* gDenominator;
            }
        }
        else if ((gNumerator < 0) && (gDenominator < 0)) {
            gNumerator = gNumerator * -1;
            gDenominator = -1 * gDenominator;
        }

        remainder = gNumerator % gDenominator; // 4
        dividend = gNumerator / gDenominator;
        long tmpDividend = dividend; //// Important !!! Need to keep this value

        // 存循环体remainder以及这个循环体
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        String result =  ".";
        if (remainder == 0){ // Important !!! All returns need to judge + or -.
            String divide = tmpDividend + "";
            if (neg) {
                divide = "-" + divide;
            }
            return divide;
        }
        //current len of result string
        int cur = 1;
        while (remainder > 0) {
            remainder = remainder * 10; //40
            if (map.containsKey(remainder)) {
                // 循环体开始的index
                int start = map.get(remainder);
                String divide = tmpDividend + "";
                if (neg) {
                    divide = "-" + divide;// Important !!! All returns need to judge + or -.
                }
                return divide + result.substring(0, start) + "(" +
                        result.substring(start, cur) + ")";
            }
            map.put(remainder, cur);
            dividend = remainder / gDenominator;
            result = result + dividend;
            remainder = remainder % gDenominator;
            cur ++;
        }
        String divide = tmpDividend + "";
        if (neg) {
            divide = "-" + divide; // Important !!! All returns need to judge + or -.
        }
        return divide+ "" + result;
    }

    public static void main(String[] args) {
        Fraction2RecurDecimal ob = new Fraction2RecurDecimal();
        System.out.println(ob.fractionToDecimal(-2147483648, 1));
    }

}
