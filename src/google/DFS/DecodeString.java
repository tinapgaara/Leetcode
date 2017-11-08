package google.DFS;

/**
 * Created by yingtan on 1/16/17.
 *
 * Given an encoded string, return it's decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {

    public String recurDecode(String s, int[] pointer) {
        int index = pointer[0];
        String res = "";
        String times = "";
        while (index  < s.length()) {
            char ch = s.charAt(index);
            // ch is digit
            if ( ( (ch - '0') >= 0 ) && ( (ch - '0') <= 9) ) {
                times = times + ch;
            }
            else if (ch == '[') {
                pointer[0] = index + 1;
                String innerStr = recurDecode(s, pointer);
                index = pointer[0];

                int repeat = Integer.parseInt(times);
                for (int i = 0 ; i < repeat; i ++) {
                    res = res + innerStr;
                }
                // reset
                times = "";
            }
            else if (ch == ']') {
                pointer[0] = index;
                return res;
            }
            else {
                res = res + ch;
            }
            index ++;
        }
        pointer[0] = s.length() - 1;
        return res;
    }

    public String decodeString(String s) {
        if  ( s == null) return null;
        int[] pointer = new int[1];
        pointer[0] = 0;
        return recurDecode(s, pointer);
    }
}
