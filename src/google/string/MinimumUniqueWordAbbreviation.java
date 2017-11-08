package google.string;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 8/7/17.
 *
 * 411. Minimum Unique Word Abbreviation
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 A string such as "word" contains the following abbreviations:

 ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.

 Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

 Note:
 In the case of multiple answers as shown in the second example below, you may return any one of them.
 Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
 Examples:
 "apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

 "apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
 */
public class MinimumUniqueWordAbbreviation {

    public String minAbbreviation(String target, String[] dictionary) {
        if (target == null || target.length() == 0) {
            return "";
        }
        else if (dictionary == null || dictionary.length == 0) {
            return ""+target.length();
        }

        PriorityQueue<Pair<String, Integer>> queue = new PriorityQueue<>(new Comparator<Pair<String, Integer>>() {
            @Override public int compare(Pair o1, Pair o2) {
                return (Integer)o1.getValue() - (Integer)o2.getValue();
            }
        });
        // generalized abbreviation
        generateAbbreviations(target, queue);

        while(! queue.isEmpty()) {
            Pair<String, Integer> pair = queue.poll();
            String abbre = pair.getKey();
            boolean allValid = true;
            for (String word : dictionary) {
                // valid abbreviation
                if (validWordAbbreviation(word, abbre)) {
                    // word conflicts with target in this abbre, shortest one here
                    allValid = false;
                    break;
                }
            }
            if (allValid) return abbre;
        }
        return "";
    }

    public void generateAbbreviations(String word, PriorityQueue<Pair<String, Integer>> queue) {

        int len = word.length();
        for (int i = 0 ; i < Math.pow(2, len); i ++) {
            int consecutiveOne = 0;
            String output = "";
            int tmpWord = i;
            int outputLen = 0;
            //0001 wor1
            for (int j = 0 ; j < len; j ++) {
                // jth digit
                if ((tmpWord & 1) == 1) {
                    consecutiveOne ++;

                    if (j == len - 1) {
                        output = output + consecutiveOne;
                        outputLen ++;
                    }
                }
                else {
                    if (consecutiveOne > 0) {
                        output =  output + consecutiveOne;
                        outputLen ++;
                        consecutiveOne = 0;
                    }
                    output = output + word.charAt(j);
                    outputLen ++;
                }
                tmpWord = (tmpWord) >> 1;
            }
            queue.offer(new Pair<String, Integer>(output.toString(), outputLen));
        }
    }

    public boolean validWordAbbreviation(String word, String abbr) {
        if (word == null || abbr == null || word.length() == 0 || abbr.length() == 0) return false;
        int p1 = 0;
        int p2 = 0;
        while(p1 < word.length() && p2 < abbr.length()) {
            char ch1 = word.charAt(p1);
            char ch2 = abbr.charAt(p2);

            // get the whole digits, which is the moving pos of p1
            if (ch2 >= '0' && ch2 <= '9') {
                // numbers can not starting from 0
                if (ch2 == '0') return false;
                int val = 0;
                while (p2 < abbr.length() && abbr.charAt(p2) >= '0' && abbr.charAt(p2) <= '9') {
                    val = val * 10 + (abbr.charAt(p2) - '0');
                    p2 ++;
                }
                p1 = p1 + val;
            }
            else {
                if (ch1 == ch2) {
                    p1 ++;
                    p2 ++;
                }
                else {
                    return false;
                }
            }
        }
        // important !
        if(p1 == word.length() && p2 == abbr.length()) return true;
        else return false;
    }
}
