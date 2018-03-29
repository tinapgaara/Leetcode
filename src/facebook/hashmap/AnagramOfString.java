package facebook.hashmap;

import java.util.Arrays;

/**
 * Created by yingtan on 3/19/18.
 *
 * check if a string contains the anagram of another string.

 */
public class AnagramOfString {
    public boolean containsAnagram(String str, String keyword) {
        char[] keys = keyword.toCharArray();
        Arrays.sort(keys);
        for (int i = 0; i < str.length(); i ++) {
            int end = i + keys.length;
            if (end < str.length()) {
                String compare = str.substring(i, end);
                char[] chs = compare.toCharArray();
                Arrays.sort(chs);
                if (new String(chs).equals(new String(keys))) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        AnagramOfString ob = new AnagramOfString();
        System.out.println(ob.containsAnagram("l%eet%code ", "d%o c"));
    }
}
