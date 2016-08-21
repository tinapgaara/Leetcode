package google.design.vendingMachine;

import google.design.threadSafe.ClassForTest;

/**
 * Created by yingtan on 11/26/15.
 */
public class AddProcess extends Thread {

    private Machine mMachine;
    private Food mFood;
    private int maxNUM;

    public AddProcess(Machine machine, Food food) {
        mMachine = machine;
        mFood = food;
        maxNUM = 10;
    }

    public void run() {

        while (true) {
            // food is enough
            while (mMachine.readCount(mFood.getId()) > maxNUM) {
                try {
                    long waitTimeInMillis = 10000;
                    System.out.println(this.getName() + " add process wait");
                    sleep(waitTimeInMillis);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
            mMachine.addFood(mFood, this);
        }
    }
}
