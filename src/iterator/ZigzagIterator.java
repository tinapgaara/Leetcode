package iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/26/15.
 */
public class ZigzagIterator {

    public List<List<Integer>> m_lists;
    public int m_listNo;
    public int m_pos;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        m_lists = new ArrayList<List<Integer>>();
        m_lists.add(v1);
        m_lists.add(v2);

        findStartPoint(m_lists);

    }

    public int next() {
        int res = m_lists.get(m_listNo).get(m_pos);
        // how to move to next element ?
        m_listNo ++;
        int len = m_lists.size();
        if (m_listNo < len) {
            while (m_pos >= m_lists.get(m_listNo).size()) {
                m_listNo ++;
                if (m_listNo >= len)
                    break;
            }
        }
        if (m_listNo >= len) {
            m_listNo = 0;
            m_pos ++;

            int maxSize = m_lists.get(m_listNo).size();

            while (m_pos >= maxSize) {
                m_listNo ++ ;
                if (m_listNo >= len)
                    break;
                maxSize = m_lists.get(m_listNo).size();
            }
        }
        return res;
    }

    public boolean hasNext() {
        if ( (m_lists != null) || (m_lists.size() > 0) ) {
            if (m_listNo < m_lists.size()) {
                List<Integer> list = m_lists.get(m_listNo);
                if (m_pos < list.size())
                    return true;
            }
        }
        return false;
    }

    public void findStartPoint(List<List<Integer>> m_lists) {
        for (int i = 0 ; i < m_lists.size(); i ++) {
            List<Integer> list = m_lists.get(i);
            for (int j = 0 ; j < list.size(); j ++) {
                if (list.get(j) != null) {
                    m_listNo = i;
                    m_pos = j;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        l2.add(1);

        ZigzagIterator i = new ZigzagIterator(l1, l2);
        while (i.hasNext()) System.out.println(i.next());
    }
}
