package facebook.math;

/**
 * Created by yingtan on 12/21/17.
 *
 * I 1
 * V 5
 * X 10
 * L 50
 * C 100
 * D 500
 * M 1000
 *
 * *
 *  M-1000
 *  CM-900
 *  D-500
 *  CD-400
 *  C-100
 *  XC-90
 *  L-50
 *  XL-40
 *  X-10
 *  IX-9
 *  V-5
 *  IV-4
 *  I-1
 */
import java.util.*;
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
