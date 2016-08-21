package substring;

/**
 * Created by yingtan on 10/24/15.
 */
public class MinWindow {

    public String minWindow(String s, String t) {
        if ( (s == null) || (s.length() == 0) || (t == null) || (t.length() == 0) )
            return "";

        int minLen = Integer.MAX_VALUE;
        if (s.length() == 1) {
            if (t.length() < 1) {
                return s;
            } else if (t.length() == 1) {
                if (s.equals(t)) return s;
            }
            return "";
        }
        int start = 0;
        int end = 0;// Important !!!
        int[] tCounts = new int[256];
        for (int i = 0 ; i < t.length(); i ++) {
            tCounts[t.charAt(i) - '0'] ++;
        }
        int[] sCounts = new int[256];
        int tLen = t.length();
        int coverCh = 0;

        int low = -1;
        int high = -1;

        while (end < s.length() && (start < s.length())) {
            sCounts[s.charAt(end) - '0'] ++;
            if (sCounts[s.charAt(end) - '0'] <= tCounts[s.charAt(end) - '0']) // Important !!!! must be <=. for "aa", "aa"
                coverCh ++;
            if (coverCh == tLen) {
                while ( (start < s.length()) && ((!t.contains(s.charAt(start) + "")) ||
                        (sCounts[s.charAt(start) - '0'] > tCounts[s.charAt(start) - '0']))) { // because, boundary case: "aa" "aa"
                    sCounts[s.charAt(start) - '0'] --; // Important !!!! first abstract the sCounts, then start ++
                    start ++;

                }
                int curLen = end - start + 1;
                if (curLen < minLen) {
                    minLen = curLen;
                    low = start;
                    high = end;
                }
                sCounts[s.charAt(start) - '0'] --;
                coverCh --;
                start ++;
            }
            end ++;
        }
        if ((low < 0) && (high < 0)) return "";
        else
            return s.substring(low, high+1);
    }

    public static void main(String[] args) {
        MinWindow ob = new MinWindow();
        ob.minWindow("acbbaca", "aba");
    }
}
