package google.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 12/22/15.
 *
 * Example:
 Given word = "word", return the following list (order does not matter):
 ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */
public class GeneralizedAbbreviation {

    public List<String> generateAbbreviationsRecur(String word) {
        // word = "" + w + recur(ord)
        // word = "1" + o + recur(rd)
        // word = "2" + r + recur(d)
        // everytime when genreate a digit, make sure a digit is followed

        List<String> res = new ArrayList<String>();
        //if (word == null || word.length() == 0) return res;
        int len = word.length();
        // word -> 4
        if (len > 0) res.add(len + "");
            // important !!!
        else res.add("");

        for (int i = 0 ; i < len; i ++) {
            List<String> rights = generateAbbreviationsRecur(word.substring(i+1));
            String left = "";
            if (i > 0) {
                left = String.valueOf(i);
            }
            for (String right : rights) {
                res.add(left + word.substring(i, i+1) + right);
            }
        }
        return res;
    }

    // bits:
    /*
    * 这道题让我们对一个单词进行部分简写，简写的规则是若干个字母可以用数字来表示，但是不能有两个相邻的数字，具体可以参考题目中给的例子，根据我以往的经验，这种列举所有情况的必定是要用DFS来写的，但是我一时半会又没想到该咋递归，后来我数了一下题目中给的例子的所有情况的个数，是16个，而word有4个字母，刚好是2的4次方，这是巧合吗，当然不是，后来我又发现如果把0到15的二进制写出来，每一个可以对应一种情况，如下所示：

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

        那么我们就可以观察出规律，凡是0的地方都是原来的字母，单独的1还是1，
        如果是若干个1连在一起的话，就要求出1的个数，用这个数字来替换对应的字母，既然规律找出来了，那么代码就很好写了，如下所示：


    *
    * */

    public List<String> generateAbbreviations_Bit(String word) {

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
                    if (consecutiveOne != 0) {
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

    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<String>();
        if ((word == null) || (word.length() == 0)) {
            res.add("");
            return res;
        }

        return recurGenerate(word, 0);
    }

    public List<String> recurGenerate(String word, int index) {
        List<String> res = new ArrayList<String>();
        if (index == word.length() -1) {
            res.add("1");
            res.add(word.substring(index));
            return res;
        }
        List<String> next = recurGenerate(word, index+1);
        for (String nextStr : next) {
            res.add(word.charAt(index) + nextStr);

            int temp = 0;
            int p = 0;
            while (p < nextStr.length() && Character.isDigit(nextStr.charAt(p))) {
                temp = temp * 10 + nextStr.charAt(p) - '0';
                p++;
            }
            res.add(Integer.toString(temp + 1) + nextStr.substring(p));
        }
        return res;
    }

    public static void main(String[] args) {
        GeneralizedAbbreviation ob = new GeneralizedAbbreviation();
        ob.generateAbbreviations("a");
    }
}
