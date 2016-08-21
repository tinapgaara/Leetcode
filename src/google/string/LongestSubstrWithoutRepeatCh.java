package google.string;

/**
 * Created by yingtan on 12/22/15.
 */
public class LongestSubstrWithoutRepeatCh {

    public int lengthOfLongestSubstring(String s) {
        if ((s == null) || (s.length() == 0))
            return 0;

        int[] indexs = new int[256];// save the position of character which appeared before
        for (int i = 0 ; i < 256; i ++) {
            indexs[i] = -1;
        }

        int maxLen = 0;
        int curLen = 1;

        int prev = 0;
        if (s.length() == 1) return 1;
        int cur = 1;

        indexs[s.charAt(0)] = 0;

        while (cur < s.length()) {
            char curCh = s.charAt(cur);

            if ((indexs[curCh] != -1) && (indexs[curCh] >= prev) ) { // Important !!!
                maxLen = Math.max(maxLen, curLen);
                curLen = cur - indexs[curCh]; // Important !!  cur - indexs..
                prev = indexs[curCh] + 1;
            }
            else {
                curLen ++;
            }
            indexs[curCh] = cur;
            cur ++;
        }

        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }

}
