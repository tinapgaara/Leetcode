package DFSBFS;

/**
 * Created by yingtan on 2/24/18.
 *
 * 488. Zuma Game
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.

 Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.

 Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.

 Examples:

 Input: "WRRBBW", "RB"
 Output: -1
 Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW

 Input: "WWRRBBWW", "WRBRW"
 Output: 2
 Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty

 Input:"G", "GGGGG"
 Output: 2
 Explanation: G -> G[G] -> GG[G] -> empty

 Input: "RBYYBBRRB", "YRBGB"
 Output: 3
 Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty
 */
import java.util.*;
public class ZumaGame {

    public int findMinStep(String board, String hand) {
        if (board == null || board.length() == 0 || hand == null || hand.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : hand.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            }
            else {
                map.put(ch, 1);
            }
        }
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        // Solution 1:  using dfs without memorization, pass max[0]. top-to-down
        //dfs_topToDown(board, map, min, 0);
        /*
        if (min[0] == Integer.MAX_VALUE) {
            return -1;
        }
        return min[0];
        */
        // Solution 2: using dfs with memorization, return from buttom, down-to-top: faster
        Map<String, Integer> mininserts = new HashMap<>();
        String key = encode(board, map);
        dfs_downToTop(board, map, mininserts);

        if (mininserts.get(key) != Integer.MAX_VALUE) {
            return mininserts.get(key);
        }
        else {
            return -1;
        }
    }
    public int dfs_downToTop(String str, Map<Character, Integer> map, Map<String, Integer> mininserts) {
        if (str.length() == 0) {
            // base case
            return 0;
        }
        String key = encode(str, map);
        if (mininserts.containsKey(key)) {
            return mininserts.get(key);
        }
        int prev = 0;
        int samecount = 1;
        int mininsert = Integer.MAX_VALUE;
        for (int i = 1 ; i < str.length(); i ++) {
            char cur = str.charAt(i);
            if (str.charAt(prev) != cur) {
                if (samecount >= 3) {
                    // can remove them
                    String newstr = str.substring(0, prev) + str.substring(i);
                    int insert = dfs_downToTop(newstr, map, mininserts);
                    mininsert = Math.min(mininsert, insert);
                } else {
                    // insert
                    char insertCh = str.charAt(prev);
                    if (map.containsKey(insertCh) && map.get(insertCh) > 0) {
                        if (map.get(insertCh) == 1) {
                            map.remove(insertCh);
                        } else {
                            map.put(insertCh, map.get(insertCh) - 1);
                        }
                        String newstr;
                        // important !!!!  need to change WWW -> "" here
                        if (samecount >= 2) {
                            newstr = str.substring(0, prev) + str.substring(i);
                        } else {
                            newstr = str.substring(0, i) + insertCh + str.substring(i);
                        }
                        int nextinserts = dfs_downToTop(newstr, map, mininserts);
                        if (nextinserts != Integer.MAX_VALUE) {
                            // able to use this way
                            // we do insertion here, so add 1 to step
                            int insert = 1 + nextinserts;
                            mininsert = Math.min(mininsert, insert);
                        }// trace back
                        if (map.containsKey(insertCh)) {
                            map.put(insertCh, map.get(insertCh) + 1);
                        } else {
                            map.put(insertCh, 1);
                        }
                    }
                }
                samecount = 1;
                prev = i;
            } else {
                samecount++;
            }
        }
        if (samecount >= 3) {
            // WWW
            mininsert = Math.min(mininsert, dfs_downToTop(str.substring(0, prev), map, mininserts));
        }
        else {
            char insertCh = str.charAt(prev);
            if (map.containsKey(insertCh) && map.get(insertCh) > 0) {
                if (map.get(insertCh) == 1) {
                    map.remove(insertCh);
                }
                else {
                    map.put(insertCh, map.get(insertCh) - 1);
                }
                String newstr;
                if (samecount >= 2) {
                    // RR + R -> RRR -> ""
                    newstr = str.substring(0, prev);
                }
                else {
                    newstr = str + insertCh;
                }
                int nextinserts = dfs_downToTop(newstr, map, mininserts);
                if (nextinserts != Integer.MAX_VALUE) {
                    mininsert = Math.min(mininsert, 1 + nextinserts);
                }
                if (map.containsKey(insertCh)) {
                    map.put(insertCh, map.get(insertCh) + 1);
                }
                else {
                    map.put(insertCh, 1);
                }
            }
        }
        // already calculated mininserts for this combination of map and str
        mininserts.put(key, mininsert);
        return mininsert;
    }
    public String encode(String str, Map<Character, Integer> map) {
        String res = str + "_";
        for (Character mapKey : map.keySet()) {
            res = res + mapKey + map.get(mapKey);
        }
        return res;
    }
    public void dfs_topToDown(String str, Map<Character, Integer> map, int[] min, int insert) {
        // base case
        if (str.length() == 0) {
            min[0] = Math.min(min[0], insert);
            return;
        }
        int prev = 0;
        int samecount = 1;
        for (int i = 1 ; i < str.length(); i ++) {
            char cur = str.charAt(i);
            if (str.charAt(prev) != cur) {
                // check count
                if (samecount >= 3) {
                    // can remove them
                    String newstr = str.substring(0, prev) + str.substring(i);
                    dfs_topToDown(newstr, map, min, insert);
                }
                else {
                    // insert
                    char insertCh = str.charAt(prev);
                    if (map.containsKey(insertCh) && map.get(insertCh) > 0) {
                        if (map.get(insertCh) == 1) {
                            map.remove(insertCh);
                        }
                        else {
                            map.put(insertCh, map.get(insertCh) - 1);
                        }
                        String newstr;
                        // important !!!!  need to change WWW -> "" here
                        if (samecount >= 2) {
                            newstr = str.substring(0, prev) + str.substring(i);
                        }
                        else {
                            newstr = str.substring(0, i) + insertCh + str.substring(i);
                        }
                        dfs_topToDown(newstr, map, min, insert + 1);
                        // trace back
                        if (map.containsKey(insertCh)) {
                            map.put(insertCh, map.get(insertCh) + 1);
                        }
                        else {
                            map.put(insertCh, 1);
                        }
                    }
                }
                samecount = 1;
                prev = i;
            }
            else {
                samecount ++;
            }
        }
        if (samecount >= 3) {
            // WWW
            dfs_topToDown(str.substring(0, prev), map, min, insert);
        }
        else {
            char insertCh = str.charAt(prev);
            if (map.containsKey(insertCh) && map.get(insertCh) > 0) {
                if (map.get(insertCh) == 1) {
                    map.remove(insertCh);
                }
                else {
                    map.put(insertCh, map.get(insertCh) - 1);
                }
                String newstr;
                if (samecount >= 2) {
                    // RR + R -> RRR -> ""
                    newstr = str.substring(0, prev);
                }
                else {
                    newstr = str + insertCh;
                }
                dfs_topToDown(newstr, map, min, insert + 1);
                if (map.containsKey(insertCh)) {
                    map.put(insertCh, map.get(insertCh) + 1);
                }
                else {
                    map.put(insertCh, 1);
                }
            }
        }
    }
}
