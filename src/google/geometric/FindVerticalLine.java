package google.geometric;

import java.util.Collections;
import java.util.List;

/**
 * Created by yingtan on 11/1/15.
 */
public class FindVerticalLine {

    public boolean findVerticalLine(List<Integer> points) {

        if (points.size() == 0) return false;

        double median = 0;
        Collections.sort(points);
        if (points.size() % 2 == 0) {
            int index = points.size() / 2;
            int index_2 = index - 1;
            median = points.get(index) + points.get(index_2) / (2.0);
        }
        else {
            int index = points.size() / 2;
            median = points.get(index);
        }

        int low = 0;
        int high = points.size() - 1;
        while (low < high) {
            if ((median - points.get(low)) == (points.get(high) - median)) {
                low ++;
                high --;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
