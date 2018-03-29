package facebook.stack;

/**
 * Created by yingtan on 3/21/18.
 */
import java.util.*;
public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return res;
        }
        Map<Integer, Integer> nextElement = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            }
            else {
                while(! stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                    nextElement.put(nums2[stack.pop()], nums2[i]);
                }
                stack.push(i);
            }
        }
        for (int i = 0 ; i < nums1.length; i ++) {
            if (nextElement.containsKey(nums1[i])) {
                int index = nextElement.get(nums1[i]);
                res[i] = index;
            }
            else {
                res[i] = -1;
            }
        }
        return res;
    }
}
