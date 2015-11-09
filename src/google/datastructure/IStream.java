package google.datastructure;

/**
 * Created by yingtan on 11/8/15.
 */
public interface IStream<T> {

    public T peek();
    public T next();
    public boolean hasNext();
    public void append(T x);
}
