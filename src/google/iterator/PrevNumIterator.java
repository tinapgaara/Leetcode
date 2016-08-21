package google.iterator;

import java.util.Iterator;

/**
 * Created by yingtan on 11/28/15.
 */
public class PrevNumIterator<T> implements Iterator<T> {

    private Iterator<T> mIterator;
    private T mPrev, mCur;

    public PrevNumIterator(Iterator<T> iterator) {
        mIterator = iterator;
        mPrev = null;
        mCur = null;
    }

    @Override
    public boolean hasNext() { // decorator: contains iterator, and keep same iterface, but also provides a new function
        return mIterator.hasNext();
    }

    @Override
    public T next() {
        mPrev = mCur;
        mCur = mIterator.next();
        return mCur;
    }

    public T getPrev() {
        return mPrev;
    }
}
