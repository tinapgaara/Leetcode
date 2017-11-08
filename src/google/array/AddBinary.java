package google.array;

/**
 * Created by yingtan on 5/7/17.
 *
 * 67. Add Binary
 *
 * Given two binary strings, return their sum (also a binary string).

 For example,
 a = "11"
 b = "1"
 Return "100".

 */
public class AddBinary {

    public String addBinary(String a, String b) {
        if ( (a == null) || (b == null) ) return null;
        char[] a_chs = a.toCharArray();
        char[] b_chs = b.toCharArray();

        int a_index = a_chs.length - 1;
        int b_index = b_chs.length - 1;
        int carry = 0;
        String res = "";
        while ( (a_index >= 0) || (b_index >= 0) ) {
            int a_int = 0;
            int b_int = 0;
            if (a_index >= 0) {
                a_int = a_chs[a_index] - '0';
                a_index --;
            }
            if (b_index >= 0) {
                b_int = b_chs[b_index] - '0';
                b_index --;
            }
            int cur = a_int ^ b_int ^ carry;
            int total = a_int + b_int + carry;
            if (total >= 2) { // when more than 2 '1', then carry = 1
                carry = 1;
            }
            else {
                carry = 0;
            }
            res = cur + res;
        }
        if (carry > 0) {
            res = carry + res;
        }
        return res;
    }
}
