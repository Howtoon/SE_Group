package objects;

import java.util.Date;

public class ParkingLot {

	private String lotID;

	private int total = 0;
    private int occupied = 0;
    private int available = 0;
    private boolean isOpen = true;
    // Image map
    // Violation object?

	private int reserved = 0;
	private int handicapped = 0;
	private int commuter = 0;
	private int resident = 0;
	private int staff = 0;
	private int visitor = 0;
	private int motorcycle = 0;

	private Date recordDate;


    public ParkingLot() {
        this.lotID = "";
        recordDate = new Date();
    }

    /** Admin/Super - update Lot constructor*/
    public ParkingLot (String ID, int numCars) {
        super();
        this.lotID = ID;
        this.occupied = numCars;
        recordDate = new Date();
    }

    /** Supervisor - add Lot constructor */
	public ParkingLot (String ID, int total, int reserved, int handicapped, int commuter,
					   int resident, int staff, int visitor, int motorcycle, boolean isOpen) {
		super();
		this.lotID = ID;
		this.total = total;
		this.available = total;
		this.reserved = reserved;
		this.handicapped = handicapped;
		this.commuter = commuter;
		this.resident = resident;
		this.staff = staff;
		this.visitor = visitor;
		this.motorcycle = motorcycle;
        recordDate = new Date();
	}

	public String getLotID() { return lotID; }
	public void setLotID(String id) { this.lotID = id; }
	public int getVisitor() {
		return visitor;
	}
	public void setVisitor(int visitor) {
		this.visitor = visitor;
	}
	public int getMotorcycle() {
		return motorcycle;
	}
	public void setMotorcycle(int motorcycle) {
		this.motorcycle = motorcycle;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getReserved() {
		return reserved;
	}
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
	public int getHandicapped() {
		return handicapped;
	}
	public void setHandicapped(int handicapped) {
		this.handicapped = handicapped;
	}
	public int getCommuter() {
		return commuter;
	}
	public void setCommuter(int commuter) {
		this.commuter = commuter;
	}
	public int getResident() {
		return resident;
	}
	public void setResident(int resident) {
		this.resident = resident;
	}
	public int getStaff() {
		return staff;
	}
	public void setStaff(int staff) {
		this.staff = staff;
	}

	public int getOccupied() {
		return occupied;
	}

	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean open) {
		isOpen = open;
	}

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}
