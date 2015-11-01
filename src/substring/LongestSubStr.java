package substring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yingtan on 9/21/15.
 */
public class LongestSubStr {
    public int lengthOfLongestSubstringTwoDistinct(String s) {

        HashSet<Character> words = new HashSet<Character>();

        char[] chs = s.toCharArray();

        if (chs.length == 0) return 0;
        int i = 0;
        int j = 1;
        words.add(chs[i]);

        int maxLen = 1;
        int curLen = 1;

        while ((i < chs.length) && (j < chs.length)) {
            if (!words.contains(chs[j])) {
                words.add(chs[j]);
            }
            int distinctNum = words.size();
            if (distinctNum > 2) {
                maxLen = Math.max(maxLen, curLen);
                curLen = 1;
                words = new HashSet<Character>();
                i ++;
                words.add(chs[i]);
                j = i + 1;

            } else {
                curLen ++;
                j ++;
            }
        }
        maxLen = Math.max(maxLen, curLen);

        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubStr ob = new LongestSubStr();
        System.out.println(ob.lengthOfLongestSubstringTwoDistinct("ecfeee"));
    }
}
