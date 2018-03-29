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
        if (s == null || t == null) return false;
        int min = Math.min(s.length(), t.length());
        for (int i = 0 ; i < min; i ++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {
                    // this is replace
                    return s.substring(i + 1).equals(t.substring( i + 1));
                }
                else if (s.length() > t.length()) {
                    // delete char from s
                    return s.substring(i + 1).equals(t.substring(i));
                }
                else {
                    // delete a char from t
                    return t.substring(i + 1).equals(s.substring(i));
                }
            }
        }
        int absDist = Math.abs(s.length() - t.length());
        if (absDist != 1) {
            return false;
        }
        else {
            return true;
        }
    }

}
