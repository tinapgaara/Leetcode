package bloomberg.calculator;

import java.util.*;

/**
 * Created by yingtan on 11/13/15.
 */
// （a+b）＊c＋（a+b）＋c，去除没用的括号

public class RemoveUsefullessParentheses {

    public String remove(String str) {
        char[] chs = str.toCharArray();
        Map<String, Integer> levels = new HashMap<>();
        levels.put("+",1);
        levels.put("-",1);
        levels.put("*",2);
        levels.put("/",2);

        Stack<String> stack = new Stack<String>();
        for (int i = 0 ; i < chs.length ; i ++) {
            stack.push(chs[i]+ "");
        }

        String prevOp = "";
        String res = "";
        while (!stack.isEmpty()) {
            String top = stack.pop();
            if (top.equals(")")) {
                res = removeParenthesis(res, prevOp, stack, levels);
            }
            else {
                res = top + res;
            }
            prevOp = top;
        }
        return res;
    }

    public String removeParenthesis(String res, String prevOp, Stack<String> stack, Map<String, Integer> levels) {
        String tmpStrInParenthesis = "";
        int level = 1;
        if (levels.containsKey(prevOp)) {
            level = levels.get(prevOp);
        }
        while ((!stack.isEmpty() && (!stack.peek().equals("(")))) {
            String top = stack.pop();
            if (top.equals(")")) {
                tmpStrInParenthesis = removeParenthesis(tmpStrInParenthesis, prevOp, stack, levels);
                continue;
            }
            if (levels.containsKey(top)) {
                int prevOpLevel = levels.get(top);
                if (prevOpLevel < level) { // encounter stronger symbol, can not remove paranthesis
                    res = top + tmpStrInParenthesis + ")" + res;
                    while ((!stack.isEmpty()) && (!stack.peek().equals("("))) {
                        res = stack.pop() + res;
                    }
                    if (!stack.isEmpty()) {
                        res = stack.pop() + res;
                    }
                    return res;
                }
            }
            tmpStrInParenthesis = top + tmpStrInParenthesis;
            prevOp = top;
        }
        if (!stack.isEmpty()) {
            stack.pop();
            res = tmpStrInParenthesis + res;
        }
        return res;
    }

    public static void main(String[] args) {
        RemoveUsefullessParentheses ob = new RemoveUsefullessParentheses();
        System.out.println(ob.remove("(a+b)*(c+(a+b)+c)"));
    }
}
