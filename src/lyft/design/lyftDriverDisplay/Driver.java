package lyft.design.lyftDriverDisplay;

/**
 * Created by yingtan on 4/22/18.
 */
public class Driver {
    private long driverId;
    private double longtitude;
    private double latitude;
    private Car car;
    public double[] getLocation() {
        return new double[]{longtitude, latitude};
    }
    public Car getCar() {
        return car;
    }
}
