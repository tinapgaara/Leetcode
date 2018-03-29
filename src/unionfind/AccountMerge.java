package unionfind;

/**
 * Created by yingtan on 2/25/18.
 */
import java.util.*;
public class AccountMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) return res;
        Map<String, String> parents = new HashMap<>();
        Map<String, String> owners = new HashMap<>();
        Map<String, TreeSet<String>> groups = new HashMap<>();
        for (List<String> account : accounts) {
            String user = account.get(0);
            for (int i = 1; i < account.size(); i ++) {
                parents.put(account.get(i), account.get(i));
                owners.put(account.get(i), user);
            }
        }
        for (List<String> account : accounts) {
            String parent = findParent(parents, account.get(1));
            for (int i = 2; i < account.size(); i ++) {
                String accountParentToUnion = findParent(parents, account.get(i));
                if (! parent.equals(accountParentToUnion)) {
                    parents.put(accountParentToUnion, parent); // union
                }
            }
        }
        for (List<String> account : accounts) {
            String parent = findParent(parents, account.get(1));
            if (! groups.containsKey(parent)) {
                groups.put(parent, new TreeSet<String>());
            }
            for (int i = 1; i < account.size(); i ++) {
                // must belong to parent in this account list
                groups.get(parent).add(account.get(i));
            }
        }
        for (Map.Entry<String, TreeSet<String>> entry : groups.entrySet()) {
            TreeSet<String> set = entry.getValue();
            String user = owners.get(entry.getKey());
            List<String> userAccount = new ArrayList<>();
            userAccount.add(user);
            //userAccount.add(entry.getKey());
            userAccount.addAll(set);
            res.add(userAccount);
        }
        return res;
    }
    public String findParent(Map<String, String> parent, String account) {
        if(parent.containsKey(account) && ! parent.get(account).equals(account)) {
            parent.put(account, findParent(parent, parent.get(account)));
        }
        return parent.get(account);
    }
}
