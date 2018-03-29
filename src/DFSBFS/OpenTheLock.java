package DFSBFS;

/**
 * Created by yingtan on 2/24/18.
 *
 * 752. Open the Lock
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

 The lock initially starts at '0000', a string representing the state of the 4 wheels.

 You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

 Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

 Example 1:
 Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 Output: 6
 Explanation:
 A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 because the wheels of the lock become stuck after the display becomes the dead end "0102".
 Example 2:
 Input: deadends = ["8888"], target = "0009"
 Output: 1
 Explanation:
 We can turn the last wheel in reverse to move from "0000" -> "0009".
 Example 3:
 Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 Output: -1
 Explanation:
 We can't reach the target without getting stuck.
 Example 4:
 Input: deadends = ["0000"], target = "8888"
 Output: -1
 */
import java.util.*;
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        if (deadends == null || target == null || deadends.length == 0 || target.length() == 0) {
            return 0;
        }
        Queue<Integer> dists = new LinkedList<>();
        Queue<String> strs = new LinkedList<>();
        dists.offer(0);
        strs.offer("0000");
        Set<String> vis = new HashSet<>();
        Set<String> invalid = new HashSet<>();
        for (String num : deadends) {
            invalid.add(num);
        }
        if (invalid.contains("0000")) {
            return -1;
        }
        while(! strs.isEmpty()) {
            String str = strs.poll();
            int dist = dists.poll();
            if (str.equals(target)) {
                return dist;
            }
            for (int i = 0 ; i < str.length(); i ++) {
                char[] chs = str.toCharArray();
                char ch = chs[i];
                char nextCh = (char)(ch + 1);
                if (ch == '9') {
                    nextCh = '0';
                }
                chs[i] = nextCh;
                String newstr = new String(chs);
                if (! invalid.contains(newstr) && ! vis.contains(newstr)) {
                    vis.add(newstr);
                    dists.offer(dist + 1);
                    strs.offer(newstr);
                }

                chs = str.toCharArray();
                nextCh = (char)(ch - 1);
                if (ch == '0') {
                    nextCh = '9';
                }
                chs[i] = nextCh;
                newstr = new String(chs);
                if (! invalid.contains(newstr) && ! vis.contains(newstr)) {
                    vis.add(newstr);
                    dists.offer(dist + 1);
                    strs.offer(newstr);
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        OpenTheLock ob = new OpenTheLock();
        String[] strs = {"0000"};
        System.out.println(ob.openLock(strs, "8888"));
    }
}
