package uber.hashmap;
import java.util.*;
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) return false;
        Map<Character, Integer> chToCount = new HashMap<>();
        for (char ch : s.toCharArray()) {
            if (chToCount.containsKey(ch)) {
                chToCount.put(ch, chToCount.get(ch) + 1);
            }
            else {
                chToCount.put(ch, 1);
            }
        }
        int odd = 0;
        for (Character key : chToCount.keySet()) {
            int count = chToCount.get(key);
            if (count % 2 != 0) {
                odd ++;
            }
            if (odd > 1) {
                return false;
            }
        }
        return true;
    }
}
