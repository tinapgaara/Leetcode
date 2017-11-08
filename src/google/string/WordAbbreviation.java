package google.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yingtan on 11/1/15.
 *
 * 527. Word Abbreviation
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

 Begin with the first character and then the number of characters abbreviated, which followed by the last character.
 If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
 If the abbreviation doesn't make the word shorter, then keep it as original.
 Example:
 Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 */
public class WordAbbreviation {

    public List<String> wordsAbbreviation(List<String> dict) {
        int len = dict.size();
        String[] res = new String[len];
        int[] prefix = new int[len];
        for (int i = 0 ; i < len ; i++) {
            prefix[i] = 1;
            res[i] = makeAbbr(dict.get(i), 1); // make abbreviation for each string with prefix 1
        }
        // check if each word's abbre is unique
        for (int i = 0 ; i < len; i ++) {
            while (true) {
                HashSet<Integer> duplicateAbbresIndex = new HashSet<Integer>();
                // compare word with other words
                for (int j = i + 1; j < len; j ++) {
                    if (res[j].equals(res[i])){
                        // jth is duplicate with ith, need to re-abbre jth
                        duplicateAbbresIndex.add(j);
                    }
                }
                // no duplicate abbres, ith abbre is good
                if (duplicateAbbresIndex.isEmpty()) {
                    break;
                }
                // else, mins there is duplicate abbres, so ith element need re-abbre
                duplicateAbbresIndex.add(i);
                // re-abbre , increase the prefix length
                for (int index: duplicateAbbresIndex) {
                    res[index] = makeAbbr(dict.get(index), prefix[index] + 1);
                    // Important !!!
                    prefix[index] ++;
                }
            }
        }
        return Arrays.asList(res);
    }
    private String makeAbbr(String s, int k) {
        if (k>=s.length()-2) return s;
        StringBuilder builder=new StringBuilder();
        builder.append(s.substring(0, k));
        builder.append(s.length()-1-k);
        builder.append(s.charAt(s.length()-1));
        return builder.toString();
    }

    public String abbreviate(String word) {
        if (word == null) return null;
        if (word.length() < 2) {
            return word;
        }
        return word.charAt(0) + (word.length() -2) + word.charAt(word.length()-1) + "";
    }

    public boolean checkDuplicate(String[] dict, String word) {
        int len = word.length();
        for (int i = 0 ; i < dict.length ; i ++) {
            if (!word.equals(dict[i])) {
                if (word.length() == dict[i].length()) {
                    if (word.length() >= 2) {
                        if (word.charAt(0) == dict[i].charAt(0)) {
                            if (word.charAt(len - 1) == dict[i].charAt(len - 1)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        // or can use hashmap to store : string word -> string abbreviate
        return false;
    }

    // follow up :这个method如果被访问千万遍，要怎么降复杂度？
    // save space then hashtable
    // point: 字符串存在hashtable, 一提降复杂度-> use trie instead
    // TODO:
    public void buildTrie(String[] dict) {

           /*
           *       null
           *      /    \
           *     A     B
           *    / \   /
           *   2  3  1
           *  /   \
           * C    C
           * (real string)
           *
           * at end of the path, (at the last node) -> store real string
           *
           * */
    }

    /*
    * 可以为 a1breviation a2reviation 1bbreviation
    abbrevia4 ab3via4 abbreviation 11n 12等，问（1）⼀一共有多少
    种缩写（2）输出所有这些缩写
    * */
    public void printAllAbbrievations(String word) {

    }



    /*
    *408. Valid Word Abbreviation   Add to List QuestionEditorial Solution  My Submissions
    Total Accepted: 6428
    Total Submissions: 23569
    Difficulty: Easy
    Contributors: Admin
    Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

    A string such as "word" contains only the following valid abbreviations:

    ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
    Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

    Note:
    Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

    Example 1:
    Given s = "internationalization", abbr = "i12iz4n":

    Return true.
    Example 2:
    Given s = "apple", abbr = "a2e":

    Return false.
    Show Company Tags
    Show Tags
    Show Similar Problems

    *
    * */
    public boolean validWordAbbreviation(String word, String abbr) {
        if ( (word == null) || (abbr == null) ) return false;
        else if ( (word.length() == 0) && (abbr.length() == 0) ) return true;

        int wordIndex =0;
        int abbrIndex = 0;
        while ( (wordIndex < word.length()) && (abbrIndex < abbr.length()) ) {
            if (word.charAt(wordIndex) == abbr.charAt(abbrIndex)) {
                wordIndex ++;
                abbrIndex ++;
                continue;
            }
            // invalid char : '09'
            if ( (abbr.charAt(abbrIndex) <= '0' ) || (abbr.charAt(abbrIndex) > '9') ) return false;

            // is number
            // the way to deal with  num(>= 2 digits)+char   ;  do not need to write code for string ends with num separately
            int start = abbrIndex;
            while ( (abbrIndex < abbr.length()) && (abbr.charAt(abbrIndex) >= '0') && (abbr.charAt(abbrIndex) <= '9') ) {
                abbrIndex ++;
            }
            int num = Integer.parseInt(abbr.substring(start, abbrIndex));
            wordIndex = wordIndex + num;
        }
        if ( (wordIndex == word.length()) && (abbrIndex == abbr.length()) ) return true;
        else return false;
    }
}
