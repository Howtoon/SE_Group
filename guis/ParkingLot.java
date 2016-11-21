package guis;

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


    public ParkingLot(String ID, int numCars) {
        super();
        this.occupied = numCars;
    }

	public ParkingLot(int total, int reserved, int handicapped, int commuter, int resident, int staff, int visitor,
			int motorcycle) {
		super();
		this.total = total;
		this.reserved = reserved;
		this.handicapped = handicapped;
		this.commuter = commuter;
		this.resident = resident;
		this.staff = staff;
		this.visitor = visitor;
		this.motorcycle = motorcycle;
	}

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
}
