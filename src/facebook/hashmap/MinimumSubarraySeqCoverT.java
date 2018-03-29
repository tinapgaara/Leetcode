package facebook.hashmap;

/**
 * Created by yingtan on 3/17/18.
 */
import java.util.*;
public class MinimumSubarraySeqCoverT {
    // assume we are at jth in ts, and jth word maps to ith word in s
    // j-1th word at ts maps to i'th word in s
    // dp[j] = dp[j-1] + distance(i', i)
    // s: ["a", "b", "c", "a"]
    // t: ["b", "a"]
    // Assume: ts only contains unique chs.

    // similar to : 727: minimum window subsequence
    public int minSubarraySeqCoverT(String[] s, String[] ts) {
        Map<String, Integer> indexsT = new HashMap<>();
        // what is the last index of tch appeared in s
        Integer[] lastoccur = new Integer[ts.length];
        // dp[i] : min len of S to cover T[0, i]
        Integer[] dp = new Integer[ts.length];
        for (int i = 0 ; i < ts.length; i ++) {
            indexsT.put(ts[i], i);
            lastoccur[i] = -1;
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0 ; i < s.length; i ++) {
            String str = s[i];
            // find out its index in ts, index_t == j
            if (indexsT.containsKey(str)) {
                int index_t = indexsT.get(str);
                // find out its previous string in ts, because this string must be after the previous string
                if (index_t == 0) {
                    // no previous string, this is the beginning word, so dp[index_t] should be zero.
                    dp[index_t] = 0;
                }
                else {
                    // depends on previous string
                    if (dp[index_t - 1] != Integer.MAX_VALUE) {
                        // already calculated the previous dp
                        // T[index_t-1] -> S[i']
                        // dp[index_t] = dp[index_t - 1] + min_distance(i - i')
                        // how to minimize? i is settled,can maximize i', so find last occur index of T[index_t-1]=lastoccur[index_t-1]
                        int ii = lastoccur[index_t - 1];
                        int minDist = i - ii;
                        if (dp[index_t -1] + minDist > dp[index_t]) {
                            dp[index_t] = dp[index_t -1] + minDist;
                        }
                    }
                    else {
                        // because T[index_t] must appear after T[index_t - 1], if dp[index_t - 1] is MAX_VALUE, this means,
                        // T[index_t - 1] never appear before T[index_t] in S, so we just ignore the current string.
                    }
                }
                lastoccur[index_t] = i;
            }
        }
        return dp[ts.length - 1];
    }
}
