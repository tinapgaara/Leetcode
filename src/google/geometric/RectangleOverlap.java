package google.geometric;

import java.awt.*;

/**
 * Created by yingtan on 11/25/15.
 */
public class RectangleOverlap {

    /*
    *
    *
    * One solution is to one by one pick all points of one rectangle
    *   and see if the point lies inside the other rectangle or not.
    *  This can be done using the algorithm discussed here.

Following is a simpler approach. Two rectangles do not overlap if one of the following conditions is true.
1) One rectangle is above top edge of other rectangle.
2) One rectangle is on left side of left edge of other rectangle.
    *
    * */
    public boolean isOverlap(Point l1, Point r1,
                             Point l2, Point r2) {

        if ((r1.x < l2.x) || (r2.x < l1.x))
            return false;

        if ((r1.y > l2.y) || (r2.y > l1.y))
            return false;

        return true;
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C-A) * (D-B);
        int area2 = (G-E) * (H-F);
        if ((C < E) || (G < A)) {
            return area1 + area2;
        }
        if ((B > H) || (F > D)) {
            return area1 + area2;
        }

        int rowLen = Math.min(C,G) - Math.max(A,E);
        int colLen = Math.min(H,D) - Math.max(F,B);

        return area1 + area2 - (rowLen)*(colLen);

    }
}
