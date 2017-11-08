package facebook.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by yingtan on 5/21/17.
 *
 * 380. Insert Delete GetRandom O(1)
 *
 *
 * Total Accepted: 28721
 Total Submissions: 73836
 Difficulty: Medium
 Contributor: LeetCode
 Design a data structure that supports all following operations in average O(1) time.

 insert(val): Inserts an item val to the set if not already present.
 remove(val): Removes an item val from the set if present.
 getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 Example:

 // Init an empty set.
 RandomizedSet randomSet = new RandomizedSet();

 // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 randomSet.insert(1);

 // Returns false as 2 does not exist in the set.
 randomSet.remove(2);

 // Inserts 2 to the set, returns true. Set now contains [1,2].
 randomSet.insert(2);

 // getRandom should return either 1 or 2 randomly.
 randomSet.getRandom();

 // Removes 1 from the set, returns true. Set now contains [2].
 randomSet.remove(1);

 // 2 was already in the set, so return false.
 randomSet.insert(2);

 // Since 2 is the only number in the set, getRandom always return 2.
 randomSet.getRandom();

 *
 */
public class GetRandom {

    // key point:
    /*
    Same probability:
    Random rnd = new Random()
    rnd.nextInt(size): return a element from [0, size-1] with same probability

    so,
    1) keep a list which has dynamic sizes and stores the elements
       To make this dynamic size, everytime when removing elements, need to swap the removed elements with the tail element, and removed tail(o(1)). However, get element's index in arraylist is o(n), so we need to hashmap to keep the index of element.
    2) keep a hashmap which stores the index of element
    */

    List<Integer> randomList;
    HashMap<Integer, Integer> randomIndex;

    /** Initialize your data structure here. */
    public GetRandom() {
        randomList = new ArrayList<Integer>();
        randomIndex = new HashMap<Integer, Integer>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (randomIndex.containsKey(val)) {
            return false;
        }
        else {
            int insertIndex = randomList.size();
            randomList.add(val);
            randomIndex.put(val, insertIndex);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (randomIndex.containsKey(val)) {
            int removedIndex = randomIndex.remove(val);
            // set tail's element to removedIndex
            int tailIndex = randomList.size() - 1;
            int tailVal = randomList.remove(tailIndex);

            // Important !
            // when removed element is exactly the tail element, then, removedIndex will > randomList.size()
            if (removedIndex < randomList.size()) {
                randomList.set(removedIndex, tailVal);
                randomIndex.put(tailVal, removedIndex);
            }
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random rdm = new Random();
        int randomIndex = rdm.nextInt(randomList.size());
        return randomList.get(randomIndex);

    }
}
