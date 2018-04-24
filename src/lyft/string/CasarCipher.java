package lyft.string;

/**
 * Created by yingtan on 4/10/18.
 *
 * Ceasar Cipher 简单说就是 abc + 2 --> cde, xyz + 2 --> zab 不是字母不变 区分大小写

 */
public class CasarCipher {

    public String moveX(String str, int dist) {
        char[] chs = str.toCharArray();
        for (int i = 0 ; i < chs.length; i ++) {
            char ch = chs[i];
            int translate = ((ch - 'a') + dist) % 26;
            char newch = (char)('a' + translate);
            chs[i] = newch;
        }
        return new String(chs);
    }
    public static void main(String[] args) {
        CasarCipher ob = new CasarCipher();
        System.out.println(ob.moveX("xyz", 2));
    }
}
