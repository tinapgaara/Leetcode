package unionfind;

/**
 * Created by yingtan on 2/22/18.
 *
 * 547. Friend Circles
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

 Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

 Example 1:
 Input:
 [[1,1,0],
 [1,1,0],
 [0,0,1]]
 Output: 2
 Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 The 2nd student himself is in a friend circle. So return 2.
 Example 2:
 Input:
 [[1,1,0],
 [1,1,1],
 [0,1,1]]
 Output: 1
 Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 Note:
 */
import java.util.*;
public class FriendCircles {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int[] parents = new int[M.length];
        int len = M.length;
        for (int i = 0 ; i < len; i ++) {
            int root = M[i][i];
            parents[i] = i;
            int rootParent = findParent(parents, root);
            for (int j = i + 1; j < len; j ++) {
                int parent = findParent(parents, M[i][j]);
                if (rootParent != parent) {
                    // union
                    parents[parent] = rootParent;
                }
            }
        }
        // group, return count
        Set<Integer> group = new HashSet<>();
        for (int parent : parents) {
            group.add(parent);
        }
        return group.size();

    }
    public int findParent(int[] parents, int num) {
        if(parents[num] != num) {
            parents[num] = findParent(parents, parents[num]);
        }
        return parents[num];
    }
}
