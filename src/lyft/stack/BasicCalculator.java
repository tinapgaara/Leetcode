package lyft.stack;

/**
 * Created by yingtan on 4/11/18.
 */
import java.util.*;
public class BasicCalculator {
    // x + sign * y
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int res = 0;
        for (int i = 0 ; i < s.length(); i ++) {
            if (s.charAt(i) == ' ') continue;
            else if (s.charAt(i) == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            }
            else if (s.charAt(i) == ')') {
                res = res * stack.pop() + stack.pop(); // 2 + (res)
            }
            else if (s.charAt(i) == '+') {
                sign = 1;
            }
            else if (s.charAt(i) == '-') {
                sign = -1;
            }
            else {
                int num = 0;
                while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = num * 10 + s.charAt(i) - '0';
                    i ++;
                }
                i --;
                res = res + sign * num;
            }
        }
        return res;
    }
}
