package lyft.design.lyftDriverDisplay;

/**
 * Created by yingtan on 4/22/18.
 */
public class Car {
    private long carId;
    private String plateNumber;
    protected GenericCarType carType;
    public GenericCarType getCarType() {
        return carType;
    }

}
