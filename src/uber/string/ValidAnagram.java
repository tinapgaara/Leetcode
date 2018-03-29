package uber.string;
import java.util.*;
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s==null && t==null) return true;
        else if (s==null || t==null) return false;
        else if (s.length() != t.length()) return false;

        Map<Character, Integer> chToCount = new HashMap<>();
        // two scan !!! Important !!!!
        for(char c : s.toCharArray())  {
            if(chToCount.containsKey(c)) {
                chToCount.put(c, chToCount.get(c) + 1);
            }
            else {
                chToCount.put(c, 1);
            }
        }
        for(char c : t.toCharArray()) {
            if (! chToCount.containsKey(c)) {
                return false;
            }
            int count = chToCount.get(c);
            if (count == 0) {
                return false;
            }
            chToCount.put(c, count - 1);
        }

        return true;
    }
    // follow up: What if the inputs contain unicode characters? How would you adapt your solution to such case?
    // unicode chs can occupy 1 or 2 chs
    public static void main(String[] args) {
        ValidAnagram ob = new ValidAnagram();
        ob.isAnagram("aaa", "aa");
    }
}
