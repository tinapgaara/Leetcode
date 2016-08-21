package google.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yingtan on 11/27/15.
 */
public class JumpIterator<T> {

    private Iterator<T> iterator;
    private boolean hasNextFlag; // Important !!! use this flag

    public JumpIterator(Iterator<T> iterator) {
        this.iterator = iterator;
        hasNextFlag = true;
    }

    public boolean hasNext() {
        if (! hasNextFlag) {
            return false;
        }
        if (iterator.hasNext()) {
            return true;
        }
        return false;
    }

    public T next() {
        T res =  iterator.next();
        if (iterator.hasNext())
            iterator.next();
        else
            hasNextFlag = false;
        return res;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Iterator<Integer> iter = list.iterator();
        JumpIterator<Integer> ob = new JumpIterator<>(iter);
        while (ob.hasNext()) {
            System.out.println(ob.next());
        }
    }
}
