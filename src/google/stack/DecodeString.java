package google.stack;

import java.util.Stack;

/**
 * Created by yingtan on 7/23/17.
 */
public class DecodeString {

    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";
        Stack<Integer> repeatStack = new Stack<Integer>();
        Stack<StringBuilder> strStack = new Stack<StringBuilder>();
        int repeat = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if (ch == '[') {
                // keep record the repeat times of future string
                repeatStack.push(repeat);
                // keep record of previous string
                strStack.push(res);

                // reset
                res = new StringBuilder();
                repeat = 0;
            }
            else if (ch == ']') {
                // pop up
                // current part of string between the current [  ]
                String inBraceString = res.toString();
                // string before the current [   ]
                res = strStack.pop();
                int popedRepeat = repeatStack.pop();
                while (popedRepeat > 0) {
                    res.append(inBraceString);
                    popedRepeat --;
                }
            }
            else if (ch - '0' >= 0 && ch - '0' <= 9) {
                repeat = repeat * 10;
                repeat = repeat + (ch - '0');
            }
            else {
                // this is string's ch
                res.append(ch);
            }
        }
        return res.toString();
    }
}
