package facebook.hashmap;

import java.util.Arrays;

/**
 * Created by yingtan on 3/19/18.
 *
 * 1. Implement the . anagramIndexOf() function找到anagram的index
 举栗子
 "leetcode".anagramIndexOf("doc") = 4
 "leetcode".anagramIndexOf("index") = -1 //不存在
 要考虑有空格 还有 %，和普通char一样对待

 */
public class AnagramIndexOf {

    public int firstIndex(String str, String keyword) {
        char[] keys = keyword.toCharArray();
        Arrays.sort(keys);
        for (int i = 0; i < str.length(); i ++) {
            int end = i + keys.length;
            if (end < str.length()) {
                String compare = str.substring(i, end);
                char[] chs = compare.toCharArray();
                Arrays.sort(chs);
                if (new String(chs).equals(new String(keys))) {
                    return i;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        AnagramIndexOf ob = new AnagramIndexOf();
        System.out.println(ob.firstIndex("l%eet%c ode ", "d%o c"));
    }
}
