package facebook.array;

/**
 * Created by yingtan on 12/20/17.
 */
public class MaximumSwap {

    // Solution of myself
    public int maximumSwap(int num) {
        String str = num + "";
        char[] chs = str.toCharArray();
        for (int i = 0 ; i < chs.length  -1 ; i ++) {
            char cur = chs[i];
            int max = -1;
            int index = -1;
            for (int j = i + 1; j < chs.length; j ++) {
                if (chs[j] > cur) {
                    // Important !!!! >= max
                    if (chs[j] - '0' >= max) {
                        max = chs[j] - '0';
                        index = j;
                    }
                }
            }
            if (max != -1) {
                chs[i] = (char)(max + '0');
                chs[index] = cur;
                return Integer.parseInt(new String(chs));
            }
        }
        return num;
    }

    public static void main(String[] args) {
        MaximumSwap ob = new MaximumSwap();
        System.out.println(ob.maximumSwap(1993));
    }
}
