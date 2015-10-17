package bloomberg;

/**
 * Created by max2 on 10/16/15.
 */
/*
* Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*
* */
public class Int2Roman {

    public String intToRoman(int num) {
        String[] romans = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
        int[] numbers = {1,4,5,9,10,40,50,90,100,400,500,900,1000};

        String res  = "";
        for (int i = numbers.length-1; i >= 0 ; i --) {
            int divide = numbers[i];
            if ((num / divide) > 0) { // find the first large dividend which is smaller than num.
                int times = num / divide;
                for (int j = 0; j < times; j++) {
                    res = res + romans[i];
                }
                num = num % divide;
            }
        }
        return res;
    }
}
