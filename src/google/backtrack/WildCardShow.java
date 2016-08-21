package google.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/8/15.
 */
public class WildCardShow {

    /*
    * 给一个wild card pattern，只含有 ‘0’ ‘1’ ‘?’，返回所有可能表示的string
    * */
    public List<String> possibleStrings(String str) { // only contains 0, 1, ?
        recurPrintPossibleStrs(str.toCharArray(), 0);
        return recurPossibleStrs("", 0, str);
    }

    // need to create new string to save list
    public List<String> recurPossibleStrs(String curstr, int index, String str) {
        if (index >= str.length()) {
            List<String> list = new ArrayList<String>();
            list.add(curstr);
            return list;
        }
        char ch = str.charAt(index);
        List<String> res = new ArrayList<String>();
        if (ch == '?') {
            String newstr = curstr + '1';
            List<String> newstrs = recurPossibleStrs(newstr, index+1, str);
            newstr = curstr + '0';
            List<String> newstrs_2 = recurPossibleStrs(newstr, index+1, str);
            res.addAll(newstrs);
            res.addAll(newstrs_2);
        }
        else if (ch == '0') {
            String newstr = curstr + '1';
            List<String> newstrs = recurPossibleStrs(newstr, index+1, str);
            res.addAll(newstrs);
        }
        else  {
            String newstr = curstr + '0';
            List<String> newstrs = recurPossibleStrs(newstr, index+1, str);
            res.addAll(newstrs);
        }
        return res;
    }


    // just need to print, so in-place recursion
    public void recurPrintPossibleStrs(char[] chs, int index) {
        if (index >= chs.length) {
            System.out.println(new String(chs));
            return;
        }
        char ch = chs[index];
        if (ch == '?') {
            chs[index] = '1';
            recurPrintPossibleStrs(chs, index + 1);
            chs[index] = '0';
            recurPrintPossibleStrs(chs, index + 1);
            chs[index] = '?';
        }
        else if (ch == '0') {
            chs[index] = '1';
            recurPrintPossibleStrs(chs, index + 1);
            chs[index] = '0';
        }
        else  {
            chs[index] = '0';
            recurPrintPossibleStrs(chs, index + 1);
            chs[index] = '1';
        }
    }

    public static void main(String[] args) {
        WildCardShow ob = new WildCardShow();
        System.out.println(ob.possibleStrings("1010??10"));
    }
}
