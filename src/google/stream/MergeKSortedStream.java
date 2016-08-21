package google.stream;

import java.util.*;

/**
 * Created by yingtan on 11/27/15.
 */
/*
*
* 写一个 merge List of sorted stream, merge List of sorted stream,
 *  就是的 k-way merge.
 *  然后因为是 然后因为是 generic,
 *  传入参数要包 传入参数要包 传入参数要包括一个 comparator.
* */
public class MergeKSortedStream<T> {

    private List<Iterator<T>> iter;
    private PriorityQueue<IterWrapper> queue;

    public MergeKSortedStream(List<Iterator<T>> lists) {
        iter = lists;
        IterComparator comparator = new IterComparator();
        queue = new PriorityQueue<IterWrapper>(comparator);

        for (Iterator<T> iter : lists) {
            queue.offer(new IterWrapper(iter, iter.next()));
        }
    }

    public class IterWrapper {
        private Iterator<T> iter;
        private T topElement;

        public IterWrapper(Iterator<T> iter, T topElement) {
            this.iter = iter;
            this.topElement = topElement;
        }
    }

    public class IterComparator implements Comparator<IterWrapper> {
        public int compare(IterWrapper w1, IterWrapper w2) {
            if ((Integer)w1.topElement > (Integer)w2.topElement) {
                return -1;
            }
            else if ((Integer)w1.topElement < (Integer)w2.topElement) {
                return 1;
            }
            return 0;
        }
    }

    public List<T> merge() {
        List<T> res = new ArrayList<T>();
        while (!queue.isEmpty()) {
            IterWrapper wrapper = queue.poll();
            T num = wrapper.topElement;
            Iterator<T> iter = wrapper.iter;
            res.add(num);
            if (iter.hasNext())
                queue.offer(new IterWrapper(iter, iter.next()));

        }
        return res;
    }


    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list2 = Arrays.asList(7, 8, 9, 10);
        Iterator<Integer> iter1 = list.iterator();
        Iterator<Integer> iter2 = list2.iterator();

        List<Iterator<Integer>> lists = Arrays.asList(iter1, iter2);
        MergeKSortedStream<Integer> ob = new MergeKSortedStream<>(lists);
        System.out.println(ob.merge());
    }

}
