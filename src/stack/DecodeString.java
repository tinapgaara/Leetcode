package stack;

/**
 * Created by yingtan on 1/20/18.
 *
 * 94. Decode String
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an encoded string, return it's decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

 */
import java.util.*;
public class DecodeString {

    // s = str + number* str
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";
        Stack<String> stack = new Stack<>();
        String curstr = "";
        int number  = 0;
        for (int i = 0 ; i < s.length(); i ++) {
            if (s.charAt(i) == '[') {
                stack.push(curstr);
                stack.push(number + "");
                number = 0;
                curstr = "";
            }
            else if (s.charAt(i) == ']') {
                int repeat = Integer.parseInt(stack.pop());
                String str = "";
                for (int j = 0 ; j < repeat; j ++) {
                    str = str + curstr;
                }
                curstr = stack.pop() + str;
            }
            else if (Character.isDigit(s.charAt(i))){
                int count = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    count = count * 10 + s.charAt(i) - '0';
                }
                i --;
                number = count;
            }
            else {
                curstr = curstr + s.charAt(i);
            }
        }
        return curstr;
    }
}
