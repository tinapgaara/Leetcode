package uber.string;

import java.util.*;

/**
 * Created by yingtan on 11/7/17.
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

 Given "paper", "title", return true
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
                // t_ch must not exist as a value in map before
                // important !!!!
                if (map.containsValue(t_ch)) return false;
                else map.put(s_ch, t_ch);
            }
        }
        return true;
    }

    public List<List<String>> groupIsomorphic(List<String> strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String str : strs) {
            int diffChsIndex = 0;
            Map<Character, Integer> map = new HashMap<>();
            String newstr = "";
            for (int i = 0 ; i < str.length(); i ++) {
                char ch = str.charAt(i);
                if(! map.containsKey(ch)) {
                    diffChsIndex ++;
                    newstr = newstr + diffChsIndex;
                    map.put(ch, diffChsIndex);
                }
                else {
                    newstr = newstr + map.get(ch);
                }
            }
            if (groupMap.containsKey(newstr)) {
                groupMap.get(newstr).add(str);
            }
            else {
                List<String> list = new ArrayList<>();
                list.add(str);
                groupMap.put(newstr, list);
            }
        }
        for (List<String> entry : groupMap.values()) {
            res.add(entry);
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> res = new ArrayList<>(Arrays.asList("title", "paper", "egg", "add", "hello", "billy"));
        IsomorphicStrings ob = new IsomorphicStrings();
        System.out.println(ob.groupIsomorphic(res));
    }
}
