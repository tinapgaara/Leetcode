package facebook.math;

/**
 * Created by yingtan on 1/7/18.
 *
 * 168. Excel Sheet Column Title
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a positive integer, return its corresponding column title as appear in an Excel sheet.

 For example:

 1 -> A
 2 -> B
 3 -> C
 ...
 26 -> Z
 27 -> AA
 28 -> AB
 */
public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        int remain = n;
        String res = "";
        while(remain > 0) {
            // important !!! distance, firstly decrease remain by one
            remain --;
            int digit = remain % 26;
            char ch = (char)(digit + 'A');
            res = ch + res;
            remain = remain / 26;
        }
        return res;
    }
}
