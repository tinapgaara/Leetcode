package bloomberg.Impl;

import java.util.LinkedList;

/**
 * Created by yingtan on 11/14/15.
 */
public class Queue<T> {

    private LinkedList<T> m_list;
    private int m_size;

    public Queue() {
        m_list = new LinkedList<T>();
        m_size = 0;
    }

    public void offer(T element) {
        m_list.offer(element);
        m_size ++;
    }

    public T poll() {
        T res = m_list.removeFirst();
        m_size --;
        return res;
    }

    public boolean isEmpty() {
        return (m_size == 0);
    }

    public int size() {
        return m_size;
    }

}
