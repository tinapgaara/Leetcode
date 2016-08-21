package vmware.impl;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 10/28/15.
 */
public class StackByQueue {

    private Queue<Integer> inbox;
    private Queue<Integer> outbox;

    public StackByQueue() {
        inbox = new LinkedList<>();
        outbox = new LinkedList<>();
    }

    public void push(int element) {
        inbox.offer(element);
    }

    public int pop() {
        int res = -1;
        int size = inbox.size(); // Important !!!!!
        for (int i = 0 ; i < size-1; i ++) {
            outbox.offer(inbox.poll());
        }
        res = inbox.poll();

        // exchange inbox and outbox name
        Queue<Integer> tmp = outbox;
        outbox = inbox;
        inbox = tmp;

        return res;
    }

    public boolean isEmpty() {
        if ((inbox.isEmpty()) && (outbox.isEmpty()) ) return true;
        else return false;
    }

    public static void main(String[] args) {
        StackByQueue ob = new StackByQueue();
        ob.push(3);
        ob.push(4);
        System.out.println(ob.pop());
        ob.push(5);
        ob.push(6);
        System.out.println(ob.pop());
    }
}
