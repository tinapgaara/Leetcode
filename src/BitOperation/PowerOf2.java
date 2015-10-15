package BitOperation;

/**
 * Created by yingtan on 10/14/15.
 */
public class PowerOf2 {

    public boolean isPowerOfTwo(int n) {
        int helper = 1;
        int i = 0;
        while ((helper < n) && (i < 31)) {  // Important !!!! : i should be smaller than 31!!!
            helper = helper << 1;
            i ++;
            System.out.println(helper);
        }
        if (helper != n) return false;
        else return true;

    }

    public static void main(String[] args) {
        PowerOf2 ob = new PowerOf2();
        System.out.println(ob.isPowerOfTwo(1073741824));
    }
}
