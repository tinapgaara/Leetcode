package OOdesign.ATM;

/**
 * Created by yingtan on 4/15/18.
 */
public class Session {
    private DebitCard currrentDebitCard;
    private Account currentAccount;
    public Session(DebitCard card) {
        currrentDebitCard = card;
    }
    public void setCurrrentDebitCard(DebitCard card) {
        currrentDebitCard = card;
    }
    public DebitCard getCard() {
        return currrentDebitCard;
    }
    public void setCurrentAccount(Account account) {
        currentAccount = account;
    }
    public Account getAccount() {
        return currentAccount;
    }
}

