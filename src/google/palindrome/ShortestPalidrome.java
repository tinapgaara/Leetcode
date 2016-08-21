package google.palindrome;

/**
 * Created by yingtan on 12/22/15.
 */
public class ShortestPalidrome {

    public String shortestPalindrome(String s) {

        if ((s == null) || (s.length() == 0))
            return "";
        int low = 0;
        int high = s.length()-1;

        int cutIndex = findLongestPalindromeFromFirstIndex(s);

        return new StringBuffer(s.substring(cutIndex+1)).reverse().toString() + s;
    }

    public int findLongestPalindromeFromFirstIndex(String s) {
        int len = s.length();
        int right = 0;
        int left = 0;
        int maxLen = 0;

        for(int i = 0 ; i < len;){
            left = right = i;
            while(right < len - 1 && s.charAt(right) == s.charAt(right + 1))
                right ++;

            i = right + 1;

            while(left > 0 && right < len - 1 && s.charAt(left - 1) == s.charAt(right + 1)){
                left --;
                right ++;
            }

            if(left == 0 && maxLen < right + 1){
                maxLen = right;
            }
        }
        return maxLen;
    }
}
