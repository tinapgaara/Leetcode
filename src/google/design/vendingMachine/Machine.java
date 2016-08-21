package google.design.vendingMachine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/26/15.
 */
public class Machine {

    private Map<Integer, Food> map;
    private Object mMutex;

    public Machine() {
        map = new HashMap<>();
        mMutex = null;
    }

    public synchronized Object getMutex() {
        if (mMutex != null) { // occupied by others
            return null;
        }
        else {
            mMutex = new Object();
            return mMutex;
        }
    }

    // the three subactions need to be down once as a unit
    // way1: use synchronized
    // way2: use mutex
    public Food getFood(Integer id, Thread thread, Object mutex) {
        Food res = null;
        if ((mutex != null) && (mutex == mMutex)) {
            if (map.containsKey(id)) {
                // step 1: get
                Food food = map.get(id);
                System.out.println(thread.getName() + " get " + + food.getId()+ "'s count:" + food.getCount());
                // step 2: minus
                food.minusOne();
                System.out.println(thread.getName() + " after minus " + food.getId()+ "'s count:" + food.getCount());
                // step 3: remove should be in the same unit with minus operation
                if (food.getCount() == 0) { // last this type of food
                    map.remove(id);
                    System.out.println(thread.getName() + " remove id " + id);
                }
                res = food;
            }
        }
        // mMutex = null; // must be put on the end of this function !!! to ensure all operations before this sentence is done together
        System.out.println(thread.getName()+ "after get food, release mutex");
        return res;
    }

    public void addFood(Food food, Thread thread, Object mutex) {
        if ((mutex != null) && (mutex == mMutex) && (food.getCount() == 0)) {
            int id = food.getId();
            if (map.containsKey(id)) {
                map.get(id).addOne();
                System.out.println(thread.getName() + " after add " + + food.getId()+ "'s count:" +food.getCount());
            } else {
                map.put(id, food);
                food.addOne();
                System.out.println(thread.getName() + " put new food " + + food.getId()+ "'s count:" +food.getCount());
            }
        }
        mMutex = null; // must be put on the end of this function !!! to ensure all operations before this sentence is done together
        System.out.println(thread.getName()+ "after add food, release mutex");
    }


    public synchronized Food getFood(Integer id, Thread thread) {
        Food res = null;
        if (map.containsKey(id)) {
            // step 1: get
            Food food = map.get(id);
            System.out.println(thread.getName() + " get " + + food.getId()+ "'s count:" + food.getCount());
            // step 2: minus
            food.minusOne();
            System.out.println(thread.getName() + " after minus " + food.getId()+ "'s count:" + food.getCount());
            // step 3: remove should be in the same unit with minus operation
            if (food.getCount() == 0) { // last this type of food
                map.remove(id);
                System.out.println(thread.getName() + " remove id " + id);
            }
            res = food;
        }
        // mMutex = null; // must be put on the end of this function !!! to ensure all operations before this sentence is done together
        System.out.println(thread.getName()+ "after get food, release mutex");
        return res;
    }

    public synchronized void addFood(Food food, Thread thread) {
        int id = food.getId();
        if (map.containsKey(id)) {
            map.get(id).addOne();
            System.out.println(thread.getName() + " after add " + + food.getId()+ "'s count:" +food.getCount());
        } else {
            map.put(id, food);
            food.addOne();
            System.out.println(thread.getName() + " put new food " + + food.getId()+ "'s count:" +food.getCount());
        }
        System.out.println(thread.getName()+ "after add food, release mutex");
    }

    public synchronized int readCount(Integer id) {
        if (map.containsKey(id)) {
            return map.get(id).getCount();
        }
        return 0;
    }
}

