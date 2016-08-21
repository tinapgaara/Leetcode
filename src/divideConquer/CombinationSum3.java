package divideConquer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/28/15.
 */
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        return recurCombineSum3(k, n, 1);
    }
    public List<List<Integer>> recurCombineSum3(int count, int sum, int low) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if ((sum <= 0) || (count == 0) || (low > 9) || (sum < low)) return res;
        if ((count == 1) && (sum >= low) && (sum <= 9) )  {
            List<Integer> single = new ArrayList<Integer>();
            single.add(sum);
            res.add(single);
            return res;
        }
        for (int i = low; i <= 9; i ++) {
            if ( (sum - i) < 0) break;
            List<List<Integer>> next = recurCombineSum3(count -1, (sum - i), low + 1);
            for (List<Integer> list :  next) {
                List<Integer> cur = new ArrayList<Integer>();
                if (i < list.get(0)) {
                    cur.add(i);
                    cur.addAll(list);
                    res.add(cur);
                }
            }
        }
        return res;
    }
}
