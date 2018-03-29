package uber.hashmap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yingtan on 12/16/17.
 */
public class MinWindowSubstring {

    public String minWindow(String s, String t) {
        if (s == null || t == null) return "";
        char[] tchs = t.toCharArray();
        Map<Character, Integer> chsToCover = new HashMap<>();
        for (char tch : tchs) {
            chsToCover.put(tch, chsToCover.containsKey(tch) ? chsToCover.get(tch) + 1 : 1);
        }
        int remainToCoverTotal = t.length();
        int left = 0;
        int right = 0;
        String minWindow = "";
        int min = Integer.MAX_VALUE;
        // keep extending righter until S convers T
        for (; right < s.length(); right ++) {
            char addedCh = s.charAt(right);
            if (chsToCover.containsKey(addedCh)) {
                // this is the ch we want to cover in T
                int remainToCoverForCh = chsToCover.get(addedCh);
                // cover this ch for 1 time
                // count can be negative here, such as T: [A, B, C] , S:[A, B, B, C]. <B: -1>
                chsToCover.put(addedCh, remainToCoverForCh - 1);
                // which case will affect the S contains all T ? When remainToCoverForCh >0, but now -1.
                if (remainToCoverForCh > 0) {
                    remainToCoverTotal --;
                }
            }
            while(remainToCoverTotal == 0) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    minWindow = s.substring(left, right + 1);
                }
                // keep smalling window
                char removedCh = s.charAt(left);
                if (chsToCover.containsKey(removedCh)) {
                    // remove this ch from s window
                    int remainToCoverForCh = chsToCover.get(removedCh);
                    chsToCover.put(removedCh, remainToCoverForCh + 1);
                    // which case will affect the S does not contains all T ? When remainToCoverForCh >=0, but now +1.
                    // remainToCoverForCh can be negative, this means, S have multiple chs than T.
                    // We only care about remainToCoverForCh==0, or remainToCoverForCh >0
                    if (remainToCoverForCh >= 0) {
                        remainToCoverTotal ++;
                    }
                }
                left ++;
            }
        }
        return minWindow;
    }

    public String minWindow_stream(String s, String t) {
        if (s == null || t == null) return "";
        char[] tchs = t.toCharArray();
        Map<Character, Integer> chsToCover = new HashMap<>();
        for (char tch : tchs) {
            chsToCover.put(tch, chsToCover.containsKey(tch) ? chsToCover.get(tch) + 1 : 1);
        }
        String minWindow = "";
        int min = Integer.MAX_VALUE;
        int numOfChsInTSeenSoFar = 0;
        LinkedHashMap<Character, Integer> chCoverToIndex = new LinkedHashMap<>(t.length(), 1.0f, true);
        for (Character ch : t.toCharArray()) {
            chCoverToIndex.put(ch, null);
        }
        // in streaming way
        // can not work when T contains multiple chs
        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            // this is ch we want to cover
            if (chCoverToIndex.containsKey(ch)) {
                Integer index = chCoverToIndex.get(ch);
                if (index == null) {
                    // this is new ch we want to cover
                    numOfChsInTSeenSoFar ++;
                }
                chCoverToIndex.put(ch, i);

                if (numOfChsInTSeenSoFar == t.length()) {
                    // already covered T

                    int startPos = getValueForFirstEntry(chCoverToIndex);
                    if (i - startPos + 1 < min) {
                        minWindow = s.substring(startPos, i + 1);
                        min = i - startPos + 1;
                    }
                }
            }
        }
        return minWindow;
    }

    public Integer getValueForFirstEntry(Map<Character, Integer> chsToCover) {
        for (Map.Entry<Character, Integer> entry : chsToCover.entrySet()) {
            return entry.getValue();
        }
        return null;
    }
}
