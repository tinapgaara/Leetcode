package google.permutation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 8/15/17.
 * 266. Palindrome Permutation
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string, determine if a permutation of the string could form a palindrome.

 For example,
 "code" -> False, "aab" -> True, "carerac" -> True.


 */
public class PalindromePermutation {

    public boolean canPermutePalindrome(String s) {
        // all even only one 1
        Map<Character, Integer> counts = new HashMap<Character, Integer>();
        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if (counts.containsKey(ch)) {
                int count = counts.get(ch);
                counts.put(ch, count + 1);
            }
            else {
                counts.put(ch, 1);
            }
        }
        int odd = 0;

        for (Map.Entry<Character, Integer> set : counts.entrySet()) {
            if (set.getValue() % 2 == 1) {
                odd ++;
            }
            if (odd > 1) return false;
        }
        return true;
    }
}
