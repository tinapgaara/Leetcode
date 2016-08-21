package dataStructure;

/**
 * Created by yingtan on 8/6/16.
 *
 *
 *
 380. Insert Delete GetRandom O(1)  QuestionEditorial Solution  My Submissions

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

 // Since 1 is the only number in the set, getRandom always return 1.
 randomSet.getRandom();

 */
import java.util.*;

public class RandomizedSet {

    private List<Integer> randomList;
    private Map<Integer, Integer> randomIndex;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        randomList = new ArrayList<Integer>();
        randomIndex = new HashMap<Integer, Integer>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (! randomIndex.containsKey(val)) {
            randomList.add(val);

            int listSize = randomList.size();
            int valIndex = listSize -1;
            randomIndex.put(val, valIndex);

            return true;
        }
        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (randomIndex.containsKey(val)) {
            int index = randomIndex.get(val);

            // remove elements corresponds to this index in arraylist

            // remove list's last element
            int lastElement = randomList.remove(randomList.size() - 1);

            // remove map's element
            randomIndex.remove(val);

            // set arraylist's index's element == arrayList's last element

            // ************* place easy to be wrong !!!!! ************ //
            if (index < randomList.size()) {
                randomList.set(index, lastElement);

                // update maps' index
                randomIndex.put(lastElement, index);
            }

            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random rnd = new Random();
        int size = randomList.size();
        int index = rnd.nextInt(size);

        return randomList.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
