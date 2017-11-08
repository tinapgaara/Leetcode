package google.mianjing;

/**
 * Created by yingtan on 11/6/17.
 */

import java.util.Arrays;
import java.util.List;

/**
 * 二维坐标系给一堆点，找出这些点能形成的最小矩形的面积
 */
public class MinRect {

    public class Point implements Comparable {
        public float m_x, m_y;

        public int compareTo(Object obj) {
            Point other = (Point) obj;
            if (this.m_x < other.m_x) return -1;
            else if (this.m_x == other.m_x) return (int) (other.m_y - this.m_y);
            else return +1;
        }
    }

    public class Rect {
        public Point m_leftTop, m_rightBottom;
        public Rect(Point leftTop, Point rightBottom) {
            m_leftTop = leftTop;
            m_rightBottom = rightBottom;
        }
        public float getArea() {
            return (m_rightBottom.m_x - m_leftTop.m_x) * (m_leftTop.m_y - m_rightBottom.m_y);
        }
    }

    public Rect calcMinRect(List<Point> pointList) {
        // 将矩阵按照 x 从小到大，y从大到小 的顺序排序
        Point[] pointArr = new Point[pointList.size()];
        pointList.toArray(pointArr);
        Arrays.sort(pointArr);

        Rect minRect = null;
        for (int i = 0; i < pointArr.length - 1; i++) {
            Point ptleftTop = pointArr[i];
            for (int j = i + 1; j < pointArr.length; j++) {
                Point ptRightBottom = pointArr[j];
                if ((ptRightBottom.m_x <= ptleftTop.m_x) || (ptRightBottom.m_y >= ptleftTop.m_y))
                    continue;
                else {
                    Rect curRect = new Rect(ptleftTop, ptRightBottom);
                    if ( (minRect == null) || (minRect.getArea() > curRect.getArea()) )
                        minRect = curRect;
                }
            }
        }
        return minRect;
    }
}
