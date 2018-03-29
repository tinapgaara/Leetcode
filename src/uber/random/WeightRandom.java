package uber.random;

import javafx.util.Pair;

import java.util.List;
import java.util.Random;

/**
 * Created by yingtan on 11/7/17.
 *
 * Write a function that returns values randomly, according to their weight.
 Let me give you an example.
 Suppose we have 3 elements with their weights: A (1), B (1) and C (2).
 The function should return A with probability 25%, B with 25% and C with 50% based on the weights.

 ssuming a list of words appear with a given probability: example
 (hello, 0.2), (world, 0.3), (ok, 0.5).
 now generate a word according to their probability(use binary search
 */
public class WeightRandom {
    // Solution 1:
    // [A, B ,C, C] : sample

    // Solution 2: sum up weights
    // [1, 2 ,4] , and then select an number ith randomly from [0 - 4]
    // then loop the array, once its sum >= number, return the current index
    /*
    * The sampling is like randomly select a point and see which area it falls into.
    * Going into this idea, we can have the following algorithm:
      W is the sum of all the weights (length of the horizontal line)
      Get a random number R from [0, W] (randomly select a point)
      Go over each element in order and keep the sum of weights of visited elements.
      Once the sum is larger than R, return the current element.
      This is finding which area includes the point.
    * */
    // o(n)
    private int randomSelect(int[] weights) {
        int totalsum = 0;
        for (int i = 1 ; i < weights.length; i ++) {
            totalsum = totalsum + weights[i];
        }
        Random random = new Random();
        int randomNum = random.nextInt(totalsum);

        int sum = 0;
        for (int i = 0 ; i < weights.length; i ++) {
            sum = sum + weights[i];
            if (sum >= randomNum) {
                return i;
            }
        }
        return -1;
    }

    private int randomGenWord(List<Pair<String, Double>> words) {
        double[] sum = new double[words.size() + 1];
        for (int i = 1 ; i < words.size(); i ++) {
            sum[i] = sum[i-1] + words.get(i).getValue();
        }
        Random random = new Random();
        // gen a double in range [0-1]
        double randomNum = random.nextDouble();

        // binary search
        // find the first element in sum[] whose value >= randomNum
        int low = 0;
        int high = sum.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (sum[mid] < randomNum) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        // two possible results
        if (sum[low] >= randomNum) {
            return low + 1;
        }
        if (sum[high] >= randomNum) {
            return high + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        WeightRandom ob = new WeightRandom();
        //ob.randomGenWord(new ArrayList<>(){new PA})
    }

}
