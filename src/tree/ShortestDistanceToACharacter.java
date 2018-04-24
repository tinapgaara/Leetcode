package tree;

import java.util.*;

/**
 * Created by yingtan on 4/23/18.
 *
 * 821. Shortest Distance to a Character
 DescriptionHintsSubmissionsDiscussSolution
 Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.

 Example 1:

 Input: S = "loveleetcode", C = 'e'
 Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]


 Note:

 S string length is in [1, 10000].
 C is a single character, and guaranteed to be in string S.
 All letters in S and C are lowercase.

 */
public class ShortestDistanceToACharacter {
    public int[] shortestToChar(String S, char C) {
        TreeSet<Integer> indexs = new TreeSet<>();
        for (int i = 0 ; i < S.length(); i ++) {
            char ch = S.charAt(i);
            if (ch == C) {
                indexs.add(i);
            }
        }
        int[] res = new int[S.length()];
        for (int i = 0 ; i < S.length(); i ++) {
            char ch = S.charAt(i);
            if (ch == C) continue;
            Integer floor = indexs.floor(i);
            int leftDist = Integer.MAX_VALUE;
            if (floor != null) {
                leftDist = i - floor;
            }
            int rightDist = Integer.MAX_VALUE;
            Integer ceil = indexs.ceiling(i);
            if (ceil != null) {
                rightDist = ceil - i;
            }
            res[i] = Math.min(rightDist, leftDist);
        }
        return res;
    }
}
