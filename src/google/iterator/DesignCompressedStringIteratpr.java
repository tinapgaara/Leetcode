package google.iterator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by yingtan on 11/5/17.
 *
 * 604. Design Compressed String Iterator
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

 The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

 next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
 hasNext() - Judge whether there is any letter needs to be uncompressed.

 Note:
 Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

 Example:

 StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

 iterator.next(); // return 'L'
 iterator.next(); // return 'e'
 iterator.next(); // return 'e'
 iterator.next(); // return 't'
 iterator.next(); // return 'C'
 iterator.next(); // return 'o'
 iterator.next(); // return 'd'
 iterator.hasNext(); // return true
 iterator.next(); // return 'e'
 iterator.hasNext(); // return false
 iterator.next(); // return ' '
 */
public class DesignCompressedStringIteratpr {

    // important !!! use int[] to change pointer count easily
    Queue<int[]> queue = new LinkedList<int[]>();
    public DesignCompressedStringIteratpr(String compressedString) {
        for (int i = 0 ; i < compressedString.length(); i ++) {
            char ch = compressedString.charAt(i);
            int count = 0;
            while(i + 1 < compressedString.length() && Character.isDigit(compressedString.charAt(i + 1))) {
                count = count * 10 + (compressedString.charAt(i + 1) - '0');
                i ++;
            }
            queue.offer(new int[]{ch - 'a', count});
        }
    }
    public char next() {
        if (hasNext()) {
            int[] top = queue.peek();
            top[1] --;
            if(top[1] == 0) {
                queue.poll();
            }
            return (char)(top[0] + 'a');
        }
        return ' ';
    }

    public boolean hasNext() {
        return ! queue.isEmpty();
    }

    public static void main(String[] args) {
        DesignCompressedStringIteratpr ob = new DesignCompressedStringIteratpr("x6");
        System.out.println(ob.next());
        System.out.println(ob.next());
        System.out.println(ob.next());
        System.out.println(ob.next());
        System.out.println(ob.next());
        System.out.println(ob.next());
        System.out.println(ob.hasNext());
        System.out.println(ob.next());
        System.out.println(ob.next());
    }
}
