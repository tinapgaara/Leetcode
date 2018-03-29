package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 12/17/17.
 */
public class SmallestSubarraySequentiallyCoversAllValues {

    // given paragraphs: "apple", "banana", "cat", "apple"
    // given keywords: "banana", "apple"
    // find out the min subarray in paragraphs which sequentially cover all keywords : subarrya[1-3]
    /*
            j'  j
    * p: [_ _ _ _ _]
    *
    * k: [_   _  _]
         i-1  i

      p[j] == k[i]

      1. find out i according to word[j] : need a map: <Word, IndexInK>
      2. i-1
      3. find out j' according to i-1 : j' = lastoccurent[i-1]
      4. calculate distance between j' and j : (j - j' + 1)
      5. dp[i] = dp[i-1] + distance(j, j')
      6. finally, return dp[klen-1]
    * */
    public int minSubarraySeqCoverAllValues(String[] paragraphs,  String[] keywords) {
        // Currently, we are at word jth in paragraph, and word j is same as keywords i
        // dpLenEndsWith[j/keyword_i] = dpLenEndsWith[keyword_i-1] + (j - lastoccurenceInParapragh[keyword_i-1]))
        Map<String, Integer> keywordToIndex = new HashMap<>();
        Integer[] lastOccurenceIndex = new Integer[keywords.length];
        Integer[] dpLenEndsAt = new Integer[keywords.length];
        int[] res = new int[2];
        // initialize
        for (int i = 0 ; i < keywords.length; i ++) {
            keywordToIndex.put(keywords[i], i);
            lastOccurenceIndex[i] = -1;
            dpLenEndsAt[i] = Integer.MAX_VALUE;
        }
        // calculate
        for (int j = 0 ; j < paragraphs.length; j ++) {
            String word = paragraphs[j];
            if (keywordToIndex.containsKey(word)) {
                int i = keywordToIndex.get(word);
                // dp[i] = 1;
                if (i == 0) {
                    dpLenEndsAt[i] = 1;
                }
                // dp[i] = dp[i-1] + distance
                // make sure we already calculated j', and j' must be exist
                // make sure when we see keyword(i), we already saw keyword(i-1)
                else if (dpLenEndsAt[i-1] != Integer.MAX_VALUE){
                    int prevIndex = lastOccurenceIndex[i -1];
                    int distance = j - prevIndex;
                    if (dpLenEndsAt[i-1] + distance < dpLenEndsAt[i]) {
                        dpLenEndsAt[i] = dpLenEndsAt[i-1] + distance;
                        // this is last word in keywords
                        if (i == keywords.length -1) {
                            int minSubarrayLen = dpLenEndsAt[i];
                            // start = end - minSubarray.length
                            res[1] = j;
                            res[0] = j - minSubarrayLen + 1;
                        }
                    }
                }
                lastOccurenceIndex[i] = j;
            }
        }
        System.out.println("Min subarray starts at:" + res[0] + ", ends at: " + res[1]);
        return dpLenEndsAt[keywords.length - 1];
    }

    public static void main(String[] args) {
        SmallestSubarraySequentiallyCoversAllValues ob = new SmallestSubarraySequentiallyCoversAllValues();
        String[] ps = {"apple", "banana", "cat", "apple", "banana", "apple"};
        String[] ks = {"banana", "apple"};
        System.out.println(ob.minSubarraySeqCoverAllValues(ps, ks));
    }
}
