package google.twoPointers;

import java.util.Collections;
import java.util.List;

/**
 * Created by yingtan on 9/13/17.
 *
 * 524. Longest Word in Dictionary through Deleting
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

 Example 1:
 Input:
 s = "abpcplea", d = ["ale","apple","monkey","plea"]

 Output:
 "apple"
 Example 2:
 Input:
 s = "abpcplea", d = ["a","b","c"]

 Output:
 "a"
 */
public class LongestWordInDictThroughDelete {

    public String findLongestWord(String s, List<String> d) {
        if (s == null || s.length() == 0 || d == null || d.size() == 0) {
            return "";
        }

        // sort dict from longer word to shorter word
        Collections.sort(d, (a, b)->{
            if(a.length() != b.length())
                // length
                return b.length() - a.length();
            // lexicographocal order
            return a.compareTo(b);
        });

        for (String word : d) {
            if (canForm(s, word)) {
                return word;
            }
        }
        return "";
    }
    // two pointer
    public boolean canForm(String s, String word) {
        int i = 0;
        int j = 0;

        if (s.length() < word.length()) return false;
        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) == word.charAt(j)) {
                i ++;
                j ++;
            }
            else {
                i ++;
            }
        }
        if (j == word.length()) return true;
        else return false;
    }
}
