package dataStructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 9/1/15.
 */
public class Queue2Stack {

    private Queue<Integer> mQueue_A;
    private Queue<Integer> mQueue_B;

    public Queue2Stack() {
        mQueue_A = new LinkedList<Integer>();
        mQueue_B = new LinkedList<Integer>();
    }

    // Push element x onto stack.
    public void push(int x) {
        if ( ! mQueue_A.isEmpty()) {
            mQueue_A.offer(x);
        }
        else if ( ! mQueue_B.isEmpty()) {
            mQueue_B.offer(x);
        }
        else if (mQueue_A.isEmpty() && mQueue_B.isEmpty()) {
            mQueue_A.offer(x);
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        if ( ! mQueue_A.isEmpty()) {
            int len = mQueue_A.size();
            for (int i = 0 ; i < len -1; i ++) {
                mQueue_B.offer(mQueue_A.poll());
            }
            mQueue_A.poll();
        }
        else if ( ! mQueue_B.isEmpty()) {
            int len = mQueue_B.size();
            for (int i = 0 ; i < len -1; i ++) {
                mQueue_A.offer(mQueue_B.poll());
            }
            mQueue_B.poll();
        }
    }

    // Get the top element.
    public int top() {
        if ( ! mQueue_A.isEmpty()) {
            int len = mQueue_A.size();
            for (int i = 0 ; i < len -1; i ++) {
                mQueue_B.offer(mQueue_A.poll());
            }
            Integer topElement = mQueue_A.poll();
            mQueue_B.offer(topElement);
            return topElement.intValue();
        }
        else if ( ! mQueue_B.isEmpty()) {
            int len = mQueue_B.size();
            for (int i = 0 ; i < len -1; i ++) {
                mQueue_A.offer(mQueue_B.poll());
            }
            Integer topElement = mQueue_B.poll();
            mQueue_A.offer(topElement);
            return topElement.intValue();
        }
        return -1;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        if (mQueue_A.isEmpty() && mQueue_B.isEmpty()) {
            return true;
        }
        else
            return false;

    }

}
