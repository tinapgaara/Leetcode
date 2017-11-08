package uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yingtan on 5/20/17.
 *
 * 49. Group Anagrams Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 129826
 Total Submissions: 388007
 Difficulty: Medium
 Contributor: LeetCode
 Given an array of strings, group anagrams together.

 For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Return:

 [
 ["ate", "eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0) return res;

        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String newStr = new String(chs);

            if (map.containsKey(newStr)) {
                List<String> list = map.get(newStr);
                list.add(str);
            }
            else {
                List<String> list = new ArrayList<String>();
                list.add(str);
                map.put(newStr, list);
            }
        }

        for (List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }
}
