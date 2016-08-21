package array;

import java.util.HashMap;

/**
 * Created by yingtan on 9/29/15.
 */
public class NumOneDigit {

    // Time exceeds limit
    public int countDigitOne(int n) {

        int dividend = 1;
        int digits = dividend * 10;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1 , 1);
        int res = 0;

        for (int i = 1 ; i <= n ; i ++) {
            if ((i / digits) >= 1) {
                dividend = dividend * 10;
                digits = digits * 10;
            }
            int divider = i / dividend;
            int numOne = 0;
            if (divider == 1) {
                numOne = 1;
            }
            int remainder = i % dividend;
            // System.out.println("remainder:"+ remainder);

            if (map.containsKey(remainder)) {
                numOne = numOne + map.get(remainder);
            }

            if (numOne > 0) {
                // System.out.println("put:"+ i+ "'s num one:"+ numOne);
                map.put(i, numOne);
                res = res + numOne;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        NumOneDigit ob =  new NumOneDigit();
        System.out.println(ob.countDigitOne(3184191));
    }
}
