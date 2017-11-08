package google.string;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 8/7/17.
 *
 * 408. Valid Word Abbreviation
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
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
 *
 * 这道题让我们验证单词缩写，关于单词缩写LeetCode上还有两道相类似的题目Unique Word Abbreviation和Generalized Abbreviation。这道题给了我们一个单词和一个缩写形式，让我们验证这个缩写形式是否是正确的，由于题目中限定了单词中只有小写字母和数字，所以我们只要对这两种情况分别处理即可。我们使用双指针分别指向两个单词的开头，循环的条件是两个指针都没有到各自的末尾，如果指向缩写单词的指针指的是一个数字的话，如果当前数字是0，返回false，因为数字不能以0开头，然后我们要把该数字整体取出来，所以我们用一个while循环将数字整体取出来，然后指向原单词的指针也要对应的向后移动这么多位数。如果指向缩写单词的指针指的是一个字母的话，那么我们只要比两个指针指向的字母是否相同，不同则返回false，相同则两个指针均向后移动一位，参见代码如下
 */
public class ValidWordAbbreviation {

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

        PriorityQueue<Pair<String, Integer>> queue = new PriorityQueue<Pair<String, Integer>>(new Comparator<Pair<String, Integer>>() {
            @Override public int compare(Pair o1, Pair o2) {
                return (Integer)o1.getValue() - (Integer)o2.getValue();
            }
        });
        // important !
        if(p1 == word.length() && p2 == abbr.length()) return true;
        else return false;
    }
}
