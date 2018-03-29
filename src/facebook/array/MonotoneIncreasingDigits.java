package facebook.array;

import java.util.Stack;

/**
 * Created by yingtan on 3/18/18.
 *
 * 738. Monotone Increasing Digits
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

 (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

 Example 1:
 Input: N = 10
 Output: 9
 Example 2:
 Input: N = 1234
 Output: 1234
 Example 3:
 Input: N = 332
 Output: 299
 Note: N is an integer in the range [0, 10^9].


 */
public class MonotoneIncreasingDigits {

    public int monotoneIncreasingDigits(int N) {
        if (N == 0) return 0;
        String str = N + "";
        char[] ch = str.toCharArray();
        for (int i = ch.length - 1; i > 0 ; i --) {
            if (ch[i] < ch[i - 1]) {
                int digit = ch[i-1] - '0';
                int newdigit = digit - 1;
                ch[i-1] = (char)(newdigit + '0');
                int tmp = i;
                while(tmp < ch.length) {
                    ch[tmp] = '9';
                    tmp ++;
                }
            }
        }
        String s = "";
        for (char c : ch) {
            if (c == '0') continue;;
            s = s + c;
        }
        return Integer.parseInt(s);
    }
    public static void main(String[] args) {
        MonotoneIncreasingDigits ob = new MonotoneIncreasingDigits();
        ob.monotoneIncreasingDigits(10);
    }
}

