package DFSBFS;

/**
 * Created by yingtan on 9/24/15.
 */
public class Point {
    int m_x ;
    int m_y;
    int m_dist;
    Point m_prev;

    public Point(int x, int y, int dist) {
        m_x = x;
        m_y = y;
        m_dist = dist;
    }

    public Point(int x, int y, int dist, Point prev) {
        m_x = x;
        m_y = y;
        m_dist = dist;
        m_prev = prev;
    }
}
