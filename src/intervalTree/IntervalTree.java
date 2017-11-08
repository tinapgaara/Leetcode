package intervalTree;

/**
 * Created by yingt on 2017/10/26.
 */

public class IntervalTree {

    IntervalTreeNode m_root;

    public IntervalTree(int start, int end) {
        m_root = new IntervalTreeNode(start, end, null);
    }

    public boolean add(Rect rect) {
        return m_root.add(rect);
    }

    public void remove(Rect rect) {
        m_root.remove(rect);
    }
}
