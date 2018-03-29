package facebook.array;

/**
 * Created by yingtan on 3/21/18.
 *
 * http://blog.csdn.net/huberjobs/article/details/51036505
 */
import java.util.*;
public class AddBigNumber {
    public String bigNumberSum(String num1, String num2) {
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }
        // deal with - +
        char first1 = num1.charAt(0);
        char first2 = num2.charAt(0);
        boolean isPos1 = true;
        boolean isPos2 = true;
        boolean resPos = true;
        if (first1 == '-' || first1 == '+') {
            if (first1 == '-') {
                isPos1 = false;
            }
            num1 = num1.substring(1);
        }
        if (first2 == '-' || first2 == '+') {
            if (first2 == '-') {
                isPos2 = false;
            }
            num2 = num2.substring(1);
        }
        // reverser order
        char[] ch1 = new StringBuffer(num1).reverse().toString().toCharArray();
        char[] ch2 = new StringBuffer(num2).reverse().toString().toCharArray();
        // prepare result array
        int[] res = new int[Math.max(ch1.length, ch2.length) + 1];
        if (isPos1 & isPos2) {
            // same pos: add together
            int i = 0;
            int j = 0;
            int len = 0;
            while(i < ch1.length && j < ch2.length) {
                res[len] = (ch1[i] - '0') + (ch2[j] - '0');
                len ++;
                i ++;
                j ++;
            }
            while(i < ch1.length) {
                res[len] = ch1[i] - '0';
                len ++;
                i ++;
            }
            while(j < ch2.length) {
                res[len] = ch2[i] - '0';
                len ++;
                j ++;
            }
            // do carry
            for (int k = 0 ; k < len; k ++) {
                if (res[k] >= 10) {
                    res[k+1] = res[k+1] + res[k] / 10;
                    res[k] = res[k] % 10;
                }
            }
        }
        else {
            // must be -1 * (long size - short size)
            if (ch1.length > ch2.length) {
                resPos = isPos1;
                // num1 - num2
                minus(ch1, ch2, res);
            }
            else if (ch1.length < ch2.length) {
                resPos = isPos2;
                // num2 - num1
                minus(ch2, ch1, res);
            }
            else {
                // same length
                // use larger number - smaller number
                boolean num1Larger = num1.compareTo(num2)>0 ? true : false;
                if (num1Larger) {
                    resPos = isPos1;
                    minus(ch1, ch2, res);
                }
                else {
                    resPos = isPos2;
                    minus(ch2, ch1, res);
                }
            }
        }
        // deal with res result array, to string
        StringBuilder st = new StringBuilder();
        if (isPos1 && isPos2) {
            int k = res.length - 1;
            while(k >= 0 && res[k] == 0) {
                // skip higher 0
                k --;
            }
            for (; k >= 0 ; k --) {
                st.append(res[k]);
            }
        }
        else if (! isPos1 && ! isPos2) {
            // -1 - 3
            int k = res.length - 1;
            while(k >= 0 && res[k] == 0) {
                // skip higher 0
                k --;
            }
            for (; k >= 0 ; k --) {
                st.append(res[k]);
            }
            st.insert(0, "-");
        }
        else {
            // -1 + 3
            int k = res.length - 1;
            while(k >= 0 && res[k] == 0) {
                // skip higher 0
                k --;
            }
            for (; k >= 0 ; k --) {
                st.append(res[k]);
            }
            if (! resPos) {
                st.insert(0, "-");
            }
        }
        return st.toString();
    }
    public void minus(char[] larger, char[] smaller, int[] res) {
        int i = 0;
        int j = 0;
        int len = 0;
        while(i < larger.length && j < smaller.length) {
            res[len] = (larger[i] - '0') - (smaller[j] - '0');
            len ++;
            i ++;
            j ++;
        }
        while(i < larger.length) {
            res[len] = larger[i] - '0';
            len ++;
            i ++;
        }
        while(j < smaller.length) {
            res[len] = smaller[i] - '0';
            len ++;
            j ++;
        }
        // do borrow
        for (int k = 0 ; k < len; k ++) {
            if (res[k] < 0) {
                res[k] = res[k] + 10;
                res[k + 1]--;
            }
        }
    }
    public static void main(String[] args) {
        AddBigNumber ob = new AddBigNumber();
        System.out.println(ob.bigNumberSum("-181", "99"));
    }
}
