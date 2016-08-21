package google.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/27/15.
 */
/*
* 扩展的斐波那契数列
* Sn = Sn
* Sn = Sn-j +  Sn-k (0<j<k)
 * 写一个 generator类。实现 next()方法
*
* */
public class FibIterator {

    private List<Integer> list;
    private int cur;
    private int k;
    private int j;

    public FibIterator(int j, int k) {
        this.j = j;
        this.k = k;
        list = new ArrayList<>();
        cur = 0;
    }

    public List<Integer> getList() {
        return list;
    }

    public int next() {

        int res = 0;
        if ((cur == 0) || (cur == 1)) {
            res = 1;
        }
        else {
            res = list.get(cur-j) + list.get(cur-k);
        }
        list.add(res);
        cur = list.size();
        return res;
    }

    public static void main(String[] args) {
        FibIterator ob = new FibIterator(1, 2);

        for (int i = 0 ; i < 10; i ++)
            ob.next();

        System.out.println(ob.getList());

    }

}
