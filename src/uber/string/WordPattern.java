package uber.string;

/**
 * Created by yingtan on 3/27/18.
 *
 * 290. Word Pattern
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a pattern and a string str, find if str follows the same pattern.

 Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

 Examples:
 pattern = "abba", str = "dog cat cat dog" should return true.
 pattern = "abba", str = "dog cat cat fish" should return false.
 pattern = "aaaa", str = "dog cat cat dog" should return false.
 pattern = "abba", str = "dog dog dog dog" should return false.
 Notes:
 You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.


 */
import java.util.*;
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || pattern.length() == 0 || str == null || str.length() == 0) return false;
        char[] patterns = pattern.toCharArray();
        String[] words = str.split(" ");
        if (patterns.length != words.length) return false;
        Map<Character, String> map = new HashMap<>();
        for(int i = 0 ; i < patterns.length; i ++) {
            char p = patterns[i];
            String s = words[i];
            //System.out.println(p + " " + s);
            if (map.containsKey(p)) {
                if (! map.get(p).equals(s)) return false;
            }
            else {
                if (! map.containsValue(s)) {
                    map.put(p, s);
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
}
