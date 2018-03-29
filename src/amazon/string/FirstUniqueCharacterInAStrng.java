package amazon.string;

import java.util.Arrays;

/**
 * Created by yingtan on 3/25/18.
 *
 * 387. First Unique Character in a String
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

 Examples:

 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.
 */
public class FirstUniqueCharacterInAStrng {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        Integer[] index = new Integer[26];
        Arrays.fill(index, -1);
        for (int i= 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            int ind = ch - 'a';
            if (index[ind] != null) {
                if (index[ind] == -1) {
                    // first time
                    index[ind] = i;
                }
                else {
                    // second time
                    index[ind] = null;
                }
            }
        }
        int min = s.length();
        for (int i = 0 ; i < 26;i ++) {
            if (index[i] != null && index[i] != -1) {
                min = Math.min(min, index[i]);
            }
        }
        if (min == s.length()) return -1;
        return min;
    }
}
