package DFSBFS;

/**
 * Created by yingtan on 9/24/15.
 */
public class Point {
    public int m_x ;
    public int m_y;
    public int m_dist;
    public Point m_prev;

    public Point(int x, int y, int dist) {
        m_x = x;
        m_y = y;
        m_dist = dist;
    }

    public Point(int x, int y) {
        m_x = x;
        m_y = y;
    }

    public Point(int x, int y, int dist, Point prev) {
        m_x = x;
        m_y = y;
        m_dist = dist;
        m_prev = prev;
    }
}
