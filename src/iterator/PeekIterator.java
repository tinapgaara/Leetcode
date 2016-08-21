package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yingtan on 9/27/15.
 */
public class PeekIterator implements Iterator<Integer> {

        public List<Integer> list ;
        public int curPos;

        public PeekIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            list = new ArrayList<Integer>();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
            curPos = 0;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return list.get(curPos);
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            int res = list.get(curPos);
            curPos ++;
            return res;
        }

        @Override
        public boolean hasNext() {
            if (curPos < list.size()) return true;
            else return false;
        }
}
