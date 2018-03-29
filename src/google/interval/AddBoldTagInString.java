package google.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yingtan on 9/14/17.
 *
 * 616. Add Bold Tag in String
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

 Example 1:
 Input:
 s = "abcxyz123"
 dict = ["abc","123"]
 Output:
 "<b>abc</b>xyz<b>123</b>"
 Example 2:
 Input:
 s = "aaabbcc"
 dict = ["aaa","aab","bc"]
 Output:
 "<b>aaabbc</b>c"
 */
public class AddBoldTagInString {
    public String addBoldTag(String s, String[] dict) {
        if(s == null || dict == null || s.length() == 0) {
            return "";
        }
        if (dict.length == 0) {
            return s;
        }
        List<int[]> list = new ArrayList<int[]>();
        // o(dict.length * s.length)
        for (String word : dict) {
            for (int i = 0 ; i < s.length(); i ++) {
                int end = i + word.length();
                if (end <= s.length() && s.substring(i, end).equals(word)) {
                    int[] index = new int[2];
                    index[0] = i;
                    index[1] = end;

                    list.add(index);
                }
            }
        }
        if (list.size() == 0) return s;

        // sort the list o(l.length log(l.length))
        Collections.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int[] prev = list.get(0);
        StringBuilder builder = new StringBuilder();
        int last = 0;
        builder.append(s.substring(last, prev[0]));
        for (int i = 1; i < list.size(); i ++) {
            int[] cur = list.get(i);
            if (! overlap(prev, cur)) {
                // add prev
                int strStart = prev[0];
                int strEnd = prev[1];
                builder.append("<b>" + s.substring(strStart, strEnd) + "</b>");
                last = strEnd;
                builder.append(s.substring(last, cur[0]));
                prev = cur;

            }
            else {
                int strStart = Math.min(prev[0],cur[0]);
                int strEnd = Math.max(prev[1], cur[1]);
                prev = new int[]{strStart, strEnd};
            }
        }
        builder.append("<b>" + s.substring(prev[0], prev[1]) + "</b>");
        builder.append(s.substring(prev[1], s.length()));
        return builder.toString();
    }

    public boolean overlap(int[] prev, int[] cur) {
        if (prev[0] <= cur[1] && (prev[1] >= cur[0] || prev[1] == cur[0] + 1)) return true;
        else return false;
    }

    public static  void main(String[] args) {
        AddBoldTagInString ob = new AddBoldTagInString();
        String s = "aaabbcc";
        String[] dict = new String[]{"aaa","aab", "bc"};
        ob.addBoldTag(s, dict);
    }
}
