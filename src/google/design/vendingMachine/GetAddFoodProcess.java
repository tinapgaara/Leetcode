package google.design.vendingMachine;

/**
 * Created by yingtan on 11/26/15.
 */
public class GetAddFoodProcess extends Thread {

    private Machine mMachine;
    private Food mFood;

    public GetAddFoodProcess(Machine machine, Food food) {
        mMachine = machine;
        mFood = food;
    }

    public void run() {

        Object mutex = null;
        while (true) {
            while ((mutex = mMachine.getMutex()) == null) { // been occupied
                try {
                    long waitTimeInMillis = 10;
                    System.out.println(this.getName() + "wait");
                    sleep(waitTimeInMillis);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            Food food = mMachine.getFood(mFood.getId(), this, mutex);

            if (food == null) {
                food = mFood;
            }
            mMachine.addFood(food, this, mutex);
            /*
            if (food == null) {
                mMachine.addFood(mFood, this, mutex);
            } else if (food.getCount() == 0) {
                mMachine.addFood(food, this, mutex);
            }

            mMachine.releaseMutex(this); // why not  ?????
            */
        }
    }

    public static void main(String[] args) {
        Machine machine = new Machine();
        Food food1 = new Food(1, "h", 12.3);
        Food food2 = new Food(2, "w", 2.5);

        final int numThreads = 8;

        GetAddFoodProcess[] threads = new GetAddFoodProcess[numThreads];

        for (int i = 0; i < numThreads / 2; i++) {
            threads[i] = new GetAddFoodProcess(machine, food1);
        }
        for (int i = numThreads / 2; i < numThreads; i++) {
            threads[i] = new GetAddFoodProcess(machine, food2);
        }
        for (int i = 0; i < numThreads; i++) {
            threads[i].start();
        }
    }

}
