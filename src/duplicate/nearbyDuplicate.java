package duplicate;

import java.util.HashMap;

/**
 * Created by yingtan on 8/25/15.
 */
public class nearbyDuplicate {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0 ; i < nums.length; i ++) {
            int val = nums[i];
            if (map.containsKey(val)) {
                int indice = map.get(val);
                if (i - indice <= k) {
                    return true;
                }
                else {
                    map.put(val,i);
                }
            }
            else {
                map.put(val, i);
            }
        }
        return false;
    }
}
