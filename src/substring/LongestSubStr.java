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
        /*
        for (int i = 0 ; i < s.length(); i ++) {
            words.add(s.charAt(i));
        }
        */
        char[] chs = s.toCharArray();

        if (chs.length == 0) return 0;
        int low = 0;
        int high = 1;
        words.add(chs[low]);

        int distinctNum = 1;
        int maxLen = 1;
        while ( (high < s.length()) && (low < high) ) {
            if ( words.contains(chs[high])) {
                high ++;
            }
            else {
                distinctNum ++;
                if (distinctNum > 2) {
                    int len = high - low;
                    maxLen = Math.max(maxLen, len);
                    System.out.println("index:"+low+", words:"+words);
                    low ++;
                    high = low + 1;

                    words = new HashSet<>();
                    if (low < s.length()) words.add(chs[low]);
                    distinctNum = 1;
                }
                else {
                    words.add(chs[high]);
                    high ++;
                }
            }
        }

        if (distinctNum <= 2) {
            maxLen = Math.max(maxLen, high - low);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubStr ob = new LongestSubStr();
        System.out.println(ob.lengthOfLongestSubstringTwoDistinct("ecfeee"));
    }
}
