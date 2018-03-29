package uber.string;

/**
 * Created by yingtan on 3/26/18.
 *
 * 249. Group Shifted Strings
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

 "abc" -> "bcd" -> ... -> "xyz"
 Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

 For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 A solution is:

 [
 ["abc","bcd","xyz"],
 ["az","ba"],
 ["acef"],
 ["a","z"]
 ]

 */
import java.util.*;
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            char prev = str.charAt(0);
            String encodeStr = "";
            for (int i = 1; i < str.length(); i ++) {
                char cur = str.charAt(i);
                int digit = (cur - prev + 26) % 26;// // Important !!!!
                encodeStr = encodeStr + digit;
                prev = cur;
            }
            if (map.containsKey(encodeStr)) {
                map.get(encodeStr).add(str);
            }
            else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(encodeStr, list);
            }
        }
        res.addAll(map.values());
        return res;
    }
}
