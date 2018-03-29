package uber.stack;

import java.util.Stack;

/**
 * Created by erict on 2017/11/10.
 */
public class Solution {

    private int m_nextStartIndex;  // 指向右括号后的第一个字符的位置

    public int calculate(String expStr) {
        m_nextStartIndex = -1;  // 初始化，-1表示尚未遇到右括号
        return _calculate(expStr, 0);
    }

    private int _calculate(String expStr, int startIndex) {
        if (expStr == null) return 0;
        int len = expStr.length();
        if (len <= startIndex) return 0;

        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        char curCh;
        int curIndex = startIndex;
        while (curIndex < len) {
            curCh = expStr.charAt(curIndex);

            // 递归地处理括号
            if (curCh == '(') {
                num = _calculate(expStr, curIndex + 1);
                curIndex = m_nextStartIndex;
                continue;
            }
            else if (curCh == ')') {
                m_nextStartIndex = curIndex + 1; // 指向右括号后的第一个字符的位置
                break; // 跳出循环，到后面的for循环 计算结果并返回
            }

            // 计算数字字符串对应的整数值
            if (Character.isDigit(curCh)) num = (num * 10) + (curCh - '0');

            if ( (! Character.isDigit(curCh) && ' ' != curCh) || (curIndex == len - 1) ) {
                // 这里'+'，'-'是前面记录下来的在 num 之前出现的运算符，
                // 因为它们的优先级低于乘除，所以暂时不能运算，需要送入stack
                if (sign == '-') stack.push(-num);
                if (sign == '+') stack.push(num);

                // 这里'*'，'/'是前面记录下来的在 num 之前出现的运算符，
                // 因为它们的优先级高，所以可以与之前压入stack的stack top进行运算
                if (sign == '*') stack.push(stack.pop() * num);
                if (sign == '/') stack.push(stack.pop() / num);

                sign = curCh;  // 当前字符成为 后续数字字符串 之前的运算符
                num = 0;  // 当前的数字字符串已经完结，所以num应该清0
            }
            curIndex++;
        }

        int result = 0;
        // 这里计算stack中数据的sum；注意减号'-'已经在压栈时处理为负数，所以这里做简单的加法即可
        for (int i: stack) result += i;
        return result;
    }
}
