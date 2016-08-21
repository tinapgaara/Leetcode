package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/26/15.
 */
public class GetFactors {

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (n == 1) return res;

        for (int i = 2; i < n; i ++) {
            if (n % i == 0) {
                int divide = n / i;
                List<Integer> newList = new ArrayList<Integer>();
                newList.add(i);
                res.addAll(recurGetFactors(divide, newList));
            }
        }
        return res;
    }
    public List<List<Integer>> recurGetFactors(int n, List<Integer> list) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> firstcopy = new ArrayList<Integer>(list);
        if (n >= list.get(list.size() - 1)) {
            firstcopy.add(n);
            res.add(firstcopy);
        }

        for (int i = 2; i < n; i ++) {
            if (n % i == 0) {
                int divide = n / i;
                if ( (divide >= i) && (i >= list.get(list.size() - 1)) ){
                    List<Integer> copy = new ArrayList<Integer>(list);
                    copy.add(i);
                    res.addAll(recurGetFactors(divide, copy));
                }
            }
        }
        return res;
    }
}
