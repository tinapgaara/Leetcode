package facebook;

/**
 * Created by yingtan on 3/22/18.
 */
import java.util.*;
public class PhoneInterviewI {
/*
    Welcome to Facebook!

    This is just a simple shared plaintext pad, with no execution capabilities.

    When you know what language you'd like to use for your interview,
    simply choose it from the dropdown in the top bar.

    Enjoy your interview!

    McDonald's sells chicken nuggets in quantities of 6, 9, or 20. Write a function that takes an integer, n, and returns a boolean that indicates whether it is possible to purchase exactly n nuggets.
//dp[n] = dp[n-6] || dp[n-9] || dp[n-20]: boolean array
            // dp[6] dp[9] dp[20]  ->  for(i=0; i<=n; i++)
            41
            */
    public boolean isPurchase(int n) {
        boolean[] dp = new boolean[n + 1]; // false
        dp[0] = true; // base case
        int[] options = {6, 9, 20};
        for (int i = 1; i <= n; i ++) { // o(n) * 3 // o(n)
            for (int option : options) {
                if (i >= option) {
                    dp[i] = dp[i] | dp[i - option];  // or logic
                }
            }
        }
        return dp[n];
    }
// n < 6 n : 4  dp[1 - 5] false dp[4] false

/*
    print all the permutation of "apple[1,2].facebook[3,4].google[5,6].com"
    apple1.facebook3.google5.com
    apple1.facebook4.google5.com
    apple2.facebook3.google5.com
    apple2.facebook4.google5.com
    apple1.facebook4.google5.com

    apple -> [1,2] : 0
    facebook -> [3, 4] :
    google-> [5, 6]
            [[1,2], [3, 4], [5, 6]]
            [1,2]  -> [3,4] -> [5, 6]
            [1,2] ->  [5, 6] -> [3, 4]
*/

    public List<String> permutate(String str) {
        if (str == null || str.length() == 0) return new ArrayList<>();
        // preprocess the str, and get [[1,2], [3,4], [5,6]]
        // [1,2]-> 0 -> apple
        // [3,4]->1 -> facebook
        // [5,6]-> 2 -> google
        Map<Integer, String> map = new HashMap<>();
        List<List<Integer>> preprocess = preprocess(str, map);
        boolean[] vis = new boolean[preprocess.size()];
        List<String> res = new ArrayList<>();
        List<Integer> path = new ArrayList<Integer>();
        dfs(preprocess, vis, "", map, path, res);
        return res;
    }
    public List<List<Integer>> preprocess(String input, Map<Integer, String> map) {
        String[] parts = input.split("\\.");
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0 ; i < parts.length - 1; i ++) {
            String part = parts[i];
            int leftBracket = part.indexOf('[');
            int rightBracket = part.indexOf(']');
            String company = part.substring(0, leftBracket);
            map.put(i, company); // 0[1, 2] -> apple  ; 1[3, 4]->facebook
            String numbers = part.substring(leftBracket + 1, rightBracket);
            String[] num = numbers.split(",");
            List<Integer> list = new ArrayList<Integer>();
            if (num != null) {
                for (String n : num) {
                    // 1 / 2
                    list.add(Integer.parseInt(n));
                }
                // [1, 2]
            }
            res.add(list);
        }
        return res;
    }

    public void dfs(List<List<Integer>> preprocess, boolean[] vis, String str, Map<Integer, String> map, List<Integer> path, List<String> res) {
        if (path.size() == preprocess.size()) {
            res.add(str + "com");
            return;
        }
        for (int i = 0; i < preprocess.size(); i ++) {
            if (! vis[i]) {
                String company = map.get(i);
                vis[i] = true;
                for (Integer num : preprocess.get(i)) {
                    String newstr = str + company + num + ".";
                    path.add(num);
                    dfs(preprocess, vis, newstr, map, path, res);
                    path.remove(path.size() - 1);
                }
                vis[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        PhoneInterviewI ob = new PhoneInterviewI();
        System.out.println(ob.permutate("apple[1,2].facebook[3,4].google[5,6].com"));
    }


}
