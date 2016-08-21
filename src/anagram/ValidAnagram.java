package anagram;

import java.util.Arrays;

/**
 * Created by yingtan on 8/25/15.
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if ( (s == null) || (t == null) ) {
            return false;
        }
        char[] ch_s = s.toCharArray();
        char[] ch_t = t.toCharArray();

        Arrays.sort(ch_s);
        Arrays.sort(ch_t);

        int len_s = ch_s.length;
        int len_t = ch_t.length;

        if (len_s != len_t) {
            return false;
        }
        else {
            for (int i = 0 ; i < len_s; i ++) {
                if (ch_s[i] != ch_t[i]) {
                    return false;
                }
            }
        }

        return true;

    }

    public static void main(String[] args) {

        String s = "aabb";
        String t = "bbaa";

        ValidAnagram ob = new ValidAnagram();
        ob.isAnagram(s,t);

    }
}
