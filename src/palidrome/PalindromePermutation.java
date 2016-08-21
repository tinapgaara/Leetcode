package palidrome;

import java.util.*;

/**
 * Created by yingtan on 9/22/15.
 */
public class PalindromePermutation {

    // Given a string s,
    // return all the palindromic permutations (without duplicates) of it.
    // Return an empty list if no palindromic permutation could be form.

    // Solution 1: generate half side, time exceed limit
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

    private boolean isPalindromePermutation(String s, Map<Character, Integer> map) {
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


    // Solution 2: generate from left and right point, move to middle point , time exceed limit
    public List<String> generatePalindromes_2(String s) {
        List<String> res = new ArrayList<String>();
        if ((s == null) || (s.length() == 0) ) return res;

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        int len = s.length();
        int odd = 0;
        for (int i = 0 ; i < len; i ++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                int count = map.get(ch) + 1;
                map.put(ch, count);
                if ((count % 2) != 0) odd++;
                else odd --;
            }
            else {
                map.put(ch, 1);
            }
        }
        if (odd > 1) return res;

        res.add("");
        Map<String, Map<Character, Integer>> maps = new HashMap<String, Map<Character, Integer>>();
        maps.put("", map);
        int low = 0;
        int high = len - 1;
        while (low < high) {
            System.out.println(low + "," + high);
            List<String> newres = new ArrayList<>();
            for (int i = 0 ; i < res.size(); i ++) {
                String str = res.get(i);
                Map<Character, Integer> existedCount = maps.get(str);
                if (existedCount != null) {
                    for (Map.Entry<Character, Integer> entry : existedCount.entrySet()) {
                        char ch = entry.getKey();
                        int count = entry.getValue();
                        if (count >= 2) {
                            int middle = str.length() / 2;
                            String newstr = str.substring(0, middle) + ch + ch +
                                    str.substring(middle, str.length());
                            newres.add(newstr);

                            Map<Character, Integer> copy =
                                    new HashMap<Character, Integer>(existedCount);
                            count = count - 2;
                            if (count <= 0 ) copy.remove(ch);
                            else copy.put(ch, count);

                            if (copy.size() > 0) maps.put(newstr, copy);
                        }
                    }
                }
            }
            res = newres;
            low ++ ;
            high --;
        }

        if ( (low == high) && (res.size() > 0)){
            List<String> newres = new ArrayList<>();
            if (maps.size() > 1) {
                for (int i = 0; i < res.size(); i++) {
                    String str = res.get(i);
                    Map<Character, Integer> existedCount = maps.get(str);
                    if (existedCount != null) {
                        for (Map.Entry<Character, Integer> entry : existedCount.entrySet()) {
                            char ch = entry.getKey();
                            int count = entry.getValue();
                            if (count == 1) {
                                int middle = str.length() / 2;
                                String newstr = str.substring(0, middle) + ch +
                                        str.substring(middle, str.length());

                                newres.add(newstr);
                            }
                        }
                    }
                }
            }
            else {
                newres.add(s);
            }
            res = newres;
        }
        return res;
    }

    public static void main(String[] args) {
        PalindromePermutation ob = new PalindromePermutation();
        System.out.println(ob.generatePalindromes("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"));
    }
}
