package google.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 8/24/17.
 *
 * 17. Letter Combinations of a Phone Number
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a digit string, return all possible letter combinations that the number could represent.

 A mapping of digit to letters (just like on the telephone buttons) is given below.



 Input:Digit string "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombination {

    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) return res;

        //recurCombineTop2Down(digits, 0, res, "");
        //return res;

        return recurCombineDown2Top(digits, 0);
    }

    public List<String> recurCombineDown2Top(String digits, int index) {
        // aggre from down to top
        List<String> res = new ArrayList<String>();
        if (index >= digits.length()) {
            res.add("");
            return res;
        }
        char ch = digits.charAt(index);
        String key = KEYS[ch - '0'];
        for (int i = 0 ; i < key.length(); i ++) {
            List<String> rest = recurCombineDown2Top(digits, index + 1);
            for (String restStr : rest) {
                res.add(key.charAt(i) + restStr);
            }
        }
        return res;
    }

    public void recurCombineTop2Down(String digits, int index, List<String> res, String cur) {
        // aggre from top to down
        if (index >= digits.length()) {
            res.add(cur);
            return;
        }

        char ch = digits.charAt(index);
        String key = KEYS[ch - '0'];
        for (int i = 0 ; i < key.length(); i ++) {
            String newstr = cur + key.charAt(i);
            recurCombineTop2Down(digits, index + 1, res, newstr);
        }
    }
}
