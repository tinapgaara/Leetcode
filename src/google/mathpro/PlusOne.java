package google.mathpro;

/**
 * Created by yingtan on 11/1/15.
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {

        if ((digits == null) || (digits.length == 0)) return null;
        int[] res = new int[digits.length];

        int carry = 1;
        for (int i = digits.length -1 ; i >= 0 ; i --) {
            int digit = digits[i];
            int num = (digit + carry) % 10;
            carry = (digit + carry) / 10;
            res[i] = num;
        }
        if (carry > 0) {
            int[] newres = new int[digits.length + 1];
            newres[0] = carry;
            for (int i = 0; i < digits.length ; i ++) {
                newres[i + 1] = res[i];
            }
            res = newres;
        }
        return res;
    }

    // how to deal with million digits array ?


}
