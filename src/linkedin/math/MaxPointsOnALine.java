package linkedin.math;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/28/17.
 */
public class MaxPointsOnALine {

    public int maxPoints(Point[] points) {
        if(points == null || points.length == 0) return 0;
        HashMap<Double, Integer> result = new HashMap<Double, Integer>();
        int max=0;
        for(int i=0; i<points.length; i++){
            int duplicate = 1;
            int vertical = 0;
            for(int j=i+1; j<points.length; j++){
                //handle duplicates and vertical
                if(points[i].x == points[j].x){
                    if(points[i].y == points[j].y){
                        duplicate++;
                    }else{
                        vertical++;
                    }
                }else{
                    double slope = points[j].y == points[i].y ? 0.0
                            : (1.0 * (points[j].y - points[i].y))
                            / (points[j].x - points[i].x);

                    if(result.get(slope) != null){
                        result.put(slope, result.get(slope) + 1);
                    }else{
                        result.put(slope, 1);
                    }
                }
            }
            for(Integer count: result.values()){
                if(count+duplicate > max){
                    max = count+duplicate;
                }
            }
            max = Math.max(vertical + duplicate, max);
            result.clear();
        }
        return max;
    }

    public int maxPoints2(Point[] points) {
        // map from slope -> number of points
        Map<Double, Integer> counts = new HashMap<Double, Integer>();
        int max = 0 ;
        for(int i = 0 ; i < points.length; i ++) {
            int duplicate = 1;
            int vertical = 0;
            Point p1 = points[i];
            for (int j = i + 1; j < points.length; j ++) {
                Point p2 = points[j];
                if (p1.x == p2.x) {
                    if (p1.y == p2.y) {
                        duplicate ++;
                    }
                    else {
                        vertical ++;
                    }
                }
                else {
                    double deltay = 1.0 * (p1.y - p2.y);
                    double slop = deltay / (p1.x - p2.x);
                    if (counts.containsKey(slop)) {
                        counts.put(slop, counts.get(slop) + 1);
                    }
                    else {
                        counts.put(slop, 1);
                    }
                }
            }
            for (Integer count : counts.values()) {
                if (count + duplicate > max) {
                    max = count + duplicate;
                }
            }
            max = Math.max(max, duplicate + vertical);
        }
        return max;
    }
}
