package OOdesign.valetParking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 4/8/18.
 */
public class ParkingLot {
    private int id;
    private List<ParkingSlot> parkSlots;
    private static ParkingLot mInstace;

    private ParkingLot(int id) {
        this.id = id;
        parkSlots = new ArrayList<>();
        buildParkingSlots();
    }
    protected List<ParkingSlot> getParkSlots() {
        return parkSlots;
    }
    private void buildParkingSlots() {

    }
    public static ParkingLot getInstance(int id) {
        if (mInstace == null) {
            mInstace = new ParkingLot(id);
        }
        return mInstace;
    }

    public static void main(String[] args) {
        ParkingLot lot = getInstance(1);// 1st parking lot
        ParkingManager manager = new ParkingManager(1, lot);// assign this manager this parking lot
        Vehicle car1 = new Car();
        Vehicle bus1 = new Bus();
        ParkingTicket ticket1 = manager.parkIn(car1);
        manager.parkIn(bus1);
        manager.moveOut(ticket1);
    }

}
