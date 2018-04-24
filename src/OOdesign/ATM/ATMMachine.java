package OOdesign.ATM;

import java.util.List;

/**
 * Created by yingtan on 4/15/18.
 */
public class ATMMachine {
    private float totalamount;
    private Session session;
    private Bank bank;
    public ATMMachine(float amount) {
        this.totalamount = amount;
        bank = new Bank();
    }
    public void insertDebitCard(DebitCard card) {
        session = new Session(card);
    }
    public List<Account> login(String passcode) {
        if (bank.valid(session.getCard(), passcode)) {
            return bank.getAccounts(session.getCard());
        }
        return null;
    }
    public void selectAccount(Account account) {
        session.setCurrentAccount(account);
    }
    public float checkBalance() {
        return session.getAccount().getBalance();
    }
    public void depositeMoney(float amount) {
        session.getAccount().deplositeMoney(amount);
    }
    public float withDrawMoney(float amount) {
        if (totalamount > amount) {
            // ATM has enough money
            if(session.getAccount().withDrawMoney(amount)) {
                return amount;
            }
            return 0;
        }
        else {
            return 0;// ATM has un-enough balance
        }
    }
    public void logout() {
        session = null;
    }

    public static void main(String[] args) {
        ATMMachine machine = new ATMMachine(1000);
        DebitCard card = new DebitCard(1234);
        machine.insertDebitCard(card);
        List<Account> accounts = machine.login("pasword");
        Account chosedCount = accounts.get(0);
        machine.selectAccount(chosedCount);

        machine.depositeMoney(100);
        machine.checkBalance();
        machine.logout();
    }
}
