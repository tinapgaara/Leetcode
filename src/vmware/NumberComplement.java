package vmware;

public class NumberComplement {

    public int complement(int num) {
        // positive number complement = positive number
        if (num >= 0) return num;
        // negative number complement
        else {
            num = ~ num;
            num = num + 1;
        }
        return num;
    }
}
