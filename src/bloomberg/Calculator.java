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
        stack.push("(");
        String operator = "";
        for (int i = 0; i < chs.length; i++) {
            int digit = chs[i] - '0';
            if ((digit <= 9) && (digit >= 0))
                operator = operator + digit;
            else {
                if (operator.length() > 0) {
                    if (!stack.isEmpty()) {
                        String operation = stack.peek();
                        if (chs[i] == ')') {
                            stack.push(operator);
                            stack.push(recurCal(stack, "(") + "");
                            operator = "";
                            continue;
                        }
                        else if (operation.equals("*")) {
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

                        } else {
                            stack.push(operator);
                        }
                    }
                    operator = "";
                }
                stack.push(chs[i] + "");
            }
        }
        if (operator.length() > 0) stack.push(operator);
        int res = 0;
        while (!stack.isEmpty()) {
            res = recurCal(stack, "(");
            if (!stack.isEmpty()) {
                stack.push(res+"");
            }
        }
        return res;
    }

    public int recurCal(Stack<String> stack, String terminator) {
        if (!stack.isEmpty()) {
            String str = stack.pop();
            if (str.equals(")")) return recurCal(stack, terminator);
            else {
                int nInt = Integer.parseInt(str);
                if ((nInt >= 0)) {
                    System.out.println(str);
                    int operator1 = nInt;
                    if (!stack.isEmpty()) {
                        String op = stack.pop();
                        if (op.equals(terminator)) return operator1;
                        if (op.equals("+")) {
                            int val = operator1 + recurCal(stack, terminator);
                            //stack.push(val + "");
                            return val;
                        } else if (op.equals("-")) {
                            int val = recurCal(stack, terminator) - operator1;
                            //stack.push(val + "");
                            return val;
                        } else if (op.equals("*")) {
                            if (!stack.isEmpty()) {
                                int operator2 = Integer.parseInt(stack.pop());
                                stack.push(operator2 * operator1 + "");
                                return recurCal(stack, terminator);
                            }
                        } else if (op.equals("/")) {
                            if (!stack.isEmpty()) {
                                int operator2 = Integer.parseInt(stack.pop());
                                stack.push((operator2 / operator1) + "");
                                return recurCal(stack, terminator);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Calculator ob = new Calculator();
        ob.calculate("(2+3*4/(1+2/1))");
    }
}