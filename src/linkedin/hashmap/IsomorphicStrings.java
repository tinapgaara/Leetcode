package linkedin.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/19/17.
 *
 * 205. Isomorphic Strings
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given two strings s and t, determine if they are isomorphic.

 Two strings are isomorphic if the characters in s can be replaced to get t.

 All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

 For example,
 Given "egg", "add", return true.

 Given "foo", "bar", return false.

 Given "paper", "title", return true.
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0 ; i < s.length(); i ++) {
            char s_ch = s.charAt(i);
            char t_ch = t.charAt(i);
            if (map.containsKey(s_ch)) {
                if (map.get(s_ch) != t_ch) return false;
            }
            else {
                // important !!!! t_ch must not exist as a value in map before
                if (map.containsValue(t_ch)) return false;
                else map.put(s_ch, t_ch);
            }
        }
        return true;
    }
}
