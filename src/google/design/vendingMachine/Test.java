package google.design.vendingMachine;

/**
 * Created by yingtan on 11/26/15.
 */
public class Test {

    public static void main(String[] args) {
        Machine machine = new Machine();
        Food food1 = new Food(1, "h", 12.3);
        Food food2 = new Food(2, "w", 2.5);

        final int numThreads = 8;

        GetProcess[] getthreads = new GetProcess[numThreads];
        AddProcess[] addthreads = new AddProcess[numThreads];

        for (int i = 0; i < numThreads / 2; i++) {
            getthreads[i] = new GetProcess(machine, food1);
        }
        for (int i = numThreads / 2; i < numThreads; i++) {
            getthreads[i] = new GetProcess(machine, food2);
        }

        for (int i = 0; i < numThreads / 2; i++) {
            addthreads[i] = new AddProcess(machine, food1);
        }
        for (int i = numThreads / 2; i < numThreads; i++) {
            addthreads[i] = new AddProcess(machine, food2);
        }


        for (int i = 0; i < numThreads; i++) {
            getthreads[i].start();
            addthreads[i].start();
        }
    }
}
