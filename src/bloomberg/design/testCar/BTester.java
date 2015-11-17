package bloomberg.design.testCar;

/**
 * Created by yingtan on 11/16/15.
 */
public class BTester implements ITester {

    @Override
    public void test(ICar car) {

        testSpeedUp(car);
        testSpeedDown(car);
    }

    public void testSpeedUp(ICar car) {
        System.out.println("Test speed up");
    }

    public void testSpeedDown(ICar car) {
        System.out.println("Test speed down");
    }
}
