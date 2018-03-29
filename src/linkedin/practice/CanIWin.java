package linkedin.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/22/17.
 */
public class CanIWin {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        boolean[] vis = new boolean[maxChoosableInteger];
        Map<Integer, Boolean> states = new HashMap<>();
        return recurWin(maxChoosableInteger, 0, desiredTotal, vis, states);
    }

    public boolean recurWin(int maxInteger, int curTotal, int desireTotal, boolean[] vis, Map<Integer, Boolean> states) {
        int state = representArray(maxInteger, vis);
        if (states.containsKey(state)) {
            return states.get(state);
        }
        for (int i = 0 ; i < maxInteger; i ++) {
            if (! vis[i]) {
                if (curTotal + i >= desireTotal) {
                    states.put(state, true);
                    return true;
                }
                boolean ifNextWinnerWin = recurWin(maxInteger, curTotal + i, desireTotal, vis, states);
                if (! ifNextWinnerWin) {
                    states.put(state, true);
                    return true;
                }
            }
        }
        states.put(state, false);
        return false;
    }

    public int representArray(int maxChoosableInteger, boolean[] vis) {
        int res = 0;
        for (int i = 0; i < maxChoosableInteger; i ++) {
            if (vis[i]) {
                res = (res << 1) + 1;
            }
            else {
                res = (res << 1);
            }
        }
        return res;
        /*
        * 1 2 3 4
        * t f f t
        *
        * 1
        * 10
        * 100
        * 1001
        *
        * */
    }
}
