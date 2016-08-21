package BitOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/19/15.
 */
public class GrayCodeXOR {

    // Solution:
    // Reverse only one nth digit in a number: XOR
    // number ^ (1 << n)
    public List<Integer> grayCode(int n) {

        List<Integer> res = new ArrayList<Integer>();

        int curVal = 0;
        res.add(curVal);

        int max = 1 << n;
        boolean[] visitedFlags = new boolean[max];
        visitedFlags[0] = true;

        int count = 1;

        while (count < max) {
            for (int i = 0 ; i < n; i ++) {
                int reverseVal = 1 << i;
                int nextVal = curVal ^ reverseVal;
                if (visitedFlags[nextVal]) {
                    continue;
                }
                else {
                    count ++;
                    curVal = nextVal;
                    visitedFlags[curVal] = true;
                    res.add(curVal);
                    break;
                }
            }
        }
        return res;
    }
}
