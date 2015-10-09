package prime;

import java.util.*;

/**
 * Created by yingtan on 10/7/15.
 */
public class nthUglyNumber {

    public class UglyNumber {
        long lastElement;
        long val;

        public UglyNumber(long last, long value) {
            lastElement = last;
            val = value;
        }
    }
    // Solution 2: need to generate a sequence of ugly numbers
    // Using Heap
    /* How to generate a sequence of ugly numbers ?
    * 1 (1th)
    *
    *
    * 2 (2th) * 2 (5th)  * 2  // but need to re-order using PriorityQueue's offer() and poll().
    *         * 3 (6th)
    *         * 5 (7th)
    *         * 3 (8th)  * 3
    *         * 5 (9th)
    *
    * 3 (3th) * 3 (10th) * 3
    *         * 5 (11th)
    *         * 5 (12th) * 5
    *
    * 5 (4th) * 5 (13th) * 5
    *
    *
    * Problem: can not keep the relatively sort, so using PriorityQueue.
    * In PriorityQueue, two properties:
    * 1) lastElement: s.t  for number 15: =(3 * 5), so the last element is 5. When get this value, last element is generated
    * by (3 * 5 * 5). But can not generate (3 * 5 * 3).
    * 2) val: the real value of number. using for comparator in PriorityQueue.
    * */
    public int nthUglyNumber(int n) {
        UglyComparator comparator = new UglyComparator();
        PriorityQueue<UglyNumber> queue = new PriorityQueue<UglyNumber>(comparator);

        if (n == 1) return 1;

        queue.offer(new UglyNumber(2,2));
        queue.offer(new UglyNumber(3,3));
        queue.offer(new UglyNumber(5,5));

        int count = 1;
        while( ! queue.isEmpty()) {
            UglyNumber num = queue.poll();
            count ++;
            if (count == n) return (int)num.val;
            else {
                long last = num.lastElement;
                if (last == 2) {
                    queue.offer(new UglyNumber(2,2*num.val));
                    queue.offer(new UglyNumber(3,3*num.val));
                    queue.offer(new UglyNumber(5,5*num.val));
                }
                else if (last == 3) {
                    queue.offer(new UglyNumber(3,3*num.val));
                    queue.offer(new UglyNumber(5,5*num.val));
                }
                else if (last == 5) {
                    queue.offer(new UglyNumber(5,5*num.val));
                }
            }
        }
        return 0;
    }

    public class UglyComparator implements Comparator<UglyNumber> {
        @Override
        public int compare(UglyNumber n1, UglyNumber n2) {
            if (n1.val > n2.val) return 1;
            else if (n1.val < n2.val) return -1;
            else return 0;
        }
    }

    // Solution 1: use map for DP; judge if each element is ugly: scan from number/2 -> 2.
    // Time out of Limit
    public int nthUglyNumber_2(int n) {
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        map.put(2, true);
        map.put(3, true);
        map.put(5, true);

        int uglyNo = 1;
        int i = 0;
        while (i < n) {
            if (isUgly(uglyNo, map)) {
                i ++;
            }
            uglyNo ++;
        }
        return (uglyNo -1);
    }
    public boolean isUgly(int number, Map<Integer, Boolean> map) {
        if (number == 1) return true;
        if (map.containsKey(number)) {
            return map.get(number);
        }
        int copyNum = number;

        int j = (copyNum /2); // PAY attention !!!
        while (number > 1) {
            boolean res = false;
            for (; j >=2 ; j --) {
                if ( (number % j)  == 0 ) {
                    if ( map.containsKey(j) && (map.get(j))) {
                        number = number / j;
                        res = true;
                        break;
                    }
                }
            }
            if (! res) {
                map.put(number, false);
                return false;
            }
        }
        map.put(copyNum, true);
        return true;
    }

    public static void  main(String[] args) {
        nthUglyNumber ob = new nthUglyNumber();
        System.out.println(ob.nthUglyNumber(1407));
    }
}
