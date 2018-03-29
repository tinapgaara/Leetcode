package string;

/**
 * Created by yingtan on 1/10/18.
 */
public class IntegerToString {

    public String convert(int num) {
        boolean isNeg = false;
        if (num < 0 ) {
            isNeg = true;
        }
        StringBuilder s = new StringBuilder();
        while(num != 0) {
            int digit = num % 10;
            s.append('0' + digit);
            num = num / 10;
        }
        if (isNeg) {
            s.append("-");
        }
        s.reverse();
        return s.toString();
    }
}
