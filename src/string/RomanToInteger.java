package string;

import java.util.*;

/**
 * Created by yingtan on 1/10/18.
 *
 * 13. Roman to Integer
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a roman numeral, convert it to an integer.

 Input is guaranteed to be within the range from 1 to 3999.


 */
public class RomanToInteger {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int res=0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = s.length();
        res = map.get(s.charAt(s.length()-1));
        for (int i = s.length() - 2; i >= 0 ; i --) {
            if (map.get(s.charAt(i)) >= map.get(s.charAt(i+1))) { // >=
                // VI 6
                res = res + map.get(s.charAt(i));
            }
            else {
                // IV 4
                res = res - map.get(s.charAt(i));
            }
        }
        return res;
    }
}
