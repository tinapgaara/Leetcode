package google.twoPointers;

/**
 * Created by yingtan on 1/7/17.
 */
public class ReverseVowels {

    public String reverseVowels(String s) {
        int len = s.length();
        int low = 0 ;
        int high = len - 1;
        String vowels = "aeiouAEIOU";
        char[] chs = s.toCharArray();
        while (low < high) {

            while ( (low < high) && (! vowels.contains(s.charAt(low) + "") ) ) low ++;
            while ( (low < high) && (! vowels.contains(s.charAt(high) + "") ) ) high --;

            char temp = chs[low] ;
            chs[low] = chs[high];
            chs[high] = temp;

            low ++;
            high --;
        }
        return new String(chs);
    }
}
