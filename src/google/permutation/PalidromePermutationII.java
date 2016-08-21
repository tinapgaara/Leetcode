package google.permutation;

import java.util.*;

/**
 * Created by yingtan on 12/22/15.
 */
/*
*
* Given a string s, return all the palindromic permutations (without duplicates) of it.
* Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Show Hint
Show Tags
Show Similar Problems

*
* */
public class PalidromePermutationII {

    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<String>();
        if ((s == null) || (s.length() == 0) ) return res;

        Map<Character, Integer> map = new HashMap();
        if (!isPalindromePermutation(s, map)) {
            return res;
        }
        String half = "";
        Character single = null;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            char ch = entry.getKey();
            if (count % 2 == 0) {
                int halfCount = count / 2;
                for (int i = 0 ; i < halfCount; i ++)
                    half = half + ch;
            }
            else if (count >= 1){
                int halfCount = (count - 1)/2;
                single = new Character(ch);
                for (int i = 0 ; i < halfCount; i ++)
                    half = half + ch;
            }
        }
        char[] chs = half.toCharArray();
        List<List<Character>> halfStrs = permuteUnique(chs);
        for (List<Character> list : halfStrs) {
            String str = "";
            String anotherHalf = "";
            int listSize = list.size();

            for (int i = 0 ; i < list.size(); i ++) {
                str = str + list.get(i);
                anotherHalf = list.get(i) + anotherHalf;
            }
            if (single != null) {
                str = str + single.charValue() + anotherHalf;
            }
            else
                str = str + anotherHalf;
            res.add(str);
        }
        if ((halfStrs.size() == 0) && (single != null))  res.add(single.toString());
        return res;
    }

    // The key to solve the boundary case. If number of numbers is odd, and there are more than 1 numbers to be odd. Then, we should return false directly.
    public boolean isPalindromePermutation(String s, Map<Character, Integer> map) {
        int tolerance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int val = map.get(c);
                map.put(c, val + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int val = (int) entry.getValue();
            if (val % 2 == 1) {
                tolerance++;
            }
        }

        if (tolerance <= 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<List<Character>> permuteUnique(char[] nums) {
        List<List<Character>> res = new ArrayList<List<Character>>();
        Arrays.sort(nums);
        LinkedList<Character> list = new LinkedList<Character>();
        for (char num : nums) list.add(num);
        permute(list, 0, res);
        return res;
    }

    public void permute(LinkedList<Character> nums, int start, List<List<Character>> res){
        if (start == nums.size() - 1){
            res.add(new LinkedList<Character>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++){
            if (i > start && nums.get(i) == nums.get(i - 1)) continue;
            nums.add(start, nums.get(i));
            nums.remove(i + 1);
            permute(nums, start + 1, res);
            nums.add(i + 1, nums.get(start));
            nums.remove(start);
        }
    }
}
