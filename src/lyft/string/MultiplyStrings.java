package lyft.string;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return null;
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0 ; i --) {
            for (int j = num2.length() - 1; j >= 0 ; j --) {
                res[i + j +1] = res[i + j +1] + (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        int carry = 0;
        for (int i = res.length - 1; i >= 0 ; i --) {
            int digit = res[i] + carry;
            carry = (digit) / 10;
            res[i] = (digit) % 10;
        }
        StringBuilder st = new StringBuilder();
        for (int i = 0 ; i <res.length; i ++) {
            st.append(res[i]);
        }
        while(st.length() > 0 && st.charAt(0) == '0') {
            st.deleteCharAt(0);
        }
        if (st.length() == 0) return "0";
        return st.toString();
    }
}
