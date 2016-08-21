package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 9/22/15.
 */
public class StroboGrammaticNum {

    public boolean isStrobogrammatic(String num) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0 ; i < 10; i ++) {
            if ((i == 0) || (i == 8) || (i == 1)) {
                map.put(i, i);
            } else if (i == 6) {
                map.put(i, 9);
            } else if (i == 9) {
                map.put(i, 6);
            } else {
                map.put(i, -1);
            }
        }
        int len = num.length();
        int low = 0;
        int high = len - 1;
        while (low < high) {
            int chlow = num.charAt(low) - 48; // char to int: '1' - 48
            int chhigh = num.charAt(high) - 48;
            if (map.get(chlow) != chhigh) {
                return false;
            }
            low ++;
            high --;
        }
        if (low == high) {
            int val = map.get(num.charAt(low) - 48);
            if ((val == 8) || (val == 0) || (val == 1))
                return true;
            else
                return false;
        }
        else {
            return true;
        }
    }

    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<String>();
        int[][] twoDigits = new int[][]{{0, 0},{1, 1},{8, 8},{6, 9},{9, 6}};
        int[] oneDigits = new int[]{0,1,8};

        int low = 0;
        int high = n -1;
        if (low == high) {
            for (int i = 0 ; i < oneDigits.length; i ++)
                res.add(oneDigits[i]+"");
            return res;
        }
        else {
            List<String> middleStrings =
                    recurFindStrobogrammatic(n, low+1, high-1, twoDigits, oneDigits);
            for (int i = 1; i < 5; i ++){
                int size = middleStrings.size();
                if (size == 0) {
                    String str = twoDigits[i][0] + "" + twoDigits[i][1];
                    res.add(str);
                }
                else {
                    for (int j = 0 ; j < size; j ++) {
                        String s = middleStrings.get(j);
                        s = twoDigits[i][0] + s + twoDigits[i][1];
                        res.add(s);
                    }
                }
            }
        }
        return res;
    }

    public List<String> recurFindStrobogrammatic(int n, int low, int high,
                                                 int[][] twoDigits, int[] oneDigits) {
        List<String> res = new ArrayList<String>();
        if (low > high)
            return res;
        else if (low == high) {
            for (int i = 0 ; i < oneDigits.length; i ++)
                res.add(oneDigits[i]+"");
        }
        else {
            List<String> middleStrings =
                    recurFindStrobogrammatic(n, low+1, high-1, twoDigits, oneDigits);
            for (int i = 0; i < 5; i ++){
                int size = middleStrings.size();
                if (size == 0) {
                    String str = twoDigits[i][0] + "" + twoDigits[i][1];
                    res.add(str);
                }
                else {
                    for (int j = 0 ; j < size; j ++) {
                        String s = middleStrings.get(j);
                        s = twoDigits[i][0] + s + twoDigits[i][1];
                        res.add(s);
                    }
                }
            }
        }
        return res;
    }
}
