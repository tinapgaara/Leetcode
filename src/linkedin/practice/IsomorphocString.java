package linkedin.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/22/17.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.

 All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

 For example,
 Given "egg", "add", return true.

 Given "foo", "bar", return false.

 Given "paper", "title", return true
 */
public class IsomorphocString {

    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) return false;
        Map<Character, Character> map = new HashMap<>();
        if (s.length() != t.length()) return false;
        for (int i = 0 ; i < s.length(); i ++) {
            char ch_s = s.charAt(i);
            char ch_t = t.charAt(i);
            // case 1
            if (map.containsKey(ch_s)) {
                if(map.get(ch_s) != ch_t) return false;
            }
            // case 2
            else {
                if (map.containsValue(ch_t)) {
                    return false;
                }
                map.put(ch_s, ch_t);
            }
        }
        return true;
    }
}
