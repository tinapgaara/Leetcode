package recursion;

/**
 * Created by yingtan on 1/11/18.
 */
import java.util.*;
public class Hanoi {

    public List<List<Integer>> sequence(int numRings) {
        List<Deque<Integer>> pegs = new ArrayList<>(); // peg 0, 1 2
        for (int i = 0 ; i < 3; i ++) {
            pegs.add(new ArrayDeque<>());
        }
        // initially, all rings is at peg 0
        for (int i = numRings ; i >= 1; i --) {
            pegs.get(0).addFirst(i); // accumalte from down to top
        }
        List<List<Integer>> res = new ArrayList<>();
        computeTowerHanoiSteps(numRings, pegs, 0, 2, 1, res);
        return res;
    }
    public void computeTowerHanoiSteps(int numRings, List<Deque<Integer>> pegs, int fromPeg, int toPeg, int leftPeg, List<List<Integer>> res) {
        if (numRings > 0) {
            // move n-1 to the leftpeg
            computeTowerHanoiSteps(numRings - 1, pegs, fromPeg, leftPeg, toPeg, res);
            // move nth to toPeg
            int top = pegs.get(fromPeg).removeFirst();
            pegs.get(toPeg).addFirst(top);
            // record this moving
            List<Integer> move = new ArrayList<>();
            move.add(fromPeg);
            move.add(toPeg);
            res.add(move);
            // move n-1 from leftpeg to topeg
            computeTowerHanoiSteps(numRings -1, pegs, leftPeg, toPeg, fromPeg, res);
        }
    }

    public static void main(String[] args) {
        Hanoi ob = new Hanoi();
        System.out.println(ob.sequence(3));
    }
}
