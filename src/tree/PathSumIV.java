package tree;

/**
 * Created by yingtan on 3/7/18.
 *
 * 666. Path Sum IV
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

 For each integer in this list:
 The hundreds digit represents the depth D of this node, 1 <= D <= 4.
 The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
 The units digit represents the value V of this node, 0 <= V <= 9.
 Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

 Example 1:
 Input: [113, 215, 221]
 Output: 12
 Explanation:
 The tree that the list represents is:
 3
 / \
 5   1

 The path sum is (3 + 5) + (3 + 1) = 12.
 Example 2:
 Input: [113, 221]
 Output: 4
 Explanation:
 The tree that the list represents is:
 3
 \
 1

 The path sum is (3 + 1) = 4.

 */
import java.util.*;
public class PathSumIV {
    public int pathSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> locToVal = new HashMap<>();
        for (int num : nums) {
            locToVal.put(num /10, num% 10);
        }
        int[] sum = new int[1];
        traverse(nums[0] / 10, locToVal, 0, sum);
        return sum[0];
    }
    public void traverse(int loc, Map<Integer, Integer> locToVal, int pathsum, int[] sum) {
        if (! locToVal.containsKey(loc)) {
            return;
        }
        int val = locToVal.get(loc);
        int level = loc / 10;
        int pos = loc %  10;
        int leftLoc = (level + 1) * 10 + 2 * pos - 1;
        int rightLoc = (level + 1) * 10 + 2 * pos;
        if (! locToVal.containsKey(leftLoc) && !locToVal.containsKey(rightLoc)) {
            // left child and right child are null
            // this is the end of path, can record sum
            sum[0] = sum[0] + pathsum + val;
        }
        else if (! locToVal.containsKey(leftLoc)) {
            traverse(rightLoc, locToVal, pathsum + val, sum);
        }
        else if (! locToVal.containsKey(rightLoc)) {
            traverse(leftLoc, locToVal, pathsum + val, sum);
        }
        else {
            traverse(leftLoc, locToVal, pathsum + val, sum);
            traverse(rightLoc, locToVal, pathsum + val, sum);
        }
    }
}
