package apple.array;

public class ReverseInteger {
    public int reverse(int x) {
        long res = 0;
        if (x == 0) return 0;
        boolean isNeg = false;
        long newx = x;
        if (newx < 0) {
            isNeg = true;
            newx = newx * -1;
        }
        while(newx > 0) {
            int digit = (int)(newx % 10);
            res = res * 10 + digit;
            if (res > Integer.MAX_VALUE) {
                return 0;
            }
            newx = newx / 10;
           // if (x == 0) break;
        }
        if (isNeg) {
            res = -1 * res;
        }
        if (res > Integer.MAX_VALUE) {
            return 0;
        }
        else return (int)res;
    }
}
