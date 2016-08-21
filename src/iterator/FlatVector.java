package iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/21/15.
 */

public class FlatVector {

        public int m_x;
        public int m_y;
        public List<List<Integer>> m_vec;

        public FlatVector(List<List<Integer>> vec2d) {
            m_vec = vec2d;
            m_x = 0;
            m_y = 0;
        }

        public int next() {
            List<Integer> list = m_vec.get(m_x);

            if (list.size() == 0) {
                m_x ++;
                m_y = 0;
                if (hasNext()) {
                    System.out.println("next");
                    return next();
                } else {
                    return 0;
                }
            }
            Integer res = list.get(m_y);
            if ( (list.size() == 0) || (m_y == (list.size() - 1)) ) {
                m_x ++;
                m_y = 0;
            }
            else {
                m_y ++;
            }
            System.out.println("res:"+res);
            return res;
        }

        public boolean hasNext() {
            int row = m_vec.size();
            if (m_x >= row) {
                return false;
            }

            List<Integer> list = m_vec.get(m_x);
            int col = list.size();
            if ((m_y >= col)){
                return false;
            }


            return true;
        }

    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();

        List<Integer> n = new ArrayList<>();
        n.add(3);
        list.add(n);

        List<Integer> l = new ArrayList<>();
        list.add(l);

        FlatVector v = new FlatVector(list);
        List<Integer> res = new ArrayList<>();
        while (v.hasNext()) {
            res.add(v.next());
        }
        System.out.println(res);
    }

}
