package dataStructure;

import java.util.Stack;

/**
 * Created by yingtan on 9/1/15.
 */
public class Stack2Queue {

    // Push element x to the back of queue.
    private Stack<Integer> mStackA;
    private Stack<Integer> mStackB;

    public Stack2Queue() {
        mStackA = new Stack<Integer>();
        mStackB = new Stack<Integer>();
    }

    public void push(int x) {
        if ( ! mStackA.isEmpty()) {
            mStackA.push(x);
        }
        else {
            while (! mStackB.isEmpty()) {
                mStackA.push(mStackB.pop());
            }
            mStackA.push(x);
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        if ( ! mStackA.isEmpty()) {
            int len = mStackA.size();
            for(int i = 0 ; i < len - 1; i ++) {
                mStackB.push(mStackA.pop());
            }
            mStackA.pop();
        }
        else if (! mStackB.isEmpty()) {
            mStackB.pop();
        }
    }

    // Get the front element.
    public int peek() {
        if (! mStackB.isEmpty()) {
            return mStackB.peek().intValue();
        }
        else if ( ! mStackA.isEmpty()) {
            int len = mStackA.size();
            for(int i = 0 ; i < len - 1; i ++) {
                mStackB.push(mStackA.pop());
            }
            Integer popElement = mStackA.pop();
            mStackB.push(popElement);
            return popElement.intValue();
        }
        return -1;
    }

    // Return whether the queue is empty.
    public boolean empty() {

        if (mStackA.isEmpty() && mStackB.isEmpty()) {
            return true;
        }
        return false;
    }
}
