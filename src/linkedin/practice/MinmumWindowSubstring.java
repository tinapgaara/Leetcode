package linkedin.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/22/17.
 */
public class MinmumWindowSubstring {

    public String minWindow(String s, String t) {
        if ((s == null) || (s.length() == 0) || (t == null) || (t.length() == 0))
            return "";
        Map<Character, Integer> tcount = new HashMap<>();
        Map<Character, Integer> scount = new HashMap<>();
        for (int i = 0 ; i < t.length(); i ++) {
            char ch = t.charAt(i);
            if (tcount.containsKey(ch)) {
                tcount.put(ch, tcount.get(ch) + 1);
            }
            else {
                tcount.put(ch, 1);
            }
        }
        int start = 0;
        int end = 0;
        int minlen = 0;
        String res = "";
        while(end < s.length()) {
            char ch = s.charAt(end);
            if (scount.containsKey(ch)) {
                scount.put(ch, scount.get(ch) + 1);
            }
            else {
                scount.put(ch, 1);
            }
            while (start <= end && cover(scount, tcount)) {
                int curlen = end - start + 1;
                if (curlen < minlen) {
                    minlen = curlen;
                    res = s.substring(start, end + 1);
                }
                // adjust start
                char removedCh = s.charAt(start);
                if (scount.get(removedCh) > 1) {
                    scount.put(removedCh, scount.get(removedCh) - 1);
                }
                else {
                    scount.remove(removedCh);
                }
                start ++;
            }
            end ++;
        }
        return res;
    }

    public boolean cover(Map<Character, Integer> scount, Map<Character, Integer> tcount) {
        for(Map.Entry<Character, Integer> entry : tcount.entrySet()) {
            if (scount.containsKey(entry.getKey())) {
                if (scount.get(entry.getKey()) < entry.getValue()) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return true;
    }
}
