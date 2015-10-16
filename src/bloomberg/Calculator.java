package bloomberg;

import java.util.Stack;

/**
 * Created by max2 on 10/16/15.
 */
/*
* Only use + - * / ( )
*
*
* */
public class Calculator {

    // Solution 1: no extra space
    public int calculate(String expression) {
        char[] chs = expression.toCharArray();
        Stack<String> stack = new Stack<String>();
        stack.push("$");
        String operator = "";
        for (int i = 0; i < chs.length; i++) {
            int digit = chs[i] - '0';
            if ((digit <= 9) && (digit >= 0))
                operator = operator + digit;
            else {
                if (operator.length() > 0) {
                    if (!stack.isEmpty()) {
                        String operation = stack.peek();
                        if (operation.equals("*")) {
                            stack.pop();
                            if (!stack.isEmpty()) {
                                int operator2 = Integer.parseInt(stack.pop());
                                stack.push((Integer.parseInt(operator) * operator2) + "");
                            }

                        } else if (operation.equals("/")) {
                            stack.pop();
                            if (!stack.isEmpty()) {
                                int operator2 = Integer.parseInt(stack.pop());
                                stack.push((Integer.parseInt(operator) / operator2) + "");
                            }

                        } else if (operation.equals(")")) {
                            stack.pop();
                            // iteration to do calculation until find "("
                            stack.push(recurCal(stack, "(") + "");
                        } else {
                            stack.push(operator);
                        }
                    }
                    operator = "";
                }
                stack.push(chs[i] + "");
            }
        }
        return recurCal(stack, "$");
    }

    public int recurCal(Stack<String> stack, String terminator) {
        if (!stack.isEmpty()) {
            int operator1 = Integer.parseInt(stack.pop());
            if (!stack.isEmpty()) {
                String op = stack.pop();
                if (op.equals(terminator)) return operator1;
                if (op.equals("+")) stack.push(operator1 + recurCal(stack, terminator) + "");
                else if (op.equals("-")) stack.push(recurCal(stack, terminator) - operator1 + "");
                else if (op.equals("*")) {
                    if (!stack.isEmpty()) {
                        int operator2 = Integer.parseInt(stack.pop());
                        stack.push(operator2 * operator1 + "");
                        recurCal(stack, terminator);
                    }
                } else if (op.equals("/")) {
                    if (!stack.isEmpty()) {
                        int operator2 = Integer.parseInt(stack.pop());
                        stack.push(operator2 / operator1 + "");
                        recurCal(stack, terminator);
                    }
                }
            }
        }
        return 0;
    }
}
