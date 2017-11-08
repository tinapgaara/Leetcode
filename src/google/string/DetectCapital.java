package google.string;

/**
 * Created by yingtan on 11/5/17.
 *
 * 520. Detect Capital
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a word, you need to judge whether the usage of capitals in it is right or not.

 We define the usage of capitals in a word to be right when one of the following cases holds:

 All letters in this word are capitals, like "USA".
 All letters in this word are not capitals, like "leetcode".
 Only the first letter in this word is capital if it has more than one letter, like "Google".
 Otherwise, we define that this word doesn't use capitals in a right way.
 Example 1:
 Input: "USA"
 Output: True
 Example 2:
 Input: "FlaG"
 Output: False
 */
public class DetectCapital {

    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) return true;
        // not allowed : aA  , AaA
        // allow: Aaaaa AAA aaa
        char prev = word.charAt(0);
        boolean prevLower = Character.isLowerCase(prev);
        for (int i = 1 ; i < word.length(); i ++) {
            boolean curLower = Character.isLowerCase(word.charAt(i));
            if (prevLower && !curLower) {
                return false;
            }
            if (!prevLower && curLower && i > 1) {
                return false;
            }
            prevLower = curLower;
        }
        return true;
    }
}
