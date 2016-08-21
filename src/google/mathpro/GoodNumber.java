package google.mathpro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 11/5/15.
 */
/*
* good number问题。 ⼀一个数如果能⽤用（⾄至少两组）两个⽴立⽅方数
相加得到那么就是good number。print ⼩小于等于n的所有good
number。
*  a^3 + b^3 <= n
*  c^3 + d^3 <= n
*
*  9:
*  1^3 + 2^3 <=
*
* */
public class GoodNumber {

    public List<Integer> goodNumbers(int n) {

        List<Integer> cubes = new ArrayList<>();
        // Important !!!
        for (int i = 1; i * i * i < n ; i ++) {
            cubes.add(i * i * i);
        }
        System.out.println(cubes);
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int i = 0 ; i < cubes.size() - 1 ; i ++) {
            for (int j = i + 1; j < cubes.size() ; j ++) {
                int sum = cubes.get(i) + cubes.get(j);
                if (sum <= n) {
                    if (counts.containsKey(sum)) {
                        int count = counts.get(sum) + 1;
                        counts.put(sum, count);
                    } else {
                        counts.put(sum, 1);
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: counts.entrySet()) {
            if (entry.getValue() > 1) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GoodNumber ob= new GoodNumber();
        System.out.println(ob.goodNumbers(1000000));
    }
}
