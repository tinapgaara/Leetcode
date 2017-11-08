package google.stack;

import java.util.Stack;

/**
 * Created by max2 on 11/10/15.
 */
/*
* Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ),
the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
*
* */
public class BasicCalculator {
    /*
    *
    * (1+(4+5+2)-3)+(6+8)
    * ->
    * 0 +1 * (1*1 +1 * (1*4 +1 * 5 +1*2) +1* (-1*3) +1*(1*6 +1*8)
    *
    * */
    public int calculate_easy(String s) {
        int len = s.length(), sign = 1, result = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += sum * sign;
            } else if (s.charAt(i) == '+')
                sign = 1;
            else if (s.charAt(i) == '-')
                sign = -1;
            else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                result = result * stack.pop() + stack.pop();// result * sign + prevsum
            }

        }
        return result;
    }

    public int calculate(String s) {
        if ((s == null) || (s.length() == 0)) return 0;
        s = s.trim();

        Stack<String> stack = new Stack<String>();
        String num = "";
        for (int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);
            if ((ch == '+') || (ch == '-')) {
                if (num.length() > 0) {
                    int nNum = Integer.parseInt(num);
                    num = "";
                    stack.push(nNum + "");
                }
                stack.push(ch+"");

            }
            else if (ch == '(') {
                stack.push(ch + "");
            }
            else if (ch == ')') {
                if (num.length() > 0) {
                    int nNum = Integer.parseInt(num);
                    num = "";
                    stack.push(nNum + "");
                }
                // pop
                int sum = calculateSum(stack);
                stack.push(sum+"");
            }
            else if ((ch >= 48) && (ch <= 57)){
                num = num + ch;
            }
        }
        if (num.length() > 0) {
            stack.push(num);
        }
        if (!stack.isEmpty()) {
            int sum = calculateSum(stack);
            return sum;
        }

        return 0;

    }

    //(1+(4+5+2
    public int calculateSum(Stack<String> stack) {
        // ( 2- 1+ 1   or  2+1+1
        if (stack.isEmpty()) return 0;

        else if (stack.size() == 1) {
            return Integer.parseInt(stack.pop()); // may exception
        }
        else if (stack.size() > 1) {
            int num2 = Integer.parseInt(stack.pop()); // may exception
            String op = stack.pop();
            if (op.equals("-")) {
                return calculateSum(stack) - num2;
            }
            else if (op.equals("+")){ // +  or (
                return calculateSum(stack) + num2;
            }
            else if (op.equals("(")) { // Important !!!
                return num2;
            }
        }
        System.out.println("Not Valid operation Input !");
        return 0;
    }

    public static void main(String[] args) {
        BasicCalculator ob = new BasicCalculator();
        System.out.println(ob.calculate_easy("(1+(4+5+2-4)+12-3)+(6+8)"));
    }

}
