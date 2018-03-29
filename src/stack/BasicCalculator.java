package stack;

/**
 * Created by yingtan on 1/20/18.
 *
 * 224. Basic Calculator
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Implement a basic calculator to evaluate a simple expression string.

 The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

 You may assume that the given expression is always valid.

 Some examples:
 "1 + 1" = 2
 " 2-1 + 2 " = 3
 "(1+(4+5+2)-3)+(6+8)" = 23
 */
import java.util.*;
public class BasicCalculator {
    // x + sign * y
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int res = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == ' ') continue;
            if (s.charAt(i) == '(') {
                stack.push(res);
                stack.push(sign);
                sign = 1;
                res = 0;
            }
            else if (s.charAt(i) == ')') {
                res = res * stack.pop() + stack.pop();
            }
            else if (s.charAt(i) == '+') {
                sign = 1;
            }
            else if (s.charAt(i) == '-'){
                sign = -1;
            }
            else {
                int num = 0;
                // digit
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = 10 * num + s.charAt(i) - '0';
                    i ++;
                }
                i --;
                res = res + num * sign;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        BasicCalculator ob = new BasicCalculator();
        ob.calculate(" 2-1 + 2 ");
    }
}
