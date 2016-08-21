package vmware.impl;
import java.util.Stack;

/**
 * Created by yingtan on 10/28/15.
 */
public class QueueByStack {

    private Stack<Integer> inbox;
    private Stack<Integer> outbox;

    public QueueByStack() {
        inbox = new Stack<>();
        outbox = new Stack<>();
    }

    public void enqueue(int element) {
        inbox.push(element);
    }

    public boolean isEmpty() {
        if ((inbox.isEmpty()) || (outbox.isEmpty()) ) return true;
        else return false;
    }

    public int dequeue() {
        int res =  -1;
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }
        }
        res = outbox.pop();
        return res;
    }
}
