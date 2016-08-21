package google.dp;

/**
 * Created by yingtan on 12/22/15.
 */
public class DistinctSubsequence {

    public int numDistinct(String s, String t) {
        if ((s == null) && (t == null)) return 0;
        if ( (s == null) && (t != null)) return t.length();
        if ( (t == null) && (s != null)) return s.length();

        int[][] nums = new int[s.length()+1][t.length()+1];
        for (int i = 0 ; i <= s.length(); i ++){
            nums[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); i ++) {
            for (int j = 1; j <= t.length(); j ++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    nums[i][j] = nums[i-1][j] + nums[i-1][j-1];
                }
                else {
                    nums[i][j] = nums[i-1][j];
                }
            }
        }
        return nums[s.length()][t.length()];
    }
}
