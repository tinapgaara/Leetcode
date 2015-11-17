package bloomberg.palidrome;

/**
 * Created by yingtan on 11/15/15.
 */
/*Given a string S, you are allowed to convert it to a palindrome by adding characters
in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".
*
* */
public class ShortestPalidrome {

    public String shortestPalindrome(String s) {

        if ((s == null) || (s.length() == 0))
            return "";

        // how to find maxLen of palidrome starts from 0 position : important !!!!
        int cutIndex = findLongestPalindromeFromFirstIndex(s);

        return new StringBuffer(s.substring(cutIndex+1)).reverse().toString() + s; // important !!! need to use StringBuffer.reverse() here: quicker
    }

    public int findLongestPalindromeFromFirstIndex(String s) {
        int len = s.length();
        int right = 0;
        int left = 0;
        int maxLen = 0;

        for(int i = 0 ; i < len;){
            left = right = i;
            while(right < len - 1 && s.charAt(right) == s.charAt(right + 1))
                right ++; // same chars, jump

            i = right + 1; // next finding palidrome will not starts from here

            while(left > 0 && right < len - 1 && s.charAt(left - 1) == s.charAt(right + 1)){ // expand to left and right from middle
                left --;
                right ++;
            }

            if(left == 0 && maxLen < right + 1){ // keep record when reaching 0 position
                maxLen = right;
            }
        }
        return maxLen;
    }

    public boolean isPalindrome(String s, int low, int high) {
        while (low <= high) {
            if (s.charAt(low) == s.charAt(high)) {
                low ++;
                high --;
                System.out.println(low + "," + high);
            }
            else
                return false;
        }
        return true;
    }

    public int longestPalindrome(String s) {

        if((s == null) || (s.length() == 0))
            return 0;

        int len = s.length();
        boolean[][] flag = new boolean[len][len];
        int maxLen  = 0;
        int maxIndex = 0;
        System.out.println(len);
        for (int i = 1 ; i < len ; i ++) {
            for (int low = 0; low < len - i ; low ++) {
                int high = low + i;
                //System.out.println(low+ "," + high);
                if (s.charAt(low) == s.charAt(high)) {
                    if ((i <= 2) || (flag[low+1][high-1])) {
                        flag[low][high] = true;

                        if ((low == 0) && (high - low + 1 > maxLen)) {
                            maxIndex = high;

                            maxLen = high - low + 1;
                        }

                    }
                    else
                        flag[low][high] = false;
                }

            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        ShortestPalidrome ob = new ShortestPalidrome();
        System.out.println(ob.shortestPalindrome("aacecaaa"));
    }
}
