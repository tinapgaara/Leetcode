package hashtable;

/**
 * Created by yingtan on 3/4/18.
 *
 * 288. Unique Word Abbreviation
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

 a) it                      --> it    (no abbreviation)

 1
 b) d|o|g                   --> d1g

 1    1  1
 1---5----0----5--8
 c) i|nternationalizatio|n  --> i18n

 1
 1---5----0
 d) l|ocalizatio|n          --> l10n
 Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

 Example:
 Given dictionary = [ "deer", "door", "cake", "card" ]

 isUnique("dear") -> false
 isUnique("cart") -> true
 isUnique("cane") -> false
 isUnique("make") -> true
 */
import java.util.*;
public class UniqueWordAbbreviation {

    Map<String, Set<String>> strs;
    public UniqueWordAbbreviation(String[] dictionary) {
        strs = new HashMap<>();
        for (String word : dictionary) {
            String abb = getAbbrev(word);
            if (strs.containsKey(abb)) {
                strs.get(abb).add(word);
            }
            else {
                Set<String> set = new HashSet<>();
                set.add(word);
                strs.put(abb, set);
            }
        }
    }
    public boolean isUnique(String word) {
        if (word == null) return false;
        String abbre = getAbbrev(word);
        if (strs.containsKey(abbre)) {
            if (! strs.get(abbre).contains(word)) { // Important !!!!
                return false;
            }
            else if (strs.get(abbre).size() > 1) {// Important !!!!
                return false;
            }
        }
        return true;
    }
    public String getAbbrev(String word) {
        String abbre = word;
        if (word.length() >= 3) {
            char first = word.charAt(0);
            char last = word.charAt(word.length() - 1);
            int len = word.length() - 2;
            abbre = first + "" + len + "" + last;
        }
        return abbre;
    }
}
