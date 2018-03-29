package facebook.math;

/**
 * Created by yingtan on 3/16/18.
 */
public class StringTToInteger {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        str = str.trim();
        long res = (long)0;
        int i = 0;
        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
            i = 1;
        }
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
                    if (res > (long)(Integer.MAX_VALUE) + (long)1) {
                        // minu overflow
                        return Integer.MIN_VALUE;
                    }
                }
                else {
                    if (res > (Integer.MAX_VALUE)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            else {
                break;
            }
        }
        if (str.charAt(0) == '-') {
            res = res * -1;
        }
        return (int)res;
    }
}
