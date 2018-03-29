package uber.backtrack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 12/2/17.
 */
public class WordPatternII {

    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null || str == null) return false;
        Map<Character, String> pattterns = new HashMap<>();
        return isMatch(pattern, 0, str, 0, pattterns);
    }

    public boolean isMatch(String pattern, int pIndex, String str, int sIndex, Map<Character, String> patterns) {
        // base case
        if (pIndex == pattern.length() && sIndex == str.length()) {
            for (Map.Entry<Character, String> entry : patterns.entrySet()) {
                System.out.println(entry.getKey() + ", " + entry.getValue());
            }
            return true;
        }
        else if (pIndex == pattern.length()) {
            return false;
        }
        else if (sIndex == str.length()) {
            return false;
        }
        Character curPattern = pattern.charAt(pIndex);
        String matchedStr = "";
        if (patterns.containsKey(curPattern)) {
            matchedStr = patterns.get(curPattern);
            //  important !!!!! range && equals here
            if (sIndex + matchedStr.length() <= str.length()) {
                if (matchedStr.equals(str.substring(sIndex, matchedStr.length() + sIndex))) {
                    int nextSIndex = sIndex + matchedStr.length();
                    return isMatch(pattern, pIndex + 1, str, nextSIndex, patterns);
                }
            }
            else {
                return false;
            }
        }
        else {
            for (int i = sIndex; i < str.length(); i ++) {
                matchedStr = matchedStr + str.charAt(i);
                // because this is new new pattern, make sure the matched string does not appear as value in map as well, use continue
                // important !! for boundary case : ab -> aa
                if(patterns.containsValue(matchedStr)) {
                    continue;
                }
                patterns.put(curPattern, matchedStr);
                if (isMatch(pattern, pIndex + 1, str, i + 1, patterns)) return true;
                patterns.remove(curPattern);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordPatternII ob = new WordPatternII();
        System.out.println(ob.wordPatternMatch("aa", "ab"));
    }

}
