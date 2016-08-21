package google.design.vendingMachine;

/**
 * Created by yingtan on 11/26/15.
 */
public class GetProcess extends Thread {

    private Machine mMachine;
    private Food mFood;

    public GetProcess(Machine machine, Food food) {
        mMachine = machine;
        mFood = food;
    }

    public void run() {

        while (true) {
            // no such food
            while (mMachine.getFood(mFood.getId(), this) == null) {
                try {
                    long waitTimeInMillis = 1000;
                    System.out.println(this.getName() + " get process wait");
                    sleep(waitTimeInMillis);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

}
