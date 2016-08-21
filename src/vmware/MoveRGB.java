package vmware;

/**
 * Created by yingtan on 10/28/15.
 */
/*
* 给一个字符串，字符串由三种字符组成: R, G, B. 例如 RBBGGGRB， 调整字符串，使得相同的字符排在一起：RRBBBGGG。
* 要求O(1) space complexity & O(n) time complexity.
* 我一开始说用3个counter，一次scan，记住每个字符出现的次数，然后reconstruct ordered result string。
*
*
* */
public class MoveRGB {

    // Solution 1: two pointer
    public void moveRGB(char[] chs) {
        // move all R to left side
        int low = 0;
        int high = chs.length-1;

        while (low < high) {
            // low = R   high = B/ G:  low ++ high --  high = R: low ++
            // low = B   high = R : swap, low ++, high --   high = B/G: high --
            // low = G   high = R: swap low ++ high --  high = B/G: high --;

            char lowCh = chs[low];
            char highCh = chs[high];
            if (lowCh == 'R') {
                low ++;
                if (highCh != 'R') {
                    high --;
                }
            }
            else {
                if (highCh == 'R') {
                    chs[low] = highCh;
                    chs[high] = lowCh;
                    low ++;
                }
                high --;
            }
        }
        // move all G to left side
        high = chs.length - 1;
        while ((low <= high) && (chs[low] == 'R')) low ++;
        while (low < high) {
            char lowCh = chs[low];
            char highCh = chs[high];
            if (lowCh == 'B') {
                low ++;
                if (highCh != 'B') {
                    high --;
                }
            }
            else {
                if (highCh == 'B') {
                    chs[low] = highCh;
                    chs[high] = lowCh;
                    low ++;
                }
                high --;
            }
        }
    }

    // Solution 2: use count

    public static void main(String[] args) {
        MoveRGB ob = new MoveRGB();
        char[] chs = new char[]{'R', 'B', 'G', 'R', 'B', 'G'};
        ob.moveRGB(chs);
        System.out.println("h");
    }
}
