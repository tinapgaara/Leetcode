package DP;

import java.util.Stack;

/**
 * Created by yingtan on 10/13/15.
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if ((s == null) || (s.length() == 0) || (s.length() == 1))  return 0;
        char[] chs = s.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        int[][] substrLen = new int[s.length()][s.length()];
        stack.push(0);
        int maxPairs = 0;
        for (int i =1; i < s.length(); i ++) {
            if (!stack.isEmpty()) {
                int topIndex = stack.peek();

                if ((chs[topIndex] == '(') && (chs[i] == ')')) {
                    int index = stack.pop();
                    if (i - index > 1) substrLen[index][i] = substrLen[index+1][i-1]+1;
                    else substrLen[index][i] = 1;

                    maxPairs = Math.max(maxPairs, substrLen[index][i]);
                }
                else {
                    if (i - topIndex > 1) substrLen[topIndex][i] = substrLen[topIndex+1][i-1];
                    else substrLen[topIndex][i] = 0;

                    maxPairs = Math.max(maxPairs, substrLen[topIndex][i]);
                    stack.push(i);
                }
            } else stack.push(i);
        }
        return maxPairs * 2;
    }
}
