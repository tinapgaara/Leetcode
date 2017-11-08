package intervalTree;

import java.util.Comparator;

/**
 * Created by yingt on 2017/10/26.
 */

public class Rect {
    int m_leftX, m_rightX, m_leftY, m_rightY;

    public Rect(int leftX, int rightX, int leftY, int rightY) {
        m_leftX = leftX;
        m_rightX = rightX;
        m_leftY = leftY;
        m_rightY = rightY;
    }

    public boolean intersectY(Rect other) {
        if ( (m_rightY > other.m_leftY) || (other.m_rightY > m_leftY) )
            return false;
        else
            return true;
    }
}
