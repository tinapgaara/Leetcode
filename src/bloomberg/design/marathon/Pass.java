package bloomberg.design.marathon;

/**
 * Created by yingtan on 11/15/15.
 */
public class Pass {
    Sensor sensor;
    long timstamp;

    public Pass(long timstamp, Sensor sensor) {
        this.sensor = sensor;
        this.timstamp = timstamp;
    }

}
