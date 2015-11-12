package bloomberg.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by max2 on 10/15/15.
 */
public class IntersectionOfTwoArrays {

    // Solution 1: use hash map
    // Solution 2: no extra space
    public List<Integer> intersection(int[] a1, int[] a2) {
        Arrays.sort(a1);
        Arrays.sort(a2);

        List<Integer> res = new ArrayList<Integer>();

        int i = 0;
        int j = 0;
        while ((i < a1.length) && (j < a2.length)) {
            if (a1[i] == a2[j]) {
                res.add(a1[i]);
                i ++;
                j ++;
            }
            else if (a1[i] > a2[j]) j ++;
            else i ++;
        }

        while (i < a1.length) {
            if (a2[a2.length-1] == a1[i]) {
                res.add(a1[i]);
                i++;
            } else if (a2[a2.length-1] < a1[i]) break;
            else i ++;
        }

        while (j < a2.length) {
            if (a1[a1.length-1] == a2[j]) {
                res.add(a2[j]);
                j++;
            } else if (a1[a1.length-1] < a2[j]) break;
            else j ++;
        }
        return res;
    }

    public static void main(String[] args) {
        IntersectionOfTwoArrays ob = new IntersectionOfTwoArrays();
        int[] a1= new int[]{2,4,5,7,9};
        int[] a2 = new int[]{4,5,7,10};
        System.out.println(ob.intersection(a1, a2));
    }
}
