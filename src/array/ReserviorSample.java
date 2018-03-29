package array;

/**
 * Created by yingtan on 1/8/18.
 */
import java.util.*;
public class ReserviorSample {

    // nums coming in streaming way : subsets are not independent from iteration to iteration
    public int[] randomSelectK(int k, Iterator<Integer> nums) {
        int[] res = new int[k];
        Random rnd = new Random();
        int i = 0;
        while (nums.hasNext()) {
            int num = nums.next();
            int randIndex = rnd.nextInt(i);
            if (randIndex < k) {
                res[randIndex] = num;
            }
            i ++;
        }
        return res;
    }

    // store all elements to memory : subset are independent
    public int[] randomSelectK(int k, List<Integer> nums) {
        int n = nums.size();
        Random rnd = new Random();
        for (int i = 0 ; i < k ; i ++) {
            // before i, all elements are set good already
            // gen random element for index i

            // each element prob: (1 - 1/n) * (1 - 1/(n-1)) * ... (1 - 1/(n-i)) * 1/(n-i) = 1/(n)
            // 1. previous round have not been choosen: (1 - 1/n) * (1 - 1/(n-1)) * ... (1 - 1/(n-i))
            // 2. this round has been choosen : 1 /(n-i)
            // multiple 1. * 2. = 1/n. Each element is choosen with equal prob
            int randIndex = i + rnd.nextInt(n - i);
            Collections.swap(nums, i, randIndex);
        }
        int[] res = new int[k];
        for (int i = 0 ; i < k; i ++) {
            res[i] = nums.get(i);
        }
        return res;
    }

}
