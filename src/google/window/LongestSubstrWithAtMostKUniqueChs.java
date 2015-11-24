package google.window;

/**
 * Created by yingtan on 11/23/15.
 */
/*
* The problem can be solved in O(n).
* Idea is to maintain a window and add elements to the window till it
* contains less or equal k, update our result if required while doing so.
* If unique elements exceeds than required in window, start removing the elements
* from left side.
* */
public class LongestSubstrWithAtMostKUniqueChs {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        int[] count = new int[256]; // for case no limitation of characters
        int low = 0;
        int high = 0;
        int maxLen = 0;
        int distinct = 0;

        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            high ++;
            if (count[ch] == 0) {
                distinct ++;
            }
            count[ch] ++;

            while (distinct > k) {
                char lowCh = s.charAt(low);
                count[lowCh] --;
                low ++;
                if (count[lowCh] == 0)
                    distinct --;
            }
            maxLen = Math.max(maxLen, high - low);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubstrWithAtMostKUniqueChs ob = new LongestSubstrWithAtMostKUniqueChs();
        ob.lengthOfLongestSubstringKDistinct("abZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZYX", 2);
    }
}
