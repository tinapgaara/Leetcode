package divideConquer;

import java.util.Stack;

/**
 * Created by yingtan on 9/28/15.
 */
public class BasicCalculatorII {

    // Solution 2; use stack instead of recursion
    // push digit + operator, do operation based on previous operator(pop(), pop()).
    // when detect previous operator is + - and current operator is + -. Then do calculation and push to stack
    // when detect current operation is * /, find next digit, and use current digit and next digit to calculation
    public int calculate(String s) {
        if ((s == null) || (s.length() == 0)) return 0;
        int i = 0;
        int j = 0;
        s = s.replace(" ", "");
        String val = "";
        int prevVal = 0;
        Stack<Integer> stack = new Stack<Integer>();
        while ((i < s.length()) && (j < s.length())) {
            char ch = s.charAt(i);
            if ((ch == '+') || (ch == '-')) {
                Integer top = null;
                if ( ! stack.isEmpty())  top = stack.peek();
                if (top != null) {
                    int topValue = top.intValue();
                    if ((topValue == '+') || (topValue == '-') && (stack.size() >= 2)) {
                        stack.pop();
                        Integer op = stack.pop();
                        if (val.length() > 0) {
                            if (topValue == '+') stack.push(Integer.parseInt(val) + op.intValue());
                            else stack.push(op.intValue() - Integer.parseInt(val));
                        }
                    }
                }
                else if (val.length() > 0) stack.push(Integer.parseInt(val));
                stack.push((int) ch);
                val = "";
                i++;
            } else if ((ch == '*') || (ch == '/')) {
                prevVal = Integer.parseInt(val);
                val = "";
                j = i + 1;
                while (j < s.length()) {
                    char ch_next = s.charAt(j);
                    if ((ch_next == '+') || (ch_next == '-') || (ch_next == '*') || (ch_next == '/')) {
                        i = j;
                        break;
                    } else {
                        val = val + ch_next;
                    }
                    j++;
                }
                if (val.length() > 0) {
                    if (ch == '*') val = (Integer.parseInt(val) * prevVal) + "";
                    else val = (prevVal / Integer.parseInt(val)) + "";
                    prevVal = 0;
                }
            } else {
                val = val + ch;
                i++;
            }
        }
        if (val.length() > 0) {
            stack.push(Integer.parseInt(val));
        }
        int res = 0;
        while ( ! stack.isEmpty()) {
            int op_2 = stack.pop();
            res = op_2;
            if (stack.size() >= 2) {
                int op = stack.pop();
                int op_1 = stack.pop();
                if (op == '+') stack.push(op_2 + op_1);
                else stack.push(op_1 - op_2);

            }
        }
        return res;
    }

    // Solution 1: out of heap, can not use recursion
    public int calculate_2(String s) {
        if ((s == null) || (s.length() == 0)) return 0;
        int i = 0;
        int j = 0;
        s = s.replace(" ", "");
        String val = "";
        boolean ifStop = true;
        while (i < s.length()) {
            if ((s.charAt(i) == '+') || (s.charAt(i) == '-') || (s.charAt(i) == '*')
                    || (s.charAt(i) == '/')) {
                j = i;
                ifStop = false;
                break;
            } else {
                val = val + s.charAt(i);
            }
            i++;
        }
        if (ifStop) return Integer.parseInt(val);
        while (j < s.length()) {
            char op = s.charAt(j);
            if (op == ' ') {
                j++;
                continue;
            }
            if ((op == '+') || (op == '-')) {
                int nextOperator = calculate(s.substring(j + 1, s.length()));
                // System.out.println(nextOperator);
                if (op == '+') return (Integer.parseInt(val) + nextOperator);
                else if (op == '-') return (Integer.parseInt(val) - nextOperator);
                j++;
            } else {
                i = j + 1;
                String anotherval = "";
                ifStop = true;
                while (i < s.length()) {
                    if ((s.charAt(i) == '+') || (s.charAt(i) == '-') || (s.charAt(i) == '*')
                            || (s.charAt(i) == '/')) {
                        j = i;
                        ifStop = false;
                        break;
                    } else {
                        anotherval = anotherval + s.charAt(i);
                    }
                    i++;
                }
                if (op == '*')
                    val = Integer.parseInt(val) * Integer.parseInt(anotherval) + "";
                else if (op == '/')
                    val = Integer.parseInt(val) / Integer.parseInt(anotherval) + "";

                if (ifStop)
                    return Integer.parseInt(val);
            }
        }
        return Integer.parseInt(val);
    }

    public static void main(String[] args) {
        BasicCalculatorII ob = new BasicCalculatorII();
        System.out.println(ob.calculate_2(" 3+5 +2 + 4 /3 "));
        String s = "a";
        s = s.substring(2,1);
        System.out.println(s.length());
    }
}
