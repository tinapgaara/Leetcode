package bloomberg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by max2 on 10/16/15.
 */
/*
* Given a roman numeral, convert it to an integer.
Input is guaranteed to be within the range from 1 to 3999.
*
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
*
* */
public class Roman2Int {

    public int romanToInt(String s) {

        int res=0;
        int len = s.length();
        String number = s;
        if (number == null) return 0;
        if (number.startsWith("M")) return 1000 + romanToInt(number.substring(1, len));
        if (number.startsWith("CM")) return 900 + romanToInt(number.substring(2,len));
        if (number.startsWith("D")) return 500 + romanToInt(number.substring(1,len));
        if (number.startsWith("CD")) return 400 + romanToInt(number.substring(2,len));
        if (number.startsWith("C")) return 100 + romanToInt(number.substring(1,len));
        if (number.startsWith("XC")) return 90 + romanToInt(number.substring(2,len));
        if (number.startsWith("L")) return 50 + romanToInt(number.substring(1,len));
        if (number.startsWith("XL")) return 40 + romanToInt(number.substring(2,len));
        if (number.startsWith("X")) return 10 + romanToInt(number.substring(1,len));
        if (number.startsWith("IX")) return 9 + romanToInt(number.substring(2,len));
        if (number.startsWith("V")) return 5 + romanToInt(number.substring(1,len));
        if (number.startsWith("IV")) return 4 + romanToInt(number.substring(2,len));
        if (number.startsWith("I")) return 1 + romanToInt(number.substring(1,len));
        return res;
    }

    public static void main(String[] args) {
        Roman2Int ob= new Roman2Int();
        ob.romanToInt("MMMDCXVI");
    }
}
