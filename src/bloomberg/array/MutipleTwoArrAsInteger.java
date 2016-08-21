package bloomberg.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 10/25/15.
 */
public class MutipleTwoArrAsInteger {

    // Solution 2: shorter code:
    public String multiply_2(String num1, String num2) {
        int[] num = new int[num1.length()+num2.length()]; // new num's length must be num1.len + num2.len
        int len1 = num1.length(), len2 = num2.length();
        for(int i=len1-1;i>=0;i--){
            for(int j=len2-1;j>=0;j--){
                int temp = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                num[i+j] += (temp+num[i+j+1])/10;
                num[i+j+1] = (num[i+j+1]+temp)%10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i: num) if(sb.length()>0||i>0)  sb.append(i);
        return (sb.length()==0)?"0":sb.toString();
    }



    // Solution 1 : complicated way !!!
    public String multiply(String num1, String num2) {

        if ( (num1 == null) || (num1.length() == 0) || (num2 == null) || (num2.length() == 0) )
            return "";

        List<Integer> arr1 = new ArrayList<>();
        for (int i = 0 ; i < num1.length(); i ++) {
            arr1.add(num1.charAt(i) - '0');
        }

        List<Integer> arr2 = new ArrayList<>();
        for (int i = 0 ; i < num2.length(); i ++) {
            arr2.add(num2.charAt(i) - '0');
        }

        List<Integer> res = multipleTwoArray(arr1, arr2);

        String str = "";
        boolean ifOverLook = true;

        for (int i = 0 ; i < res.size() ; i ++) {
            if (res.get(i) != 0) ifOverLook = false;
            if (!ifOverLook)
                str = str + res.get(i);
        }

        if (str.length() == 0) str = "0";
        return str;
    }

    public List<Integer> multipleTwoArray(List<Integer> arr1, List<Integer> arr2) {

        int i = arr2.size() - 1;

        List<Integer> res = new ArrayList<>();
        int zeros = 0; //  Important !!!!
        while (i >= 0 ) {
            int j = arr1.size() - 1;
            int digit2 = arr2.get(i);
            int carry = 0;

            List<Integer> curLevel = new ArrayList<>();
            while (j >= 0) {
                int digit1 = arr1.get(j);

                int product = digit1 * digit2 + carry;
                carry = product / 10;

                int digit = product % 10;

                curLevel.add(0, digit);
                j --;
            }

            if (carry > 0) {
                curLevel.add(0, carry);
            }
            if (res.size() == 0) res = curLevel;
            else {

                for (int k = 0 ; k < zeros ; k ++)
                    curLevel.add(0);
                res = sumTwoArray(curLevel, res);
            }
            zeros ++;
            i --;
        }
        return res;
    }

    public List<Integer> sumTwoArray(List<Integer> arr1, List<Integer> arr2) {
        int carry = 0;

        List<Integer> res = new ArrayList<>();

        int i = arr1.size() - 1;
        int j = arr2.size() - 1;

        while ((i >= 0 ) && (j >= 0)) {
            int digit1 = arr1.get(i);
            int digit2 = arr2.get(j);

            int digit = (digit1 + digit2 + carry) % 10;
            carry = (digit1 + digit2 + carry) / 10;

            res.add(0, digit);

            i --;
            j --;
        }

        if (i >= 0) {
            while (i >= 0) {
                int digit = (arr1.get(i) + carry) % 10;
                carry = (arr1.get(i) + carry) / 10;

                res.add(0, digit);
                i --;
            }
        }
        else {
            while (j >= 0) {
                int digit = (arr2.get(j) + carry) % 10;
                carry = (arr2.get(j) + carry) / 10;

                res.add(0, digit);
                j --;
            }
        }

        if (carry > 0) res.add(0, carry); // Important !!!!
        return res;
    }

    public static void main(String[] args) {
        MutipleTwoArrAsInteger ob = new MutipleTwoArrAsInteger();

        List<Integer> arr1 = Arrays.asList(2,3,7);
        List<Integer> arr2 = Arrays.asList(2,8,4);
        System.out.println(ob.multiply("123", "456"));
        System.out.println(ob.multipleTwoArray(arr1,arr2));

    }
}
