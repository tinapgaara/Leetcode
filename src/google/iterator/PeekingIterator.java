package google.iterator;

import java.util.Iterator;

/**
 * Created by yingtan on 11/21/15.
 */
public class PeekingIterator implements Iterator<Integer> {

    private Integer cache;
    private boolean cacheFlag;
    private Iterator<Integer> iter;

    public PeekingIterator(Iterator<Integer> iterator) {
        cache = null;
        cacheFlag = false;
        iter = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if ( ! cacheFlag) {
            cache = iter.next();
            cacheFlag = true;
        }
        return cache;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = null;
        if (cacheFlag) {
            res = cache;
            cache = null; // Important !!!! reset, will influence hasNext()
            cacheFlag = false; // no cache anymore
        }
        else {
            res = iter.next();
        }
        return res;
    }

    @Override
    public boolean hasNext() {
        if ((iter.hasNext()) || (cache != null))
            return true;
        return false;
    }
}
