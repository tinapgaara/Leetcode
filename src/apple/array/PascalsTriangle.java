package apple.array;
import java.util.*;
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<Integer> prev = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        prev.add(1);
        res.add(new ArrayList<>(prev));
        if (numRows == 1) return res;
        for (int i = 1; i < numRows; i ++) {
            prev = res.get(res.size() - 1);
            if (prev.size() == 1) {
                // [1,1]
                List<Integer> cur = new ArrayList<>();
                cur.add(1);
                cur.add(1);
                res.add(new ArrayList<>(cur));
                continue;
            }
            else if (prev.size() > 1) {
                List<Integer> cur = new ArrayList<>();
                cur.add(prev.get(0));
                for (int j = 0; j < prev.size() - 1; j ++) {
                    cur.add(prev.get(j) + prev.get(j+1));
                }
                cur.add(prev.get(prev.size() - 1));
                res.add(cur);
            }
        }
        return res;
    }
}
