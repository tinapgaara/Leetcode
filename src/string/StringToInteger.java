package string;

/**
 * Created by yingtan on 1/10/18.
 *
 * 8. String to Integer (atoi)
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Implement atoi to convert a string to an integer.

 Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

 Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

 Update (2015-02-10):
 The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
 */
public class StringToInteger {

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        str = str.trim();
        long res = (long)0;
        int i = 0;
        // check sign
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            i = 1;
        }
        // skip first 0s
        while(i < str.length()) {
            if (str.charAt(i) == '0') {
                i ++;
            }
            else {
                break;
            }
        }
        for (; i < str.length(); i ++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                res = res * 10 + (str.charAt(i) - '0');
                if (str.charAt(0) == '-') {
                    // must do overflow inside loop
                    if(res > (long)(Integer.MAX_VALUE + (long)1)) {
                        return Integer.MIN_VALUE;
                    }
                }
                else {
                    if (res > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            else break;
        }
        if (str.charAt(0) == '-') {
            res = res * -1;
        }
        return (int)res;
    }
}
