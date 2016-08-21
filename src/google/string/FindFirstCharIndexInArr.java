package google.string;

import java.util.HashMap;

/**
 * Created by max2 on 11/3/15.
 */
/*
* find_first_of(string a, string b) 要求返回index of string a where Hash table
b's char first appear in string a


例如: a:"abc" b:"cb" =>
return 1 (char 'b' first appear in a of index 1).
*
* */
public class FindFirstCharIndexInArr {

    public int findFirstChar(String a, String b) {
        // O (a.length)
        HashMap<Character, Integer> indexs = new HashMap<Character, Integer>();
        for (int i = 0 ; i < a.length(); i ++) {
            if (!indexs.containsKey(a.charAt(i))) {
                indexs.put(a.charAt(i), i);
            }
        }
        int minIndex = Integer.MAX_VALUE;

        // scan once
        // O (b.length)
        for (int i = 0 ; i < b.length(); i ++) {
            if (indexs.containsKey(b.charAt(i))) {
                int index = indexs.get(b.charAt(i));
                if (index <minIndex) {
                    minIndex = index;
                }
            }
        }
        return minIndex;

        // or according to the index , rerank the string b. Then return first char.
        // O(a.length) + O(b.length * (log(b.length)))
    }
}
