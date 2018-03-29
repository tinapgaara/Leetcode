package facebook.array;

/**
 * Created by yingtan on 2/19/18.
 * 275. H-Index II
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 */
public class HIndexII {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int low = 0;
        int high = citations.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            int h = citations.length - mid;
            if (citations[mid] >= h) {
                // citations[mid] is large, and h is small
                high = mid;
            }
            else {
                // h is large, search righter
                low = mid +  1;
            }
        }
        if (citations[low] >= (citations.length - low)) return citations.length - low;
        else if (citations[high] >= (citations.length - high)) return citations.length - high;
        return 0;
    }

}
