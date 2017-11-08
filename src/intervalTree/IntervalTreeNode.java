package intervalTree;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by yingt on 2017/10/26.
 */

public class IntervalTreeNode {

    int m_start = Integer.MIN_VALUE;
    int m_end = Integer.MAX_VALUE;
    int m_middle;

    Rect m_rect = null;

    IntervalTreeNode m_left, m_right;

    public IntervalTreeNode(int start, int end, Rect rect) {
        m_start = start;
        m_end = end;
        m_middle = (start + end) / 2;

        m_rect = rect;

        m_left = null;
        m_right = null;
    }

    public boolean add(Rect rect) {
        if ( (m_left == null) && (m_right == null) && (m_rect == null) ) {
            m_rect = rect;
            return false;
        }

        if (m_rect != null) {
            if (m_rect.intersectY(rect)) return true;  // We do NOT continue here !!!
        }

        if (m_left == null)
            m_left = new IntervalTreeNode(m_start, m_middle, null);
        if (m_right == null)
            m_right = new IntervalTreeNode(m_middle, m_end, null);
        if (m_rect != null) {
            descend2SubNodes(m_rect);
            m_rect = null;
        }

        if (rect.m_leftY < m_middle) return m_left.add(rect);
        else if (rect.m_rightY >= m_middle) return m_right.add(rect);
        else {
            boolean overlap = m_left.add(rect);
            if (overlap) return true;
            return m_right.add(rect);
        }
    }

    public void remove(Rect rect) {
        if (m_rect == rect) {
            m_rect = null;
            return;
        }

        if (rect.m_leftY < m_middle) m_left.remove(rect);
        else if (rect.m_rightY >= m_middle) m_right.add(rect);
        else {
            m_left.remove(rect);
            m_right.remove(rect);
        }
    }

    // only leaf node contains rect
    private void descend2SubNodes(Rect rect) {
        if (rect.m_leftY < m_middle) m_left.m_rect = rect;
        else if (rect.m_rightY >= m_middle) m_right.m_rect = rect;
        else {
            m_left.m_rect = rect;
            m_right.m_rect = rect;
        }
    }

}
