package facebook.unionfind;

/**
 * Created by yingtan on 2/19/18.
 * 721. Accounts Merge
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

 Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

 After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

 Example 1:
 Input:
 accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 Explanation:
 The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 The second John and Mary are different people as none of their email addresses are used by other accounts.
 We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 */
import java.util.*;
public class AccountsMerge {
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
