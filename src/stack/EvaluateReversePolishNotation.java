package stack;

/**
 * Created by yingtan on 1/20/18.
 *
 * 150. Evaluate Reverse Polish Notation
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Evaluate the value of an arithmetic expression in Reverse Polish Notation.

 Valid operators are +, -, *, /. Each operand may be an integer or another expression.

 Some examples:
 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
import java.util.*;
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        Stack<Integer> res = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                Integer num1 = res.pop();
                Integer num2 = res.pop();
                res.push(num1 + num2);
            }
            else if (token.equals("-")) {
                Integer num2 = res.pop();
                Integer num1 = res.pop();
                res.push(num1 - num2);

            }
            else if (token.equals("*")) {
                Integer num1 = res.pop();
                Integer num2 = res.pop();
                res.push(num1 * num2);
            }
            else if (token.equals("/")) {
                Integer num2 = res.pop();
                Integer num1 = res.pop();
                res.push(num1 / num2);
            }
            else {
                res.push(Integer.parseInt(token));
            }
        }
        return res.pop();
    }
}
