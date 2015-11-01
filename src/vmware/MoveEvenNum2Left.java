package vmware;

/**
 * Created by yingtan on 10/28/15.
 */
public class MoveEvenNum2Left {

    // Solution 1: two pointer
    // can not keep previous relative ordering
    public void move(int[] num) {
        int low = 0;
        int high = num.length - 1;

        while (low < high) {
            int lowNum = num[low];
            int highNum = num[high];
            // even even : low ++;
            // even odd: continue;
            // odd even: exchange, high -- , low ++;
            // odd odd: high --;
            if (lowNum % 2 == 0) {
                if (highNum % 2 == 0) {
                    low ++;
                }
                else {
                    continue;
                }
            }
            else {
                if (highNum % 2 == 0) {
                    int tmp = num[low];
                    num[low] = num[high];
                    num[high] = tmp;

                    low ++;
                    high --;
                }
                else {
                    high --;
                }
            }
        }
    }

    public static void main(String[] args) {
        MoveEvenNum2Left ob = new MoveEvenNum2Left();
        int[] num = new int[]{2,5,6,8};
        ob.move(num);
        System.out.println(num);
    }
}
