package array;

import java.util.*;

/**
 * Created by yingtan on 9/28/15.
 */
public class FindCelebirty {

    public int findCelebrity(int n) {
        int candidate = 0;
        Queue<Integer> candidates = new LinkedList<Integer>();
        candidates.offer(candidate);
        Map<Integer, HashSet<Integer>> unknowPeople = new HashMap<Integer, HashSet<Integer>>();
        boolean[] testFlag = new boolean[n];
        while ( ! candidates.isEmpty())  {
            candidate = candidates.poll();

            if ( (candidate >= 0) && (candidate < n) && ( ! testFlag[candidate])) {
                int anotherPerson = 0;
                boolean isFind = true;
                while ((anotherPerson < n)) {
                    if (anotherPerson != candidate) {
                        boolean isKnow = knows(anotherPerson, candidate);
                        if (!isKnow) {
                            candidates.offer(anotherPerson);// push anotherPErson to queue, because it does not know candidate, can be celebirty
                            isFind = false;
                            if (unknowPeople.containsKey(anotherPerson)) {
                                unknowPeople.get(anotherPerson).add(candidate);
                            } else {
                                HashSet<Integer> newset = new HashSet<Integer>();
                                newset.add(candidate);
                                unknowPeople.put(anotherPerson, newset);
                            }
                        }
                    }
                    anotherPerson++;
                }
                if (isFind) {
                    boolean flag = checkUnknownOther(candidate, n, unknowPeople);
                    if (flag)
                        return candidate;
                }
                testFlag[candidate] = true;
            }
        }
        return -1;
    }

    public boolean checkUnknownOther(int candidate, int n, Map<Integer, HashSet<Integer>> unknowPeople) {
        if (unknowPeople.containsKey(candidate)) {
            HashSet<Integer> unfamilar = unknowPeople.get(candidate);
            if (unfamilar.size() == n) return true;
            for (int i = 0 ; i < n ; i ++) {
                if (i == candidate) continue;
                if (unfamilar.contains(i)) continue;
                if (knows(candidate, i)) return false;
            }
            return true;
        }
        else {
            for (int i = 0 ; i < n ; i ++) {
                if (i == candidate) continue;
                if (knows(candidate, i)) return false;
            }
        }
        return true;
    }

    public boolean knows(int m, int n) {
        if (m == 0) {
            if (n == 1)
                return false;
            if (n == 2)
                return true;
        }

        if (m == 2) return false;
        if (m == 1){
            if (n == 2)
                return true;
            if(n == 0)
                return false;
        }
        return false;
    }

    public static void main(String[] args) {
        FindCelebirty ob = new FindCelebirty();
        System.out.println(ob.findCelebrity(3));
    }
}
