package google.string;

import java.util.Iterator;

/**
 * Created by yingtan on 11/7/15.
 */
/*
* pattern 可以跨 strings, 但是必須要是連續的, 注意是 iterable, 所
以是不能回頭的
我是 stringBuffer 去保存已經⾛走過的部分, 如果不 match rewind
的時候就從 stringBuffer 裡⾯面的 character 開始 check
不夠再從 iterater 拿
boolean contains(String pattern, Iterable<String> strs)
pattern: "abc"
strs : "ab", "cd" -> true
strs : "aa", "bcd" -> true
strs : "ab", "ac" -> false
*
* */
public class StringMatch {

    public boolean containsSubstr(String pattern, Iterable<String> strs) {
        int[] next = constructNextArray(pattern);

        Iterator<String> iter = strs.iterator();

        int p2 = 0; // pattern index
        int curLen = 0; // across strings
        while (iter.hasNext()) {
            String str = iter.next();
            int p1 = 0; // str index
            while ((p1 < str.length()) && (p2 < pattern.length())) {
                if (str.charAt(p1) == pattern.charAt(p2)) {
                    p1 ++;
                    p2 ++;
                    curLen ++;
                }
                else {
                    if (p2 >= 1) {
                        int moveSteps = curLen - next[p2-1];
                        p1 = p1 - curLen + moveSteps;
                        p2 = 0;
                        curLen = 0;
                    }
                    else {
                        p1 ++;
                    }
                }
            }
            if (curLen == pattern.length()) return true;
        }
        return false;

    }

    public boolean matchSubString(String str, String substr) {
        int[] next = constructNextArray(substr);

        int p1 = 0; // index in str
        int p2 = 0;// index in substr
        int curLen = 0;

        while ((p1 < str.length()) && (p2 < substr.length())) {
            if (str.charAt(p1) == substr.charAt(p2)) {
                p1 ++;
                p2 ++;
                curLen ++;
            }
            else {
                if (p2 >= 1) {
                    int moveSteps = curLen - next[p2 - 1];
                    p1 = p1 - curLen + moveSteps;
                    p2 = 0;
                    curLen = 0;
                }
                else {
                    p1 ++;
                }
            }
        }
        if (curLen == substr.length()) {
            return true;
        }
        return false;
    }

    // KMP do string match: match substring
    public int[] constructNextArray(String str) {
        int[] next = new int[str.length()];
        int i = 0;
        int j = 1;
        while (j < next.length) {
            if (str.charAt(i) == str.charAt(j)) {
                next[j] = i + 1;
                i ++;
                j ++;
            }
            else if (i == 0) {
                next[j] = i;
                j ++;
            }
            else {
                i = next[i-1]; // ???
            }
        }
        return next;
    }

    public static void main(String[] args) {
        StringMatch ob = new StringMatch();
        String str = "abc";
        int[] next = ob.constructNextArray(str);
        System.out.println(ob.matchSubString("aabcd", str));
    }

}
