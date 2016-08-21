package DP;

/**
 * Created by max2 on 10/9/15.
 */
public class LargestCommonSequence {

    public int lcs(String a, String b) {
        int len_1 = a.length(); // row
        int len_2 = b.length(); // col

        int[][] lenLCS = new int[len_2+1][len_1+1];

        for (int i = 0 ; i < lenLCS.length; i ++) {
            lenLCS[i][0] = 0;
        }
        for (int i = 0 ; i < (len_1+1); i ++) {
            lenLCS[0][i] = 0;
        }

        for (int i =1; i < lenLCS.length; i ++) {
            for (int j = 1; j < (len_1 + 1); j ++) {
                char charAtI = b.charAt(i-1);
                char charAtJ = a.charAt(j - 1);

                if (charAtI == charAtJ) {
                    lenLCS[i][j] = lenLCS[i-1][j-1] + 1;
                }
                else {
                    lenLCS[i][j] = Math.max(lenLCS[i-1][j], lenLCS[i][j-1]);
                }
            }
        }
        return lenLCS[len_2][len_1];
    }

    public static void main(String[] args) {
        LargestCommonSequence ob = new LargestCommonSequence();
        System.out.println(ob.lcs("bdcaba", "bdca"));
    }
}
