package facebook.dp;

/**
 * Created by yingtan on 5/17/17.
 *
 * 161. One Edit Distance Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 30987
 Total Submissions: 100128
 Difficulty: Medium
 Contributor: LeetCode
 Given two strings S and T, determine if they are both one edit distance apart.
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null ) return false;
        int sLen = s.length();
        int tLen = t.length();

        int minLoop = Math.min(sLen, tLen);
        for (int i = 0 ; i < minLoop; i ++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (sLen == tLen) {
                    String laterSStr = s.substring(i+1, sLen);
                    String laterTStr = t.substring(i+1, tLen);
                    if (laterSStr.equals(laterTStr)) return true;
                    else return false;
                }
                else if (sLen > tLen){
                    String laterSStr = s.substring(i+1, sLen);
                    String laterTStr = t.substring(i, tLen);
                    if (laterSStr.equals(laterTStr)) return true;
                    else return false;
                }
                else {
                    String laterSStr = s.substring(i, sLen);
                    String laterTStr = t.substring(i+1, tLen);
                    if (laterSStr.equals(laterTStr)) return true;
                    else return false;
                }
            }
        }
        if ( (sLen - tLen == 1) || (tLen - sLen == 1) ) return true;
        else return false;
    }
}
