package stack;

import java.util.*;

/**
 * Created by yingtan on 9/21/15.
 */
public class Calculator {

    // Solution 1:
    // So slow, can be faster
    public int calculate(String s) {
        List<String> list = breakString(s);
        Stack<String> stack = new Stack<String>();

        if (list.size() == 0) return 0;
        stack.push(list.get(0));

        for (int i = 1 ; i < list.size(); i ++) {
            String top = stack.peek();
            String ch = list.get(i);
            switch (ch) {
                case "+":
                    stack.push("+");
                    break;
                case "-":
                    stack.push("-");
                    break;
                case " ":
                    break;
                case "(":
                    stack.push("(");
                    break;
                case ")":
                    calVal(stack);
                    break;
                default:
                    operate(stack, ch, top);
                    break;
            }
        }
        return Integer.parseInt(stack.peek());
    }

    public void calVal(Stack<String> stack) {
        int var = Integer.parseInt(stack.pop());
        stack.pop();
        if (stack.size() == 0) {
            stack.push(var+"");
            return;
        }
        else
            operate(stack, var+"", stack.peek());
    }

    public void operate(Stack<String> stack, String curNum, String top) {
        if (stack.size() == 0)
            return;

        if ((! top.equals("+")) && ( ! top.equals("-"))) {
            stack.push(curNum);
            return;
        }
        while ((top.equals("+")) || top.equals("-")) {
            if (top.equals("+")) {
                stack.pop();
                int res = (Integer.parseInt(stack.pop()) + Integer.parseInt(curNum));
                if (stack.size()  == 0) {
                    stack.push(res + "");
                    break;
                }
                else {
                    top = stack.peek();
                    stack.push(res + "");
                }
            } else if (top.equals("-")) {
                stack.pop();
                int res = (Integer.parseInt(stack.pop()) - Integer.parseInt(curNum));
                if (stack.size()  == 0) {
                    stack.push(res + "");
                    break;
                }
                else {
                    top = stack.peek();
                    stack.push(res + "");
                }
            }
        }
    }

    public List<String> breakString(String s) {
        s = s.replaceAll(" ", "");
        List<String> list = new ArrayList<>();
        String tmp = "";
        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if ((ch >= 48) && (ch <= 57)) {
                tmp = tmp + ch;
            }
            else if ((ch == 40) || (ch == 41) || (ch == 43) || (ch == 45) ) {
                if (tmp.length() > 0) {
                    list.add(tmp);
                    tmp = "";
                }

                list.add(ch+"");
            }
        }
        if (tmp.length() > 0)
            list.add(tmp);
        return list;
    }

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("hello");
        set.add("hello");

        Calculator cl = new Calculator();
        set.remove("hello");
        System.out.println(set.size());
    }

}
