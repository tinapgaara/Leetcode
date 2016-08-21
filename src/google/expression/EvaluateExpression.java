package google.expression;

import java.util.Stack;

/**
 * Created by yingtan on 11/26/15.
 */
/*
* ，evaluate expression,比如"(+ 7 81 2)"，返回90。格式必须严格match（数之间只能有一个空格），不match就
throw exception。完了之后让evaluate复杂的表达式，比如"(* 7 (+ 1 2))"，返回21。也必须check格式，递归。
* */
public class EvaluateExpression {

    public int evaluate(String expression) {

        String[] strs = expression.split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = 0 ; i < strs.length; i ++) {
            String term = strs[i];
            if (term.equals("+") || (term.equals("-")) || (term.equals("*")) || (term.equals("/"))) {
                stack.push(term);
            }
            else { // number
                recurCalculate(stack, term);
            }
        }
        if (!stack.isEmpty()) {
            if (stack.size() > 1) {
                String term = stack.pop();
                recurCalculate(stack, term);
            }
            return Integer.parseInt(stack.pop());
        }
        return 0;
    }

    public void recurCalculate(Stack<String> stack, String term) {
        if (! stack.isEmpty()) {
            String peek = stack.peek();
            if (peek.equals("+") || (peek.equals("-")) || (peek.equals("*")) || (peek.equals("/"))) {
                stack.push(term);
            } else { // number
                stack.pop();
                try {
                    Integer operator2 = Integer.parseInt(term);
                    Integer operator1 = Integer.parseInt(peek);

                    if (!stack.isEmpty()) {
                        String operation = stack.pop();
                        if (operation.equals("+")) {
                            int newTerm = operator1 + operator2;
                            recurCalculate(stack, newTerm + "");
                        } else if (operation.equals("-")) {
                            int newTerm = operator1 - operator2;
                            recurCalculate(stack, newTerm + "");
                        } else if (operation.equals("*")) {
                            int newTerm = operator1 * operator2;
                            recurCalculate(stack, newTerm + "");
                        } else if (operation.equals("/")) {
                            int newTerm = operator1 / operator2;
                            recurCalculate(stack, newTerm + "");
                        } else {
                            throw new java.lang.NumberFormatException();
                        }
                    }
                    else {
                        throw new java.lang.NumberFormatException();
                    }
                } catch ( java.lang.NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            stack.push(term);
        }
    }

    public static void main(String[] args) {
        EvaluateExpression ob = new EvaluateExpression();
        String str = "- * 12 + 2 3 14";
        System.out.println(ob.evaluate(str));
    }
}
