package bloomberg;

/**
 * Created by yingtan on 10/25/15.
 */
public class LongestSubStrNoRepeatChs {

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

        indexs[s.charAt(0) - '0'] = 0;

        while (cur < s.length()) {
            char curCh = s.charAt(cur);
            if ((indexs[curCh - '0'] != -1) && (indexs[curCh - '0'] >= prev) ) { // Important !!!
                maxLen = Math.max(maxLen, curLen);
                curLen = cur - indexs[curCh - '0']; // Important !!  cur - indexs..
                prev = indexs[curCh - '0'] + 1;
            }
            else {
                curLen ++;
            }
            indexs[curCh - '0'] = cur;// Important !!! must be put outside of if and else
            cur ++; //
        }

        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }
}
