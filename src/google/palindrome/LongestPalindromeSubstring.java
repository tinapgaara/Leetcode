package google.palindrome;

/**
 * Created by yingtan on 12/22/15.
 */
public class LongestPalindromeSubstring {

    public String longestPalindrome(String s) {

        if((s == null) || (s.length() == 0))
            return null;

        int len = s.length();
        boolean[][] flag = new boolean[len][len];
        int[] index = new int[2];
        int maxLen  = 0;

        for (int i = 1 ; i < len ; i ++) {
            for (int low = 0; low < len - i ; low ++) {
                int high = low + i;
                if (s.charAt(low) == s.charAt(high)) {
                    if ((i <= 2) || (flag[low+1][high-1])) {
                        flag[low][high] = true;

                        if (high - low + 1 > maxLen) {
                            index[0] = low;
                            index[1] = high;

                            maxLen = high - low + 1;
                        }

                    }
                    else
                        flag[low][high] = false;
                }


            }
        }
        return s.substring(index[0], index[1]+1);
    }
}
