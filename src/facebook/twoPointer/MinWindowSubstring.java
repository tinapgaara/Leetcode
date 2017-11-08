package facebook.twoPointer;

/**
 * Created by yingtan on 5/13/17.
 */
public class MinWindowSubstring {

    public String minWindow(String s, String t) {
        if ( (s == null) || (s.length() == 0) || (t == null) || (t.length() == 0) )
            return "";

        // Important !!! here
        // sCount[ch] stores how many times ch appear in string s
        int[] sCounts = new int[256];
        int[] tCounts = new int[256];

        for (int i = 0 ; i < t.length(); i ++) {
            char ch = t.charAt(i);
            tCounts[ch] ++;
        }

        int end = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        String minStr = "";
        while (end < s.length()) {
            char ch = s.charAt(end);
            sCounts[ch] ++;
            while (isContains(sCounts, tCounts) && start <= end) {
                if (end - start + 1 < minLen) {

                    minStr = s.substring(start, end + 1);
                    minLen = end - start + 1;
                }
                //remove start ch
                char startCh = s.charAt(start);
                sCounts[startCh] --;
                start ++;
            }
            end ++;
        }
        return minStr;
    }

    // judge if s string contains t string
    public boolean isContains(int[] sCounts, int[] tCounts) {
        for (int i = 0 ; i < 256; i ++) {
            if (sCounts[i] < tCounts[i]) {
                return false;
            }
        }
        return true;
    }
}
