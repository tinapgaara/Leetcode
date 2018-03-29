package linkedin.practice;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/22/17.
 */
public class MaxPointOnALine {

    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) return 0;
        int vertical = 0;
        int dup = 1;
        int max = 0;
        for (int i = 0 ; i < points.length; i ++) {
            Point p1 = points[i];
            HashMap<Double, Integer> result = new HashMap<Double, Integer>();
            for(int j = i + 1 ; j < points.length; j ++) {
                Point p2 = points[j];
                if (p1.x == p2.x) {
                    if (p1.y == p2.y) {
                        dup ++;
                    }
                    else {
                        vertical ++;
                    }
                }
                double diffY = 0.0;
                if (p1.y != p2.y) {
                    diffY = (p1.y - p2.y) * 1.0;
                }
                double slope = diffY / (p1.x - p2.x);
                if (result.containsKey(slope)) {
                    result.put(slope, result.get(slope) + 1);
                }
                else {
                    result.put(slope, 1);
                }
                for (Map.Entry<Double, Integer> entry : result.entrySet()) {
                    int samePointsALine = entry.getValue();
                    max = Math.max(max, samePointsALine + dup);
                }
                max = Math.max(max, vertical + dup);
            }
        }
        return max;
    }
}
