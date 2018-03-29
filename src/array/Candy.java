package array;

/**
 * Created by yingtan on 1/25/18.
 *
 * 135. Candy
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:

 Each child must have at least one candy.
 Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?

 Two pass
 */
import java.util.*;
public class Candy {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int[] candy = new int[ratings.length];
        Arrays.fill(candy, 1);
        // two pass
        for (int i = 1 ; i < ratings.length; i ++) {
            if (ratings[i] > ratings[i-1]) {
                candy[i] = candy[i-1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i --) {
            if (ratings[i] > ratings[i+1]) {
                candy[i] = Math.max(candy[i], candy[i+1] + 1);
            }
        }
        int count = 0;
        for (int c : candy) {
            count = count + c;
        }
        return count;
    }
}
