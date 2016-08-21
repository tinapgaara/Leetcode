package google.mathpro;

/**
 * Created by yingtan on 12/20/15.
 */
public class ExcelSheetColumnTitle {

    public String convertToTitle(int n) {
        StringBuffer buffer = new StringBuffer();

        while (n > 0) {
            int rest = n % 26;
            n = n / 26;
            if (rest == 0) {
                buffer.append('Z');
                n --;
            }
            else {
                buffer.append((char)(64 + rest));
            }
        }
        return buffer.reverse().toString();
    }

    public static void main(String[] args) {
        ExcelSheetColumnTitle ob = new ExcelSheetColumnTitle();
        ob.convertToTitle(26);
    }
}
