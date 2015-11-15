package bloomberg.mathpro;

/**
 * Created by yingtan on 11/15/15.
 */
/*
* 11 * 7 = 11 * (2^2 + 2^1 + 2^0)
* = 11 * (2^2) + 11 * (2^1) + 11 * (2^0)
* == 11<<2 + 11<<1 + 11<<0
*
* */
public class MultipleTwoIntegers {

    public long multiple(int num1, int num2) {
        int shift  = 0;
        long res = 0; // pay attention !!!!
        while (num2 > 0 ) {
            int lastBit = num2 & 1;
            if (lastBit != 0) { // equals 1
                if (shift == 0) {
                    res = res + num1;
                }
                else {
                    res = res + (num1 << shift);
                }
            }
            num2 = num2 >> 1;
            shift ++;
        }
        return res;
    }

    public static void main(String[] args) {
        MultipleTwoIntegers ob = new MultipleTwoIntegers();
        System.out.println(ob.multiple(11,8));
    }
}
