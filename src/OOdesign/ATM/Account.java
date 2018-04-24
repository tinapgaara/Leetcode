package OOdesign.ATM;

/**
 * Created by yingtan on 4/15/18.
 */
public class Account {
    private float balance;
    public void deplositeMoney(float amount) {
        this.balance = this.balance + amount;
    }
    public boolean withDrawMoney(float amount) {
        if (balance > amount) {
            balance = balance - amount;
            return true;
        }
        else {
            return false;
        }
    }
    public float getBalance() {
        return balance;
    }
}
