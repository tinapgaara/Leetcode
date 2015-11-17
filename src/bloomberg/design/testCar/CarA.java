package bloomberg.design.testCar;

/**
 * Created by yingtan on 11/16/15.
 */
public class CarA implements ICar {

    @Override
    public ITester createTester() {
        return new ATester();
    }

}
