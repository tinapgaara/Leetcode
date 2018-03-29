package facebook.array;

import java.util.Arrays;

/**
 * Created by yingtan on 5/28/17.
 *
 * 274. H-Index Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 71262
 Total Submissions: 217320
 Difficulty: Medium
 Contributor: LeetCode
 Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

 According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

 For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

 Note: If there are several possible values for h, the maximum one is taken as the h-index.

 Credits:
 Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.


 275. H-Index II Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 46092
 Total Submissions: 135645
 Difficulty: Medium
 Contributor: LeetCode
 Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 */
public class HIndex {

    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        Arrays.sort(citations);
        for(int i = 0 ; i < citations.length ; i++) {
            int h = citations.length - i;
            if (citations[i] >= h) {
                return h;
            }
        }
        return 0;
    }
}
