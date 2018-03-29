package linkedin.practice;

import java.util.Stack;

/**
 * Created by yingtan on 11/22/17.
 *
 * Some examples:
 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            if (token.equals("+")) {
                int sum = stack.pop() + stack.pop();
                stack.push(sum);
            }
            else if (token.equals("-")) {
                int sum = -1 * stack.pop() + stack.pop();
                stack.push(sum);
            }
            else if (token.equals("*")) {
                int sum = stack.pop() * stack.pop();
                stack.push(sum);
            }
            else if (token.equals("/")) {
                int divide = stack.pop();
                int sum = stack.pop() / divide;
                stack.push(sum);
            }
            else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
