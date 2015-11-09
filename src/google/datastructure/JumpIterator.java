package google.datastructure;

import java.util.Iterator;

/**
 * Created by yingtan on 11/8/15.
 */
public class JumpIterator {

    public Iterator<Integer> iter;
    public JumpIterator(Iterator<Integer> iter) {
        this.iter = iter;
    }
    public int next() {

        if (iter.hasNext()) {
            return iter.next();
        }
        return 0;
    }
    public boolean hasNext() {
        if (iter.hasNext()) {
            iter.next();
            if (iter.hasNext()) return true;
        }
        return false;
    }

}
