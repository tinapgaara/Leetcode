package linkedin.stack;

import java.util.Stack;

/**
 * Created by yingtan on 11/20/17.
 *
 * 150. Evaluate Reverse Polish Notation
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Evaluate the value of an arithmetic expression in Reverse Polish Notation.

 Valid operators are +, -, *, /. Each operand may be an integer or another expression.

 Some examples:
 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            if (token.equals("+")) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(num1 + num2);
            }
            else if (token.equals("-")) {
                Integer num2 = stack.pop();
                Integer num1 = stack.pop();
                stack.push(num1 - num2);
            }
            else if (token.equals("*")) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(num1 * num2);
            }
            else if (token.equals("/")) {
                Integer num2 = stack.pop();
                Integer num1 = stack.pop();
                stack.push(num1 / num2);
            }
            else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }
}
