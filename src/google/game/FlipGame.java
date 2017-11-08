package google.game;

/**
 * Created by yingtan on 11/10/15.
 */
/*
* You are playing the following Flip Game with your friend: Given a string that
* contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
* The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++"
to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
* */
public class FlipGame {

    public boolean canWin(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        char[] arr = s.toCharArray();

        return recurCanWin(arr);
    }
    // for i, i+1, flip, and then check canWin(newarr), if !canWin(newarr), return true
    // if loop all is and i+1 and flip, all canWin(newarr) returns true, then return false
    public boolean recurCanWin(char[] arr) {
        for (int i = 0 ; i < arr.length -1; i ++) {
            if ((arr[i] == '+') && (arr[i+1] == '+')) {
                arr[i] = '-';
                arr[i+1] = '-';

                boolean canWin = recurCanWin(arr);

                // important ! before return, must flip back
                arr[i] = '+';
                arr[i+1] = '+';

                if (! canWin) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FlipGame ob = new FlipGame();
        System.out.println(ob.canWin("++++"));
    }
}
