package google.mathpro;

/**
 * Created by yingtan on 12/22/15.
 */
/*
* Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*
* */
public class FactorialTrailingZeros {

    public int trailingZeroes(int n) {
        long dividend = 5;
        long count = 0;
        while (n >= dividend) {
            count = count + (n / dividend);
            dividend = dividend * 5;
        }
        return (int)count;
    }
}
