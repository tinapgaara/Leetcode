package tree;

import java.util.*;

/**
 * Created by yingtan on 12/10/17.
 */
public class AddCredit {

    Map<String, Integer> clientToCredit;
    TreeMap<Integer, Set<String>> creditToClients;
    int offset = 0;

    public AddCredit() {
        creditToClients = new TreeMap<>();
        clientToCredit = new HashMap<>();
    }

    // remove(clientId) : remove this client from system
    public boolean remove(String clientId) {
        Integer credit = clientToCredit.get(clientId);
        if (credit != null) {
            clientToCredit.remove(clientId);
            Set<String> clients = creditToClients.get(credit);
            if (clients.isEmpty()) {
                creditToClients.remove(credit);
            }
            else {
                clients.remove(clientId);
            }
            return true;
        }
        return false;
    }
    // add a client with specific credits
    public void insert(String clientId, int credit) {
        int addedCredit = credit - offset;
        remove(clientId);
        clientToCredit.put(clientId, addedCredit);
        if (creditToClients.containsKey(addedCredit)) {
            creditToClients.get(addedCredit).add(clientId);
        }
        else {
            Set<String> set = new HashSet<>();
            set.add(clientId);
            creditToClients.put(addedCredit, set);
        }
    }

    // add credits to all clients simutanously
    // when a client is lookedup or doing other stuff, still need to add that credits to client
    public void addAll(int c) {
        offset = offset + c;
    }

    // get the credits of this clientId
    public int lookup(String clientId) {
        Integer credit = clientToCredit.get(clientId);
        if (credit != null) {
            return credit + offset;
        }
        else {
            return -1;
        }
    }
}
