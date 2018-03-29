package linkedin.binarysearch;

/**
 * Created by yingtan on 11/29/17.
 */
public class FindFirstLargerCh {

    public char findLargerCh(char[] list, char target) {
        int low = 0;
        int high = list.length - 1;
        while(low + 1 < high) {
            int med = low + (high - low) / 2;
            char medch = list[med];
            if (medch < target) {
                low = med + 1;
            }
            else {
                high = med;
            }
        }
        if (list[low] > target) return list[low];
        if (list[high] > target) return list[high];
        return '.';
    }


}
