package google.hashset;

import java.util.HashSet;

/**
 * Created by yingtan on 8/20/17.
 *
 * 409. Longest Palindrome
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

 This is case sensitive, for example "Aa" is not considered a palindrome here.

 Note:
 Assume the length of given string will not exceed 1,010.

 Example:

 Input:
 "abccccdd"

 Output:
 7

 Explanation:
 One longest palindrome that can be built is "dccaccd", whose length is 7.

 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        if(s == null || s.length() == 0 ) return 0;
        // count how many pairs
        // ddd -> when parse two dd , count ++; , finally left one d
        int count = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            // ch appears twice
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
                count ++;
            }  else {
                set.add(s.charAt(i));
            }
        }

        if (!set.isEmpty()) return count * 2  + 1;
        else return count * 2;
    }
}
