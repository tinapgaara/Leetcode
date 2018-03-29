package uber.math;
import java.util.*;
public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> vis = new HashSet<>();
        vis.add(n);
        while(n != 1) {
            int cur = n;
            int sum = 0;
            while(cur > 0) {
                int digit = cur % 10;
                cur = cur / 10;
                sum = sum + digit * digit;
            }
            if (vis.contains(sum)) {
                // vis before
                return false;
            }
            else {
                vis.add(sum);
                n = sum;
            }
        }
        return true;
    }
}
