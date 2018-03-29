package hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingtan on 12/17/17.
 *
 * If x is odd, x = 3 * x + 1, if x is even, x = x / 2; will eventually arrives to 1.
 Test the collatz conjecture for first n positive integers.

 Collatz can fail in two ways:
 A sequence returns to a previous number in the sequence(infinit loop)
 A sequence goes infinity (flag overflow)

 Recursively stores all number already calculated before and proved coverage to 1.
 If already tested all numbers up to k and they are all good, you can stop the chain as soon as you reach a number that is less than or equal to k.
 Multiplication and division is expensive, change to bit shifting and addition

 */
public class CollatzConjecture {

    public boolean testCollatzConjecture(int n) {
        // validOnes which can coverage to 1
        Set<Long> valid = new HashSet<>();
        valid.add(1l);
        valid.add(2l);
        for (int i = 3 ; i <= n ; i = i + 1) {
            Set<Long> sequence = new HashSet<>();
            long testI = i;
            while(testI >= i) {
                if (sequence.contains(testI)) {
                    return false;
                }
                else {
                    sequence.add(testI);
                }
                // this is the valid one, already be verified
                if (valid.contains(testI)) {
                    break;
                }
                else {
                    valid.add(testI);// Important !!!
                }
                if ((testI % 2 != 0)) { // odd number

                    long newtestI = testI * 3 + 1;
                    if (newtestI < testI) {
                        // overflow
                        return false;
                    }
                    testI = newtestI;
                }
                else {
                    testI = testI / 2;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CollatzConjecture ob = new CollatzConjecture();
        ob.testCollatzConjecture(3);
    }
}
