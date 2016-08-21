package string;

/**
 * Created by yingtan on 10/11/15.
 */
/**
 * Leetcode: Interleaving String
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

     For example,
     Given:
     s1 = "aabcc",
     s2 = "dbbca",

     When s3 = "aadbbcbcac", return true.
     When s3 = "aadbbbaccc", return false.
 */
/*
* Solution:
* boolean dp[i][j] : shows if the s1[0,..,i] and s2[0,...,j] can combine to the s3[0,...,(i+j)]
*
* if (s1.charAt(i) == s3.charAt(i + j)):
*  then check if s1[0,...,i-1] and s2[0,...,j] can form s3[0,...,(i+j)] :
*  == checks if dp[i-1][j] = true / false ?
*  dp[i][j] = dp[i-1][j]
*
* else if (s2.charAt(j) == s3.charAt(i+j)) :
*   then check is s2[0,...,j-1] and s1[0,,,i] can form s3[0,...,(i+j)];
*   == checks if dp[i][j-1] = true/false ?
*   dp[i][j] = dp[i][j-1]
*
* else : (!= s2  and != s1)
*   dp[i][j] = false;
*
* Special case:
*  s1.charAt(i) == s3.charAt(i+j)  &&  s2.charAt(j) == s3.charAt(i+j):
*   dp[i][j] = dp[i-1][j] | dp[i][j-1]
*
* specific case:
    *"aabc"
    "abad"
    "aabadabc"
*
*
* */

public class IsInterleaveStr {

    // DP:
    public boolean isInterleave_2(String s1, String s2, String s3) {
        if ( (s1 == null) || (s2 == null) || (s3 == null) ) return false;
        boolean[][] isInterLeavePart = new boolean[s2.length() + 1][s1.length() + 1];
        isInterLeavePart[0][0] = true;
        // Pay attention !!!! boundary case
        if ((s3.length() < s2.length()) || (s3.length() < s1.length()) ) return false;
        // Pay attention !!!! boundary case!!!!
        if (s1.length() == 0) {
            if (s2.equals(s3)) return true;
            else return false;
        }
        if (s2.length() == 0) {
            if (s1.equals(s3)) return true;
            else return false;
        }

        String s = "";
        String subs3 = "";
        for (int i = 1 ; i < isInterLeavePart.length; i ++) {
            s = s + s2.charAt(i-1);
            subs3 = subs3 + s3.charAt(i-1);
            if (s.equals(subs3)) isInterLeavePart[i][0] = true;
            else isInterLeavePart[i][0] = false;
        }
        s = "";
        subs3 = "";
        for (int i = 1 ; i < isInterLeavePart[0].length; i ++) {
            s = s + s1.charAt(i-1);
            subs3 = subs3 + s3.charAt(i-1);
            if (s.equals(subs3)) isInterLeavePart[0][i] = true;
            else isInterLeavePart[0][i] = false;
        }

        for (int i = 1; i < isInterLeavePart.length; i ++) {
            char ch2 = s2.charAt(i-1);
            for (int j = 1; j < isInterLeavePart[0].length; j ++) {
                // Pay attention !!!! need to judge the index of s3 is out of boundary !!!! If so, return false.
                int s3Index = i+j-1;
                if (s3Index >= s3.length()) return false;

                if ( (s1.charAt(j-1) == s3.charAt(i+j-1)) && (ch2 == s3.charAt(i+j-1)) ) { // special case: Pay attention
                    isInterLeavePart[i][j] = isInterLeavePart[i][j-1] | isInterLeavePart[i-1][j];
                }
                else if (s1.charAt(j-1) == s3.charAt(i+j-1)) {
                    isInterLeavePart[i][j] = isInterLeavePart[i][j-1];
                } else if (ch2 == s3.charAt(i+j-1)) {
                    isInterLeavePart[i][j] = isInterLeavePart[i-1][j];
                } else isInterLeavePart[i][j] = false;
            }
        }
        return isInterLeavePart[s2.length()][s1.length()];
    }


    public boolean isInterleave(String s1, String s2, String s3) {
        if ( (s1 == null) || (s2 == null) || (s3 == null) ) return false;
        if (s1.length() == 0) {
            if (s2.equals(s3)) return true;
            else return false;
        }
        if (s2.length() == 0) {
            if (s1.equals(s3)) return true;
            else return false;
        }
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        while ( (p1 < s1.length()) && (p2 < s2.length()) && (p3 < s3.length()) ) {
            char ch3 = s3.charAt(p3);
            char ch2 = s2.charAt(p2);
            char ch1 = s1.charAt(p1);

            if (ch3 == ch1) {
                p1 ++;
                p3 ++;
            }
            else if (ch3 == ch2) {
                p2 ++;
                p3 ++;
            }
            else return false;
        }
        if ((p1 < s1.length()) &&  (p3 < s3.length())) {
            while ( (p1 < s1.length()) &&  (p3 < s3.length())) {
                char ch1 = s1.charAt(p1);
                char ch3 = s3.charAt(p3);
                p1++;
                p3++;
                if (ch1 != ch3) return false;
            }
        }
        else if ((p2 < s2.length()) &&  (p3 < s3.length())) {
            while ( (p2 < s1.length()) &&  (p3 < s3.length())) {
                char ch2 = s2.charAt(p2);
                char ch3 = s3.charAt(p3);
                p2++;
                p3++;
                if (ch2 != ch3) return false;
            }
        }
        System.out.println(p1 + "," + p2 + "," + p3);
        if ((p3 < s3.length()) || (p2 < s2.length()) || (p1 < s1.length()) ) return false;

        return true;
    }

    public static void main(String[] args) {
        IsInterleaveStr ob = new IsInterleaveStr();
        System.out.println(ob.isInterleave_2("aabc", "abad", "aabadabc"));
    }
}
