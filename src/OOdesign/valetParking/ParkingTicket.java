package OOdesign.valetParking;

/**
 * Created by yingtan on 4/8/18.
 */
public class ParkingTicket {
    private int id;
    private long enterTime;
    private long exitTime;
    private Vehicle vehicle;
    private ParkingSlot slot;

    public ParkingTicket(int id, long enterTime, Vehicle vehicle, ParkingSlot slot) {
        this.id = id;
        this.enterTime = enterTime;
        this.vehicle = vehicle;
        this.slot = slot;
    }
    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }
    public int getId() {
        return id;
    }
    public ParkingSlot getSlot() {
        return slot;
    }
}
