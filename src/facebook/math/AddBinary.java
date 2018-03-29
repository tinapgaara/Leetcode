package facebook.math;

/**
 * Created by yingtan on 2/10/18.
 *
 * 67. Add Binary
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given two binary strings, return their sum (also a binary string).

 For example,
 a = "11"
 b = "1"
 Return "100".
 */
public class AddBinary {
    // addBinary，follow-up是变成base 2-10 的加法。还是中国小哥，人挺不错的。
    public String addBinary(String a, String b) {
        if (a == null || b == null) return "";
        int i1 = a.length() - 1;
        int i2 = b.length() - 1;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while(i1 >= 0 && i2 >= 0) {
            int digit = (a.charAt(i1) - '0') + (b.charAt(i2) - '0') + carry;
            carry = digit / 2;
            digit = digit % 2;
            builder.append(digit);
            i1 --;
            i2 --;
        }
        while(i1 >= 0) {
            int digit = a.charAt(i1) - '0' + carry;
            carry = digit / 2;
            digit = digit % 2;
            builder.append(digit);
            i1 --;
        }
        while(i2 >= 0) {
            int digit = b.charAt(i2) - '0' + carry;
            carry = digit / 2;
            digit = digit % 2;
            builder.append(digit);
            i2 --;
        }
        if (carry != 0) {
            builder.append(carry);
        }
        return builder.reverse().toString();
    }
    public String addBinary_kbase(String a, String b, int k) {
        if (a == null) return b;
        if (b == null) return a;
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder st = new StringBuilder();
        while(i >= 0 && j >= 0) {
            int sum = (a.charAt(i) - '0') + (b.charAt(j) - '0') + carry;
            int digit = sum % k;
            carry = sum / k;
            st.append(digit);
            i --;
            j --;
        }
        while(i >= 0) {
            int sum = (a.charAt(i) - '0') + carry;
            int digit = sum % k;
            carry = sum / k;
            st.append(digit);
            i --;
        }
        while(j >= 0) {
            int sum = (b.charAt(j) - '0') + carry;
            int digit = sum % k;
            carry = sum / k;
            st.append(digit);
            j --;
        }
        if (carry > 0) {
            st.append(carry);
        }
        return st.reverse().toString();
    }
}
