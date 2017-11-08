package google.array;

/**
 * Created by yingtan on 11/5/17.
 *
 * 给两个字符串，一个是另一个多了个插入的字符，找出是哪个字符。时间空间各种trade off。比较简单
 */
public class FindInsertCh {

    public char findCh(String word1, String word2) {
        int xor = 0;
        int i = 0;
        for (; i < Math.min(word1.length(),word2.length()); i ++) {
            xor = xor ^ word1.charAt(i) ^ word2.charAt(i);
        }
        if (i < word1.length()) {
            xor = xor ^ word1.charAt(i);
        }
        else if (i < word2.length()) {
            xor = xor ^ word2.charAt(i);
        }
        System.out.println((char)xor);
        return (char)xor;
    }
    public static void main(String[] args) {
        FindInsertCh ob = new FindInsertCh();
        ob.findCh("he", "heh");
    }
}
