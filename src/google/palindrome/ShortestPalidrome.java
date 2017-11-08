package google.palindrome;

/**
 * Created by yingtan on 12/22/15.
 *
 * 214. Shortest Palindrome
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

 For example:

 Given "aacecaaa", return "aaacecaaa".

 Given "abcd", return "dcbabcd".

 下面这种Java写法也是在找相同的前缀后缀，但是并没有每次把前缀后缀取出来比较，而是用两个指针分别指向对应的位置比较，然后用end指向相同后缀的起始位置，最后再根据end的值来拼接两个字符串

 Credits:
 */
public class ShortestPalidrome {

    public String shortestPalindrome_simple(String s) {
        int i = 0, end = s.length() - 1, j = end;
        char[] arr = s.toCharArray();
        while (i < j) {
            if (arr[i] == arr[j]) {
                ++i; --j;
            } else {
                i = 0; --end; j = end;
            }
        }
        return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
    }


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
