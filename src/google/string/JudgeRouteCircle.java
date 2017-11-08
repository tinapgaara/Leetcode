package google.string;

/**
 * Created by yingtan on 9/16/17.
 *
 * 657. Judge Route Circle
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

 The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

 Example 1:
 Input: "UD"
 Output: true
 Example 2:
 Input: "LL"
 Output: false
 */
public class JudgeRouteCircle {

    public boolean judgeCircle(String moves) {
        if (moves == null || moves.length() == 0) return true;
        int verticalCount = 0;
        int horizontalCount = 0;
        for (int i = 0; i < moves.length(); i ++) {
            char ch = moves.charAt(i);
            if (ch == 'U') {
                verticalCount ++;
            }
            else if (ch == 'D') {
                verticalCount --;
            }
            else if (ch == 'L') {
                horizontalCount ++;
            }
            else if (ch == 'R') {
                horizontalCount --;
            }
        }
        if (horizontalCount == 0 && verticalCount == 0) {
            return true;
        }
        else return false;
    }
}
