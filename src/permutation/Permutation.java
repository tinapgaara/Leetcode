package permutation;

import java.util.*;

/**
 * Created by yingtan on 9/21/15.
 */
public class Permutation {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null) {
            return res;
        }
        return recurPermute(nums, 0, nums.length - 1);
    }

    public List<List<Integer>> recurPermute(int[] nums, int low, int high) {
        if ((low == high)  && (low < nums.length)) {
            List<List<Integer>> list = new ArrayList<List<Integer>>();
            List<Integer> l = new ArrayList<Integer>();
            l.add(nums[low]);
            list.add(l);
            return list;
        }

        int num = nums[low];
        List<List<Integer>> next = recurPermute(nums, low + 1, high);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (List<Integer> list : next) {
            for (int i = 0 ; i < list.size(); i ++) {
                List<Integer> copy = new ArrayList<Integer>(list);
                copy.add(i, num);
                res.add(copy);
            }
            List<Integer> lastCopy = new ArrayList<Integer>(list);
            lastCopy.add(num);
            res.add(lastCopy);
        }
        return res;
    }

    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        char[] chs = s.toCharArray();
        int len = chs.length;

        for (int i = 0 ; i < chs.length; i ++) {
            char ch = chs[i];
            if (map.containsKey(ch)) {
                int count = map.get(ch);
                map.put(ch, (count+1));
            }
            else {
                map.put(ch, 1);
            }
        }
        int oddCount = 0;
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            int value = entry.getValue();
            if ((value % 2) != 0) {
                oddCount ++;
            }
        }
        if ((len % 2) == 0) {
            if (oddCount == 0) {
                return true;
            }
        }
        else {
            if (oddCount == 1) {
                return true;
            }
        }
        return false;
    }
}
