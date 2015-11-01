package bloomberg;

/**
 * Created by yingtan on 10/25/15.
 */
// in place: two pointer
public class RemoveVowel {

    public char[] removeVowels(char[] chs) {
        if ( (chs == null) || (chs.length == 0) ) return chs;

        int curIndex = 0;
        int curLargestIndexAfterRemoval = 0;

        while (curIndex < chs.length) {
            char ch = chs[curIndex];

            if ((ch == 'a') || (ch == 'o') || (ch == 'e') || (ch == 'u') || (ch == 'i')) {
                curIndex ++;
            }
            else {
                chs[curLargestIndexAfterRemoval] = chs[curIndex];
                curLargestIndexAfterRemoval ++;
                curIndex ++;
            }
        }

        for (int i = curLargestIndexAfterRemoval; i < chs.length; i ++) {
            chs[i] = ' ';
        }
        return chs;
    }

    public static void main(String[] args) {
        RemoveVowel ob = new RemoveVowel();
        char[] chs = new char[]{'e', 'd', 'g', ' ', 'b', 'e', 'a', 't', 's', ' ', 'f', 'n', 'c'};
        ob.removeVowels(chs);
        System.out.println(new String(chs));
    }
}
