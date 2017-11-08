package google.DFS;

import java.util.*;

/**
 * Created by yingtan on 11/5/17.
 *
 * 638. Shopping Offers
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 In LeetCode Store, there are some kinds of items to sell. Each item has a price.

 However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

 You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.

 Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

 You could use any of special offers as many times as you want.

 Example 1:
 Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
 Output: 14
 Explanation:
 There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
 In special offer 1, you can pay $5 for 3A and 0B
 In special offer 2, you can pay $10 for 1A and 2B.
 You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
 Example 2:
 Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 Output: 11
 Explanation:
 The price of A is $2, and $3 for B, $4 for C.
 You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C.
 You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C.
 You cannot add more items, though only $9 for 2A ,2B and 1C.
 */
public class ShoppingOffers {

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (price == null || special == null || needs == null) {
            return 0;
        }
        Map<List<Integer>, Integer> dp = new HashMap<>();
        List<Integer> copyNeed = new ArrayList<>();
        copyNeed.addAll(new ArrayList<>(needs));
        //dp.put(copyNeed, 0);
        return dfs(dp, price, special, needs);
    }

    private int dfs(Map<List<Integer>, Integer> dp, List<Integer> price, List<List<Integer>> specials, List<Integer> needs) {
        if (dp.containsKey(needs)) {
            return dp.get(needs);
        }
        int res = Integer.MAX_VALUE;
        // use special for this needs combination
        for (List<Integer> special : specials) {
            List<Integer> needCopy = new ArrayList<>(needs);
            boolean isValid = true;
            int specialPrice = special.get(special.size() - 1);
            for (int i = 0 ; i < needs.size(); i ++) {
                int productCount = special.get(i);
                if (productCount > needs.get(i)) {
                    isValid = false;
                    break;
                }
                needCopy.set(i, needs.get(i) - productCount);
            }
            if (isValid) {
                res = Math.min(res, specialPrice + dfs(dp, price, specials, needCopy));
            }
        }
        // don't use special price for this combination
        int noSpecial = 0;
        for (int i = 0 ; i < needs.size(); i ++) {
            noSpecial = noSpecial + needs.get(i) * price.get(i);
        }
        res = Math.min(res, noSpecial);
        dp.put(needs, res);
        return res;
    }

    public static void main(String[] args) {
        ShoppingOffers ob = new ShoppingOffers();
        List<Integer> prices = new ArrayList<>();
        prices.add(2);
        prices.add(5);

        List<List<Integer>> specials = new ArrayList<>(Arrays.asList(Arrays.asList(3,0,5),Arrays.asList(1,2,10)));
        List<Integer> needs = new ArrayList<>(Arrays.asList(3,2));

        System.out.println(ob.shoppingOffers(prices, specials, needs));

    }
}
