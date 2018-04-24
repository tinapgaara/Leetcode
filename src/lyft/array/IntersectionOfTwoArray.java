package lyft.array;
import java.util.*;
public class IntersectionOfTwoArray {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null ||nums2 == null ) return nums1;
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        Set<Integer> res = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                res.add(num);
            }
        }
        int[] r = new int[res.size()];
        int i = 0;
        for (int num : res) {
            r[i] = num;
            i ++;
        }
        return r;
    }
}
