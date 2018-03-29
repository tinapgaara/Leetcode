package facebook.math;

/**
 * Created by yingtan on 1/7/18.
 *
 * 3. Multiply Strings
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

 Note:

 The length of both num1 and num2 is < 110.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultipleString {

    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) return "";
        // important !!!! multiple string can be extremly large, using int[] to store result
        int[] res = new int[num1.length() + num2.length()];
        for (int i1 = num1.length() - 1; i1 >= 0 ; i1 --) {
            for (int i2 = num2.length() -1; i2 >= 0; i2 --) {
                res[i1 + i2 + 1] = res[i1 + i2 + 1] + (num1.charAt(i1) - '0') * (num2.charAt(i2) - '0');
            }
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        // important !!! must scan from tail to head
        for (int i = res.length - 1; i >= 0 ; i --) {
            int digit = (res[i] + carry) % 10;
            carry = (res[i]+ carry)/ 10;
            res[i] = digit;
        }
        for (int i = 0 ; i < res.length; i ++) {
            sb.append(res[i]);
        }
        // important !!! delete all first 0s
        while(sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        if (sb.length() == 0) return "0";
        else return sb.toString();
    }
}

