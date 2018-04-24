package OOdesign.valetParking;

/**
 * Created by yingtan on 4/8/18.
 */
import java.util.*;
public class ParkingManager {
    private int id;
    private ParkingLot parkingLot;
    private List<ParkingTicket> parkingTickets;

    public ParkingManager(int id, ParkingLot parkingLot) {
        this.id = id;
        this.parkingLot = parkingLot;
        this.parkingTickets = new ArrayList<>();
    }

    public ParkingTicket parkIn(Vehicle vehicle) {
        ParkingSlot slot = this.findParkingSlot(vehicle.parkSize);
        if (slot != null) {
            return this.generateParkingTicket(vehicle, slot);
        }
        else {
            return null;
        }
    }
    public void moveOut(ParkingTicket ticket) {
        this.withDrawParkingTicket(ticket.getId());
    }

    protected ParkingSlot findParkingSlot(int vehicalSize) {
        for (ParkingSlot slot : parkingLot.getParkSlots()) {
            if (slot.available) {
                if (slot.size >= vehicalSize) {
                    return slot;
                }
            }
        }
        return null;
    }
    protected ParkingTicket generateParkingTicket(Vehicle vehicle, ParkingSlot slot) {
        int newticketId = parkingTickets.size();
        ParkingTicket ticket = new ParkingTicket(newticketId, System.currentTimeMillis(), vehicle, slot);
        parkingTickets.add(ticket);
        return ticket;
    }
    protected void withDrawParkingTicket(int parkingTicketId) {
        ParkingTicket ticket = parkingTickets.get(parkingTicketId);
        long exitTime = System.currentTimeMillis();
        ticket.setExitTime(exitTime);
        // release the slot
        ticket.getSlot().available = true;
    }
}
