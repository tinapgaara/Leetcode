package facebook.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.*;

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
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String key = new String(chs);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            }
            else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }
        res.addAll(map.values());
        return res;
    }
}
