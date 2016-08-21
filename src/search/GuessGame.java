package search;

/**
 * Created by yingtan on 8/7/16.
 *
 *
 *
 *
 374. Guess Number Higher or Lower  QuestionEditorial Solution  My Submissions
 Total Accepted: 13179
 Total Submissions: 42057
 Difficulty: Easy
 We are playing the Guess Game. The game is as follows:

 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number is higher or lower.

 You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

 -1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
 Example:
 n = 10, I pick 6.

 Return 6.
 */
public class GuessGame {

    public int guess(int mid) {
        return 0; //
    }

    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low < high) {
            int mid = low + (high - low) / 2; // important !!!!
            int status = guess(mid);
            if (status == 0) {
                return mid;
            }
            else if (status == -1) {
                // higher
                high = mid - 1; //
            }
            else
                low = mid + 1;
        }
        return low;
    }

    /* TODO: can not pass why ???
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low < high) {
            int mid = (low + high) >> 1; // (low + high) too big, will out of time limit, use low + (high - low)/2
            int status = guess(mid);
            if (status == 0) {
                return mid;
            } else if (status == -1) {
                // higher
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return low;
    }
*/

    /*

    Procedure binary_search
   A ← sorted array
   n ← size of array
   x ← value ot be searched

   Set lowerBound = 1
   Set upperBound = n

   while x not found

      if upperBound < lowerBound
         EXIT: x does not exists.

      set midPoint = lowerBound + ( upperBound - lowerBound ) / 2

      if A[midPoint] < x
         set lowerBound = midPoint + 1

      if A[midPoint] > x
         set upperBound = midPoint - 1

      if A[midPoint] = x
         EXIT: x found at location midPoint

   end while

end procedure

    */

}
