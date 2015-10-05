package BitOperation;

/**
 * Created by yingtan on 9/28/15.
 */
public class BitwiseAnd {


    // how to calculate higher digit: (Integer.MAX_VALUE << i) & number
    // i= 0: higher n digits same or not
    // i= 1: higher (n-1) digits same or not
    // i= 2: higher (n-2) digits same or not
    public int rangeBitwiseAND(int m, int n) {
        int helper = Integer.MAX_VALUE;
        while ((m & helper) != (n & helper)) {
            helper = helper << 1;
        }
        return (m & helper);
    }


    public int rangeBitwiseAnd(int m, int n) {
        if (m > n ) {
            int tmp = n;
            n = m;
            m = tmp;
        }
        int res = 0;
        for (int i = 0 ; i < 32; i ++) {
            int lastDigit = 1;
            for (int j = m ; j <= n ; j ++) {
                int digit = (j >> i);
                if  ( (digit == 0) || (lastDigit == 0) ) {
                    lastDigit = 0;
                    break;
                }
                lastDigit = ((digit & 1) & (lastDigit));
            }
            res = (lastDigit << i) + res;
        }
        return res;
    }

    public static void main(String[] args) {
        BitwiseAnd ob = new BitwiseAnd();
        System.out.println(ob.rangeBitwiseAND(6, 7));
    }
}
