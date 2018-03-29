package amazon.array;

/**
 * Created by yingtan on 3/25/18.
 *
 * 682. Baseball Game
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 You're now a baseball game point recorder.

 Given a list of strings, each string can be one of the 4 following types:

 Integer (one round's score): Directly represents the number of points you get in this round.
 "+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
 "D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
 "C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
 Each round's operation is permanent and could have an impact on the round before and the round after.

 You need to return the sum of the points you could get in all the rounds.

 Example 1:
 Input: ["5","2","C","D","+"]
 Output: 30
 Explanation:
 Round 1: You could get 5 points. The sum is: 5.
 Round 2: You could get 2 points. The sum is: 7.
 Operation 1: The round 2's data was invalid. The sum is: 5.
 Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
 Round 4: You could get 5 + 10 = 15 points. The sum is: 30.
 Example 2:
 Input: ["5","-2","4","C","D","9","+","+"]
 Output: 27
 Explanation:
 Round 1: You could get 5 points. The sum is: 5.
 Round 2: You could get -2 points. The sum is: 3.
 Round 3: You could get 4 points. The sum is: 7.
 Operation 1: The round 3's data is invalid. The sum is: 3.
 Round 4: You could get -4 points (the round 3's data has been removed). The sum is: -1.
 Round 5: You could get 9 points. The sum is: 8.
 Round 6: You could get -4 + 9 = 5 points. The sum is 13.
 Round 7: You could get 9 + 5 = 14 points. The sum is 27.
 */
public class BaseballGame {
    public int calPoints(String[] ops) {
        if (ops == null || ops.length == 0) return 0;
        int[] points = new int[ops.length];
        int index = -1;
        for (String str : ops) {
            if (str.equals("C")) {
                index --;
            }
            else if (str.equals("D")) {
                int lastp = points[index];
                int curp = lastp * 2;
                index ++;
                points[index] = curp;
            }
            else if (str.equals("+")) {
                int last = points[index];
                if (index - 1 >= 0) {
                    int lastTwo = points[index - 1];
                    int cur = last + lastTwo;
                    index ++;
                    points[index] = cur;
                }
            }
            else {
                int p = Integer.parseInt(str);
                index ++;
                points[index] = p;
            }
        }
        int sum = 0;
        for (int i = 0 ; i <= index; i ++) {
            sum = sum + points[i];
        }
        return sum;
    }
    public static void main(String[] args) {
        BaseballGame ob = new BaseballGame();
        System.out.println((int)('z' - 'a'));
    }
}
