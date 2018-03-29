package amazon.string;

/**
 * Created by yingtan on 3/22/18.
 *
 * 438. Find All Anagrams in a String
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

 Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

 The order of output does not matter.

 Example 1:

 Input:
 s: "cbaebabacd" p: "abc"

 Output:
 [0, 6]

 Explanation:
 The substring with start index = 0 is "cba", which is an anagram of "abc".
 The substring with start index = 6 is "bac", which is an anagram of "abc".
 Example 2:

 Input:
 s: "abab" p: "ab"

 Output:
 [0, 1, 2]

 Explanation:
 The substring with start index = 0 is "ab", which is an anagram of "ab".
 The substring with start index = 1 is "ba", which is an anagram of "ab".
 The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
import java.util.*;
public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null || s.length() == 0 || p.length() == 0) return res;
        int[] ps = new int[26];
        for (char ch : p.toCharArray()) {
            ps[ch - 'a'] ++;
        }
        int len = p.length();
        if (len > s.length()) return res;
        int[] ss = new int[26];
        // start indice 0
        for (int i = 0 ; i < len; i ++) {
            ss[s.charAt(i) - 'a'] ++;
        }
        boolean isfirst = true;
        for (int i = 0; i < 26; i ++) {
            if (ss[i] != ps[i]) {
                isfirst = false;
                break;
            }
        }
        if (isfirst) {
            res.add(0);
        }
        for (int i = len; i < s.length(); i ++) {
            char removedch = s.charAt(i - len);
            char addedch = s.charAt(i);
            ss[removedch - 'a'] --;
            ss[addedch - 'a'] ++;
            boolean add = true;
            for (int j = 0 ; j < 26; j ++) {
                if (ss[j] != ps[j]) {
                    add = false;
                }
            }
            if (add) {
                res.add(i - len + 1);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        FindAllAnagramsInAString ob = new FindAllAnagramsInAString();
        System.out.println(ob.findAnagrams("cbaebabacd", "abc"));
    }
}
