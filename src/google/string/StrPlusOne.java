package google.string;

/**
 * Created by yingtan on 11/5/15.
 */
/*
* 给⼀一个unsigned Integer in String type. plus one and return the
result as String. 之后Follow up是变成signed，传⼊入的可能为正
也可能为负数
*
* */
public class StrPlusOne {

    public String plusOne(int num) {

        int carry = 1;
        String res = "";
        while (num > 0) {
            int digit = num % 10;
            int strDigit = (digit + carry) % 10;

            res = strDigit + res;

            carry = (digit+carry) / 10;
            num = num / 10;
        }
        if (carry > 0) {
            res = carry + res;
        }
        return res;
    }

}
