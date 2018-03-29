package amazon.greedy;

/**
 * Created by yingtan on 3/22/18.
 *
 * 763. Partition Labels
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

 Example 1:
 Input: S = "ababcbacadefegdehijhklij"
 Output: [9,7,8]
 Explanation:
 The partition is "ababcbaca", "defegde", "hijhklij".
 This is a partition so that each letter appears in at most one part.
 A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 Note:

 S will have length in range [1, 500].
 S will consist of lowercase letters ('a' to 'z') only.
 */
import java.util.*;
public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) return res;
        Map<Character, Integer> latestIndex = new HashMap<>();
        for (int i = 0 ; i < S.length(); i ++) {
            latestIndex.put(S.charAt(i), i);
        }
        int start = 0;
        int end = 0;
        int leastIndexOfCurPartition = latestIndex.get(S.charAt(start));
        while(end < S.length() && start <= end) {
            char ch = S.charAt(end);
            if (latestIndex.get(ch) > leastIndexOfCurPartition) {
                leastIndexOfCurPartition = latestIndex.get(ch);
            }
            if (end == leastIndexOfCurPartition) {
                res.add(end - start + 1);
                start = end + 1;
            }
            end ++;
        }
        if (end - start > 0) {
            res.add(end - start);
        }
        return res;
    }
    public static void main(String[] args) {
        PartitionLabels ob = new PartitionLabels();
        System.out.println(ob.partitionLabels("vhaagbqkaq"));
    }
}
