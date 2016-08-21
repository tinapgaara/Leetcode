package stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/20/15.
 */
public class MinStack {

    private List<Integer> m_elements;
    private int m_top;

    public MinStack() {
        m_top = -1;
        m_elements = new ArrayList<Integer>();
    }

    public void push(int x) {
        int m_min;
        if (m_elements.size() == 0) {
            m_min = x;
        }
        else {
            m_min = getMin();
            if (x < m_min) {
                m_min = x;
            }
        }
        m_elements.add(x);
        m_top ++;

        m_elements.add(m_min);
        m_top ++;
    }

    public void pop() {
        m_elements.remove(m_top);
        m_top --;
        m_elements.remove(m_top);
        m_top --;
    }

    public int top() {
        return m_elements.get(m_top - 1);
    }

    public int getMin() {
        System.out.println(m_top);
        return m_elements.get(m_top);
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();

        char i = '2';
        int  y = (int)(i - '0');
        System.out.print(y);

        /*
        stack.push(-2);
        stack.push(0);
        stack.push(-1);
        */
    }
}
