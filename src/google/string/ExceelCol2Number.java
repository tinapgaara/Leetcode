package google.string;

/**
 * Created by yingtan on 11/1/15.
 */
/*
Given a column title as appear in an Excel sheet, return its corresponding column number.
* Excel Sheet Column Number:
*
*For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
*
* */
public class ExceelCol2Number {

    public int titleToNumber(String s) {
        if ((s == null) || (s.length() == 0)) return 0;
        int prevNum = 0;
        for (int i = 0 ; i < s.length(); i ++) {
            int num = prevNum * 26 + (int)(s.charAt(i)) - 64;
            prevNum = num;
        }
        return prevNum;
    }

/*
*
*   1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
* */
    // Solution : scan from right position to left position in n.
    public String convertToTitle(int n) {
        StringBuffer buffer = new StringBuffer();

        while (n > 0) {
            int rest = n % 26;
            n = n / 26;
            if (rest == 0) {
                buffer.append('Z');
                n --;
            }
            else {
                buffer.append((char)(64 + rest));
            }
        }
        return buffer.reverse().toString();
    }
}


