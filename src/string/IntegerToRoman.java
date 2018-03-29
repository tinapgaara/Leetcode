package string;

/**
 * Created by yingtan on 1/10/18.
 *
 * 12. Integer to Roman
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an integer, convert it to a roman numeral.

 Input is guaranteed to be within the range from 1 to 3999.


 */
public class IntegerToRoman {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < values.length; i ++) {
            while (num >= values[i]) { // important !!! using while here
                builder.append(strs[i]);
                num = num - values[i];
            }
        }
        return builder.toString();
    }
}
