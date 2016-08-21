package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by yingtan on 10/5/15.
 */
/*Given a pattern and a string str, find if str follows the same pattern.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
Both pattern and str contains only lowercase alphabetical letters.
Both pattern and str do not have leading or trailing spaces.
Each word in str is separated by a single space.
Each letter in pattern must map to a word with length that is at least 1.
* */
public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        if ( (pattern == null) && (str == null)) return true;
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        StringTokenizer tokenier = new StringTokenizer(str, " ");
        int i = 0;
        while (tokenier.hasMoreTokens()) {
            String token = tokenier.nextToken();
            if (i < pattern.length()) {
                char ch = pattern.charAt(i);
                if (map.containsKey(ch)) {
                    String string = map.get(ch);
                    if ( ! string.equals(token)) {
                        return false;
                    }
                }
                else {
                    if (set.contains(token)) return false;
                    map.put(ch,token);
                }
            }
            else return false;
            set.add(token);
            i ++;
        }
        if (i < pattern.length()) return false;
        return true;
    }
}
