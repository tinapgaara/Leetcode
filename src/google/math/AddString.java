package google.math;

/**
 * Created by yingtan on 8/20/17.
 *
 * 415. Add Strings
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 Note:

 The length of both num1 and num2 is < 5100.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddString {

    public String addStrings(String num1, String num2) {
        if (num1 == null && num2 == null) return null;
        else if (num1 == null && num2 != null) return num2;
        else if (num1 != null && num2 == null) return num1;

        int len1 = num1.length();
        int len2 = num2.length();
        int index1 = len1 - 1;
        int index2 = len2 - 1;
        int carry = 0;
        String res = "";

        while(index1 >= 0 && index2 >= 0) {
            char ch1 = num1.charAt(index1);
            char ch2 = num2.charAt(index2);

            int i1 = Integer.parseInt(ch1+"");
            int i2 = Integer.parseInt(ch2+"");

            int current = (i1 + i2 + carry) % 10;
            carry = (i1 + i2 + carry) / 10;

            res = current + res;

            index1 --;
            index2 --;
        }

        while (index1 >= 0) {
            char ch1 = num1.charAt(index1);
            int i1 = Integer.parseInt(ch1+"");
            int current = (i1 + carry) % 10;
            carry = (i1 + carry) / 10;

            res = current + res;

            index1 --;
        }

        while (index2 >= 0) {
            char ch2 = num2.charAt(index2);
            int i2 = Integer.parseInt(ch2+"");
            int current = (i2 + carry) % 10;
            carry = (i2 + carry) / 10;
            res = current + res;

            index2 --;
        }

        if (carry > 0) {
            res = carry + res;
        }
        return res;
    }
}
