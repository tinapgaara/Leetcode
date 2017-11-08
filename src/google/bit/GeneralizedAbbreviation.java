package google.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 8/6/17.
 *
 * 320. Generalized Abbreviation
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Write a function to generate the generalized abbreviations of a word.

 Example:
 Given word = "word", return the following list (order does not matter):
 ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 Seen this question in a real interview before?   Yes  No
 Difficulty:Medium
 Total Accepted:22.4K
 Total Submissions:50K
 Contributor: LeetCode

 0000 word
 0001 wor1
 0010 wo1d
 0011 wo2
 0100 w1rd
 0101 w1r1
 0110 w2d
 0111 w3
 1000 1ord
 1001 1or1
 1010 1o1d
 1011 1o2
 1100 2rd
 1101 2r1
 1110 3d
 1111 4

 */
public class GeneralizedAbbreviation {

    public List<String> generateAbbreviations(String word) {

        List<String> res = new ArrayList<String>();
        //if (word == null || word.length() == 0) return res;
        int len = word.length();
        for (int i = 0 ; i < Math.pow(2, len); i ++) {
            int consecutiveOne = 0;
            String output = "";
            int tmpWord = i;
            //0001 wor1
            for (int j = 0 ; j < len; j ++) {
                // jth digit
                if ((tmpWord & 1) == 1) {
                    consecutiveOne ++;

                    if (j == len - 1) {
                        output = output + consecutiveOne;
                    }
                }
                else {
                    if (consecutiveOne > 0) {
                        output =  output + consecutiveOne;
                        consecutiveOne = 0;
                    }
                    output = output + word.charAt(j);
                }
                tmpWord = (tmpWord) >> 1;
            }
            res.add(output);

        }
        return res;
    }
}
