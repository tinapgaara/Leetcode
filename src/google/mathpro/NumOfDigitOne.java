package google.mathpro;

/**
 * Created by yingtan on 12/22/15.
 */
/*
* Given an integer n, count the total number of digit 1
* appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
* */
public class NumOfDigitOne {

    public int countDigitOne(int n) {
        long count = 0 ;
        long m = 1;
        while (m <= n) {
            long a = n / m ;
            long b = n %  m;
            count = count + ((a + 8) / 10) * m;
            if (a % 10 == 1) {
                count = count + (b + 1);
            }
            m = m * 10;
        }
        return (int)count;
    }
}
