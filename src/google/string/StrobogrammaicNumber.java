package google.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 11/6/15.
 */
/*
*A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*
* */
public class StrobogrammaicNumber {

    int count = 0;
    public int strobogrammaticInRange(String low, String high) {
        List<String> res = new ArrayList<String>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(8, 8);
        map.put(6, 9);
        map.put(9, 6);
        int[] oneDigits = new int[]{0, 1, 8};

        // generate 2 digits: 69 88 96 : should be in range[50, 100]
        // generate 3 digits: 818 ... should be in range[50,100]
        String s= "";
        for (int i = low.length(); i <= high.length(); i ++) {
            recurGenerateStrobo(s, low, high, 0, i-1, map, oneDigits);
        }
        return count;
    }

    public void recurGenerateStrobo(String s, String lowstr, String highstr,
                                    int low, int high, HashMap<Integer, Integer> map, int[] oneDigits) {
        if (low > high) {
            if ( ((s.length() == 1) || (s.charAt(0) != '0')) // first digit can not be one for >= 2 digits number
                && (compareTo(lowstr, s) && (compareTo(s, highstr)))) {
                count ++;
            }
            // is in range
        }
        else if (low == high) {
            int len = s.length();
            for (int i = 0 ; i < oneDigits.length ; i ++) {
                String news = s.substring(0, len / 2) + oneDigits[i] + s.substring(len/2, len);
                recurGenerateStrobo(news, lowstr, highstr, low + 1, high -1, map, oneDigits);
            }
        }
        else {
            int len = s.length();
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                String news = s.substring(0, len / 2) + entry.getKey() + entry.getValue() + s.substring(len / 2);
                recurGenerateStrobo(news, lowstr, highstr, low + 1, high -1, map, oneDigits);
            }
        }
    }

    public boolean compareTo(String s1, String s2) {
        if (s2.length() > s1.length()) return true;
        else if (s2.length() < s1.length()) return false;
        else {
            if (s1.compareTo(s2) <= 0) {
                return true;
            }
            else
                return false;
        }
    }


    public static void main(String[] args) {
        StrobogrammaicNumber ob = new StrobogrammaicNumber();
        System.out.println(ob.strobogrammaticInRange("50", "100"));
    }
}
