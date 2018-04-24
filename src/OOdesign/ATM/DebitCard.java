package OOdesign.ATM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 4/15/18.
 */
public class DebitCard {
    private long cardId;
    private List<Account> account;
    public DebitCard(long id) {
        account = new ArrayList<>();
        this.cardId = id;
    }
    public void addAccount(Account a) {
        account.add(a);
    }
}
