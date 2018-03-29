package apple.math;
import java.util.*;
import java.math.BigDecimal;
public class MaxPointsOnALine {
    public class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) return 0;
        int max = 0;
        PointComparator comp = new PointComparator();
        Arrays.sort(points, comp);
        for (int i = 0 ; i < points.length; i ++) {
            Point a = points[i];
            Map<String, Integer> sameLineCount = new HashMap<>();
            int dup = 0;
            int vertical = 0;
            int maxLine = 0;
            for (int j = i + 1; j < points.length; j ++) {
                Point b = points[j];
                // cal duplicate ones
                if (a.x == b.x  && a.y == b.y) {
                    dup ++;
                }
                // cal vertical ones
                else if (a.x - b.x == 0) {
                    vertical ++;
                }
                // others, must be a different line
                else {
                    /*
                    *  Name     Width in Bits   Range
    double  64              1 .7e–308 to 1.7e+308
    float   32              3 .4e–038 to 3.4e+038
                    *
                    * */
                    double deltaX = (a.x - b.x) * 1.0;
                    BigDecimal divisor = new BigDecimal((a.y - b.y) * 1.0);
                    BigDecimal divid = new BigDecimal((a.x - b.x) * 1.0);
                    String str = divisor.divide(divid, 100, BigDecimal.ROUND_HALF_UP).toString();
                    //double delta = divisor.divide(divid, 1000, BigDecimal.ROUND_HALF_UP).doubleValue();
                    if (! sameLineCount.containsKey(str)) {
                        sameLineCount.put(str, 1);
                    }
                    else {
                        sameLineCount.put(str, sameLineCount.get(str) + 1);
                    }
                    // Very important !!!!!
                    maxLine = Math.max(maxLine, sameLineCount.get(str));
                }
            }
            //System.out.println(maxLine + " " + dup + " " + vertical);
            max = Math.max(max, dup + maxLine + 1); // Important !!!
            max = Math.max(max, dup + vertical + 1);// Important !!!
        }
        return max;
    }
    public class PointComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            if (p1.x == p2.x) {
                return p1.y - p2.y;
            }
            else {
                return p1.x - p2.x;
            }
        }
    }
    //[[0,0],[94911151,94911150],[94911152,94911151]]
    public static void main(String[] args) {
        BigDecimal dec = new BigDecimal(94911150 * 1.0);
        BigDecimal dec2 = new BigDecimal(94911151 * 1.0);
        System.out.println(dec.divide(dec2, 1000, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(94911151 * 1.0 / 94911152);
    }
}
