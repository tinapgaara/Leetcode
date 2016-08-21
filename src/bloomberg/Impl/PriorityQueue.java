package bloomberg.Impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yingtan on 11/16/15.
 */
public class PriorityQueue {

    private List<Integer> elements;
    private int MAX_SIZE;

    private int heapSize; // important !!!!

    public PriorityQueue() {
        elements = new ArrayList<>();
    }

    public void offer(Integer element) {

        elements.add(Integer.MIN_VALUE); // add in tail: o(1)

        increaseKey(elements.size()-1, element); // o(logn)
        heapSize ++;

    }

    public Integer poll() { // extractMax  o(logn)

        int max = elements.get(0);
        int last = elements.get(heapSize-1);

        elements.set(0, last); // set complexity: o(1)

        heapSize --;

        maxHeap(0, heapSize); // o(logn)

        return max;
    }

    public void extractTopK(int k) {
        int maxHeap = heapSize; // important !!!

        for (int i = maxHeap-1; i >= maxHeap -k; i --) {
            int tmp = elements.get(i);
            int first = elements.get(0);
            elements.set(0, tmp);
            System.out.println(first);

            heapSize --;
            maxHeap(heapSize, 0);
        }
    }

    public Integer peak() {
        return elements.get(0); // o(1)
    }

    public boolean contains(Integer i) { // o(n)
        return elements.contains(i);
    }

    public void remove(Integer i) { // o(n)
        elements.remove(i);
    }

    private int parent(int cur) {
        return (cur-1) /2;
    }

    private int left(int cur) {
        return 2*cur + 1;
    }

    private int right(int cur) {
        return 2*cur + 2;
    }

    private void buildHeap() { // n/2 * o(logn)

        for (int i = heapSize / 2 ; i >= 0 ; i --) {
            maxHeap(heapSize, i);
        }
    }

    public void increaseKey(int index, int k) { // o(logn)
        elements.set(index, k); // set complexity: o(1)

        int cur = index;

        while ((parent(cur) >= 0) && (elements.get(cur) > elements.get(parent(cur)))) {
            int parentval = elements.get(parent(cur));
            int val = elements.get(cur);
            elements.set(cur, parentval);
            elements.set(parent(cur), val);

            cur = parent(cur);
            if (cur < 0) break;
        }
    }

    private void maxHeap(int maxHeapSize, int i) { // o(logn)

        int left = left(i);
        int max = elements.get(i);
        int maxIndex = i;
        if ((left < maxHeapSize) && (elements.get(left) > elements.get(i))) {
            max = elements.get(left);
            maxIndex = left;
        }

        int right = right(i);
        if ((right < maxHeapSize) && (elements.get(right) > elements.get(i)) ) {
            maxIndex = right;
            max = elements.get(right);
        }

        if (maxIndex != i) {
            int tmp = elements.get(i);
            elements.set(maxIndex, tmp);
            elements.set(i, max);

            maxHeap(maxHeapSize, maxIndex);
        }
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        queue.extractTopK(3);
        //System.out.println(queue.poll() + "," + queue.poll());//.;
    }

}
