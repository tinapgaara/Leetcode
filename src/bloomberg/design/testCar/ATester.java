package bloomberg.design.testCar;

/**
 * Created by yingtan on 11/16/15.
 */
public class ATester implements ITester {

    @Override
    public void test(ICar car) {

        testTurnLeft(car);
        testTurnRight(car);
    }

    public void testTurnLeft(ICar car) {
        System.out.println("Test turn left");
    }

    public void testTurnRight(ICar car) {
        System.out.println("Test turn right");
    }

}
