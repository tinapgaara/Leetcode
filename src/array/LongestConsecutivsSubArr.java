package array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingtan on 11/14/15.
 */
public class LongestConsecutivsSubArr {

    public int longestConsecutiveSubArr(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0 ; i < arr.length ; i ++) {
            set.add(arr[i]);
        }
        int maxLen = 0;
        int curLen = 0;
        while (!set.isEmpty()) {
            int min = findMin(set);
            curLen ++;
            int next = min + 1;
            while (set.contains(next)) {
                set.remove(next);
                curLen ++;
                next = next + 1;
            }
            maxLen = Math.max(maxLen, curLen);
            curLen = 0;
        }
        return maxLen;
    }

    public int findMin(Set<Integer> set) {

        int min = Integer.MAX_VALUE;
        for (Integer element : set) {
            min = Math.min(min, element);
        }
        set.remove(min);
        return min;
    }

    public static void main(String[] args) {
        LongestConsecutivsSubArr ob = new LongestConsecutivsSubArr();
        int[] arr = new int[]{5,7,6,26,27,28,29,30};
        System.out.println(ob.longestConsecutiveSubArr(arr));
    }
}
