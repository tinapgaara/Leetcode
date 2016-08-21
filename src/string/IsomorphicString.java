package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 9/29/15.
 */
public class IsomorphicString {

    public boolean isIsomorphic(String s, String t) {
        if ( (s == null) || (t == null) || (s.length() == 0) || (t.length() == 0) )
            return true;
        if (s.length() !=  t.length() ) return false;

        int sLen = s.length();
        int tLen = t.length();
        Map<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0 ; i < sLen; i ++) {
            char sCh = s.charAt(i);
            char tCh = t.charAt(i);
            if (map.containsKey(sCh)) {
                if (map.get(sCh) != tCh)
                    return false;
            }
            else if (map.containsValue(tCh)) return false;
            else {
                map.put(sCh, tCh);
            }
        }
        return true;
    }
}
