package google.dp;

/**
 * Created by yingtan on 5/6/17.
 *
 *
 *
 * 135. Candy
 *
 * There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:

 Each child must have at least one candy.
 Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?
 */
public class Candy {

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;

        int[] res = new int[ratings.length];
        res[0] = 1;
        for (int i = 1 ; i < ratings.length; i ++) {
            if (ratings[i] > ratings[i - 1]) {
                res[i] = res[i-1] + 1;
            }
            else {
                res[i] = 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i --) {
            if (ratings[i] > ratings[i + 1]) {
                res[i] = Math.max(res[i], res[i + 1] + 1);
            }
        }

        int sum = 0;
        for (int i = 0 ; i < res.length; i ++) {
            sum = sum + res[i];
        }
        return sum;
    }
}
