package google.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 12/22/15.
 */
public class GeneralizedAbbreviation {

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
