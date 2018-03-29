package facebook.array;

public class AddBinary {
    public String addBinary(String a, String b) {
        if (a == null) return b;
        if (b == null) return a;
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder st = new StringBuilder();
        while(i >= 0 && j >= 0) {
            int sum = (a.charAt(i) - '0') + (b.charAt(j) - '0') + carry;
            int digit = sum % 2;
            carry = sum / 2;
            st.append(digit);
            i --;
            j --;
        }
        while(i >= 0) {
            int sum = (a.charAt(i) - '0') + carry;
            int digit = sum % 2;
            carry = sum / 2;
            st.append(digit);
            i --;
        }
        while(j >= 0) {
            int sum = (b.charAt(j) - '0') + carry;
            int digit = sum % 2;
            carry = sum / 2;
            st.append(digit);
            j --;
        }
        if (carry > 0) {
            st.append(carry);
        }
        return st.reverse().toString();
    }
    public String addBinary_kbase(String a, String b, int k) {
        if (a == null) return b;
        if (b == null) return a;
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder st = new StringBuilder();
        while(i >= 0 && j >= 0) {
            int sum = (a.charAt(i) - '0') + (b.charAt(j) - '0') + carry;
            int digit = sum % k;
            carry = sum / k;
            st.append(digit);
            i --;
            j --;
        }
        while(i >= 0) {
            int sum = (a.charAt(i) - '0') + carry;
            int digit = sum % k;
            carry = sum / k;
            st.append(digit);
            i --;
        }
        while(j >= 0) {
            int sum = (b.charAt(j) - '0') + carry;
            int digit = sum % k;
            carry = sum / k;
            st.append(digit);
            j --;
        }
        if (carry > 0) {
            st.append(carry);
        }
        return st.reverse().toString();
    }
    public static void main(String[] args) {
        AddBinary ob = new AddBinary();
        System.out.println(ob.addBinary_kbase("244", "56", 10));
    }
}
