package objects;

import java.util.Date;
/**
 * File Name: ParkingLot.java
 * UWF Parking App
 *
 * This class represents the parking lot.
 *
 * @author Will, Julien
 * @version 1.0
 */
public class ParkingLot
{
    /** the lot's name */
	private String lotID;

    /** the total number of spaces */
	private int total = 0;

    /** the number of filled spaces */
	private int occupied = 0;

    /** the number of free spaces */
	private int available = 0;

	/** whether or not the lot is open or closed*/
	private boolean isOpen = true;

    /** the number of violations */
    private int violations = 0;

    /** the number of reserved spaces */
	private int reserved = 0;

    /** the number of handicapped spaces */
	private int handicapped = 0;

    /** the number of commuter spaces */
	private int commuter = 0;

    /** the number of resident spaces */
	private int resident = 0;

    /** the number of faculty spaces */
	private int staff = 0;

    /** the number of visitor spaces */
	private int visitor = 0;

    /** the number of motorcycle spaces */
	private int motorcycle = 0;

    /** the time and date at which the lot is recorded */
	private Date recordDate;

    /**
     * Constructor used to create blank lot.
     */
	public ParkingLot()
    {
		this.lotID = "";
		recordDate = new Date();
	}

    /** Admin/Super - update Lot constructor */
    public ParkingLot (String ID, int numCars, int vio)
    {
        super();
        this.lotID = ID;
        this.total = numCars;
        this.occupied = numCars;
        this.violations = vio;
        recordDate = new Date();
    }

	/** Supervisor - add Lot constructor */
	public ParkingLot (String ID, int total, int reserved, int handicapped, int commuter,
			int resident, int staff, int visitor, int motorcycle, boolean isOpen)
    {
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
		this.violations = 0;
		recordDate = new Date();
	}

    /** Returns the lot's name */
	public String getLotID()
   {
      return lotID;
   }

    /** Sets the lot's name */
	public void setLotID(String id)
   {
      this.lotID = id;
   }

    /** Returns the number of visitor spaces */
    public int getVisitor() {
		return visitor;
	}

    /** Sets the number of visitor spaces */
	public void setVisitor(int visitor) {
		this.visitor = visitor;
	}

    /** Returns the number of motorcycle spaces */
	public int getMotorcycle() {
		return motorcycle;
	}

    /** Sets the number of motorcycle spaces */
	public void setMotorcycle(int motorcycle) {
		this.motorcycle = motorcycle;
	}

    /** Returns the total number of spaces */
	public int getTotal() {
		return total;
	}

    /** Sets the total number of spaces */
	public void setTotal(int total) {
		this.total = total;
	}

    /** Returns the number of reserved spaces */
	public int getReserved() {
		return reserved;
	}

    /** Sets the number of reserved spaces */
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

    /** Returns the number of handicapped spaces */
	public int getHandicapped() {
		return handicapped;
	}

    /** Sets the number of handicapped spaces */
	public void setHandicapped(int handicapped) {
		this.handicapped = handicapped;
	}

    /** Returns the number of commuter spaces */
	public int getCommuter() {
		return commuter;
	}

    /** Sets the number of commuter spaces */
	public void setCommuter(int commuter) {
		this.commuter = commuter;
	}

    /** Returns the number of resident spaces */
	public int getResident() {
		return resident;
	}

    /** Sets the number of residents spaces */
	public void setResident(int resident) {
		this.resident = resident;
	}

    /** Returns the number of faculty spaces */
	public int getStaff() {
		return staff;
	}

    /** Sets the number of faculty spaces */
	public void setStaff(int staff) {
		this.staff = staff;
	}

    /** Returns the number of occupied spaces */
	public int getOccupied() {
		return occupied;
	}

    /** Sets the number of occupied spaces */
	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}

    /** Returns the number of available spaces */
	public int getAvailable() {
		return available;
	}

    /** Sets the number of available spaces */
	public void setAvailable(int available) {
		this.available = available;
	}

    /** Returns the status of the lot */
	public boolean isOpen() {
		return isOpen;
	}

    /** Sets the status of the lot */
	public void setOpen(boolean open) {
		isOpen = open;
	}

    /** Returns the number of violations */
    public int getViolations() {
        return violations;
    }

    /** Sets the number of violations */
    public void setViolations(int violations) {
        this.violations = violations;
    }

    /** Returns the date of the report */
    public Date getRecordDate() {
		return recordDate;
	}

    /** Sets the date of the report */
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
}
