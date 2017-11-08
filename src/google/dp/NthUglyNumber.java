package google.dp;

/**
 * Created by yingtan on 9/5/17.
 */
public class NthUglyNumber {

    public int nthUglyNumber(int n){
        int i2=0, i3=0, i5=0;
        int[] k = new int[n];
        k[0] = 1;
        for (int i=1; i<n; i++) {
            k[i] = Math.min(k[i2]*2, Math.min(k[i3]*3, k[i5]*5));
            if (k[i]%2 == 0) i2++;
            if (k[i]%3 == 0) i3++;
            if (k[i]%5 == 0) i5++;
        }

        return k[n-1];
    }

    public static void main(String[] args) {
        NthUglyNumber ob = new NthUglyNumber();
        ob.nthUglyNumber(4);
    }
}
