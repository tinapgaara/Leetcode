package stack;

/**
 * Created by yingtan on 1/20/18.
 *
 * 402. Remove K Digits
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

 Note:
 The length of num is less than 10002 and will be ≥ k.
 The given num does not contain any leading zero.
 Example 1:

 Input: num = "1432219", k = 3
 Output: "1219"
 Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 Example 2:

 Input: num = "10200", k = 1
 Output: "200"
 Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 Example 3:

 Input: num = "10", k = 2
 Output: "0"
 Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
import java.util.*;
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0) {
            return "";
        }
        Stack<Integer> stack = new Stack<>();
        int removeCount = k;
        for (int i = 0 ; i < num.length(); i ++) {
            int cur = num.charAt(i) - '0';
            if (stack.isEmpty()) {
                stack.push(num.charAt(i) - '0');
            }
            else {
                while(! stack.isEmpty() && cur < stack.peek() && removeCount > 0) {
                    removeCount --;
                    stack.pop();
                }
                stack.push(cur);
            }
        }
        String str = "";
        while(removeCount > 0 && ! stack.isEmpty()) {
            removeCount --;
            stack.pop();
        }
        if (stack.isEmpty()) {
            // all poped out
            return "0";
        }
        while(! stack.isEmpty()) {
            str = stack.pop() + str;// construct result
        }
        while (str.length() > 1 && str.charAt(0) == '0') {
            str = str.substring(1); // remove all zeros at the string begining
        }
        return str;
    }
}
