package bloomberg.design.testCar;

/**
 * Created by yingtan on 11/16/15.
 */
public class Main {

    public static void main(String[] args) {
        ICar carA = new CarA();
        ICar carB = new CarB();

        ITester testerA = carA.createTester();
        ITester testerB = carB.createTester();

        testerA.test(carA);
        testerB.test(carB);
    }
}
