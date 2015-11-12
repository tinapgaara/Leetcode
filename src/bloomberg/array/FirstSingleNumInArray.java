package bloomberg.array;

import java.util.Arrays;

/**
 * Created by max2 on 10/15/15.
 */
public class FirstSingleNumInArray {

    public int findFirstSingleElement(int[] num) {
        Arrays.sort(num);
        if (num.length == 1) return num[0];

        int prevIndex = 0;
        int prev = num[0];
        int curIndex = 1;
        while (curIndex < num.length){
            int cur = num[curIndex];
            if (cur == prev) curIndex ++;
            else {
                if (curIndex - prevIndex== 1) return num[prevIndex];
                prev = cur;
                prevIndex = curIndex;
                curIndex ++;
            }
        }
        if (num[num.length-1] != num[num.length-2]) return num[num.length-1];
        return -1;
    }

    public static void main(String[] args) {
        FirstSingleNumInArray ob = new FirstSingleNumInArray();
        int[] num = new int[]{4,4,4,4,4,4,6,6,6,7};
        System.out.println(ob.findFirstSingleElement(num));
    }
}
