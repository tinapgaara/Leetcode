package google.twoPointers;

/**
 * Created by yingtan on 2/2/17.
 *
 * 360. Sort Transformed Array   Add to List QuestionEditorial Solution  My Submissions
 Total Accepted: 8535
 Total Submissions: 19780
 Difficulty: Medium
 Contributors: Admin
 Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

 The returned array must be in sorted order.

 Expected time complexity: O(n)

 Example:
 nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

 Result: [3, 9, 15, 33]

 nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

 Result: [-23, -5, 1, 7]
 */
public class SortedTransformedArr {

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null) return null;
        int[] res = new int[nums.length];
        int low = 0;
        int high = nums.length - 1;
        int i = 0;
        if (a > 0) {
            i = nums.length - 1; //  sorted nums means the tail and front can cause max, so i points to res end right now
        }

        while (low <= high) {
            int front = calculate(nums[low], a, b, c);
            int tail = calculate(nums[high], a, b, c);

            if (a > 0) {
                if (front < tail) {
                    res[i] = tail;
                    high --;
                }
                else {
                    res[i] = front;
                    low ++;
                }
                i --;
            }
            else {
                if (front < tail) {
                    res[i] = front;
                    low ++;
                }
                else {
                    res[i] = tail;
                    high --;
                }
                i ++;
            }
        }
        return res;
    }

    public int calculate(int num, int a, int b, int c) {
        return a * num * num + b * num + c;
    }
}
