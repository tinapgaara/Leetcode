package google.array;

/**
 * Created by yingtan on 11/6/15.
 */
/*
* Give a list of numbers:
3, 5, 9, 2
Generate a signature for the list of numbers:
i, i, d

because 3->5: increase: i
because 9->2: decrease: d

Example:
6, 8, 3, 5, 2. i,d,i,d


Question is:
Given a signature for a list of number, trying to print out a list of
number that fits the signature, with lowest value of the highest
number, and at the same time, no duplicated numbers
Numbers[1, N]

eg:
iidd: minNum = 12543

1:
i: 2:
i: 3
d: 4: no match

核⼼心就是每次记录last decreasing point. insert next
number before last decreasing point

1 2 4 3:   4 is the last decreasing point
1 2 4 3 6 5: 6 is the last decreasing point
* */
public class IncreaseDecreaseArrSignature {

    // starts from 1, ends with len+1.
    public void printNumbers(char[] signature) {

        int[] res = new int[signature.length + 1];
        res[0] = 1; // important !!!
        int lastDecresingPoint = 0;
        for (int i = 1 ; i < res.length ; i ++) {
            char tend = signature[i-1];
            if (tend == 'i') {
                res[i] = res[i-1] + 1;
                lastDecresingPoint ++;
            }
            else {
                int curVal = i+1; // important !!!
                res[i] = curVal;
                // insert curVal before the lastDecresingPoint
                int prev = i;
                for (int j = i-1 ; j >= lastDecresingPoint; j --) {
                    int tmp = res[j];
                    res[j] = res[prev];
                    res[prev] = tmp;

                    prev = j;
                }
            }
        }

        for(int i = 0 ; i < res.length ; i ++) {
            System.out.println(res[i]);
        }
    }

    public static void main(String[] args) {
        IncreaseDecreaseArrSignature ob = new IncreaseDecreaseArrSignature();
        char[] signature = new char[]{'i', 'i', 'd'};
        ob.printNumbers(signature);
    }


}
