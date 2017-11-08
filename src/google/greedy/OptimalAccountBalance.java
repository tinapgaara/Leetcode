package google.greedy;

import java.util.*;

/**
 * Created by yingtan on 10/28/17.
 *
 * 465. Optimal Account Balancing
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

 Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

 Note:

 A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 Example 1:

 Input:
 [[0,1,10], [2,0,5]]

 Output:
 2

 Explanation:
 Person #0 gave person #1 $10.
 Person #2 gave person #0 $5.

 Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 Example 2:

 Input:
 [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

 Output:
 1

 Explanation:
 Person #0 gave person #1 $10.
 Person #1 gave person #0 $1.
 Person #1 gave person #2 $5.
 Person #2 gave person #0 $5.

 Therefore, person #1 only need to give person #0 $4, and all debt is settled.


 Backtracking: time complexity O(N!)

 Use HashMap to store the initial debts of each person,
 negative means the person sends money to others, positive means the person gets money from others.

 now if the map value is 0, which means the person is all set, free of debts.

 Only consider those people with debts(either positive or negative)

 store them in an array, use backtracking and greedy to clear each person's debts from 1st person till last one.

 How to clear one person's debt? find a person 2 in the following array that has opposite sign(+->-  or - -> +), and clear person1's whole debt with person2 only.

 Here's the trick: example: [7, -6, -1], one obvious optimal solution is person1 pay $6 to person2, and pay $1 to person3. Notice that this optimal solution is equivalent to another solution:

 person1 pay $7 to person2, and person2 pay $1 to person3. So when doing DFS, everytime we only consider clearing person1's debt wholly with another 1 person, we don't need to consider clearing with other more people, cause clearing with 1 person is already guaranteed to be optimal.



 This problem still has some debates in discussion, will check later
 */
public class OptimalAccountBalance {

    int res = Integer.MAX_VALUE;
    public int minTransfers(int[][] transactions) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
         for (int[] transaction : transactions) {
             map.put(transaction[0], map.getOrDefault(transaction[0], 0)-transaction[2]);
             map.put(transaction[1], map.getOrDefault(transaction[1], 0)+transaction[2]);
         }
        ArrayList<Integer> depts = new ArrayList<Integer>();
        for (int dept : map.values()) {
            if (dept != 0) depts.add(dept);
        }
        helper(depts, 0, 0);
        return res;
    }
    // clear the user[start] debt, distribute his money to following users
    public void helper(ArrayList<Integer> depts, int start, int count) {
        // skip 0 debt users
        while (start < depts.size() && depts.get(start) == 0) start++;
        if (start == depts.size()) {
            res = Math.min(res, count);
            return;
        }
        for (int i = start + 1; i < depts.size(); i++) {
            // -1 +1 , +1 -1
            if (depts.get(start) < 0 && depts.get(i) > 0 || depts.get(start) > 0 && depts.get(i) < 0) {
                depts.set(i, depts.get(i) + depts.get(start));
                helper(depts, start + 1, count + 1);
                depts.set(i, depts.get(i) - depts.get(start));
            }
        }
    }

    public int minTransfers_notWork(int[][] transactions) {
        if (transactions == null || transactions.length == 0) return 0;

        Map<Integer, Integer> accountMap = new HashMap<>();
        for (int i = 0 ; i < transactions.length; i ++) {
            int start = transactions[i][0];
            int end = transactions[i][1];
            int money = transactions[i][2];

            if(accountMap.containsKey(start)) {
                accountMap.put(start, accountMap.get(start) - money);
            }
            else {
                accountMap.put(start, -1 * money);
            }
            if(accountMap.containsKey(end)) {
                accountMap.put(end, accountMap.get(end) + money);
            }
            else {
                accountMap.put(end, money);
            }
        }
        Integer[] account = new Integer[accountMap.size()];
        int index = 0;
        boolean isBalance = true;
        for (Map.Entry<Integer, Integer> entry : accountMap.entrySet()) {
            account[index] = entry.getValue();
            index ++;

            if (entry.getValue() != 0) {
                isBalance = false;
            }
        }
        // case: [[0,1,1],[1,2,1],[2,0,1]]
        if (isBalance) return 0;

        // sort the array from high to low
        Arrays.sort(account, new MoneyComparator());

        int res = 0;
        int low = 0;
        int high = account.length - 1;
        while (low < high) {
            if (account[low] == 0 && account[high] == 0) {
                low ++;
                high --;
                continue;
            }
            else if (account[low] == -1 * account[high]) {
                account[low] = 0;
                account[high] = 0;
                // move the account[low] -> account[high]
                low ++;
                high --;
            }
            else if (account[low] <= -1 * account[high]) {
                account[high] = account[high] + account[low];
                low ++;
            }
            else {
                account[low] = account[low] + account[high];
                high --;
            }
            res ++;
        }
        return res;
    }

    public class MoneyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer s1, Integer s2) {
            return s2.intValue() - s1.intValue();
        }
    }

    public static void main(String[] args) {
        OptimalAccountBalance ob = new OptimalAccountBalance();
        int[][] transactions = new int[][]{{0,1,1},{0,2,1},{1,3,1},{2,3,1}};
        ob.minTransfers(transactions);
    }
}
