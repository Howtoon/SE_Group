package database;

import objects.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
/**
 * Created by julienpugh on 11/14/16.
 */
public class LotDBManager {

    /**
     * The main method takes in two String arguments: the db properties and the contacts text file
     * A mailing list is created and queried.
     * (TEST METHOD ONLY, DELETE LATER)
     */
    /*public static void main (String[] args) throws Exception {

        LotDBManager dbm = new LotDBManager();
        ParkingLot a = new ParkingLot("A", 90, 3, 5,
                7, 8, 9, 10, 11, true);

        System.out.printf("Here are Lot A's statistics: %s, total - %d, visitor - %d, commuter - %d, %b, %s\n",
                a.getLotID(), a.getTotal(), a.getVisitor(), a.getCommuter(), a.isOpen(), a.getRecordDate());
        ParkingLot ab = new ParkingLot("E", 4);

        dbm.addLot(a);
        dbm.getLot("E");
        ParkingLot newA = dbm.getLot("A");

        System.out.printf("Here are NEW Lot A's statistics: %s, total - %d, visitor - %d, commuter - %d, %b, %s\n",
                newA.getLotID(), newA.getTotal(), newA.getVisitor(), newA.getCommuter(), newA.isOpen(), newA.getRecordDate());
    }*/

    /** Used to access database */
    private Connection conn;

    /** Used to issue general SQL statements */
    private Statement stat;

    /** Used for specific SQL statements */
    private PreparedStatement pStat;

    /** Used to store the results from a query */
    private ResultSet result;

    /** Used to store properties of our results */
    private ResultSetMetaData rsm;

    /**
     * Default constructor that reads the properties file and initializes access to the database
     * Also enables SQL statements to be issued.
     */
    public LotDBManager () {

        try {
            SimpleDataSource.init("database/database.properties");
            this.openConnection();
            this.stat = this.conn.createStatement();
            this.createTables(0);
        } catch (Exception e) {
            System.out.println("exception in creating user db manager");
        }
    }

    /**
     * Method used show to open the connection
     */
    public void openConnection () {

        try
        { conn = SimpleDataSource.getConnection(); }
        catch (Exception e)
        { System.out.println ("connection already open"); }
    }

    /**
     * Method used to create the Lot tables.
     * 0 - Lots, 1 - Violations, 2 - Walking Times, other - Map
     * Always checks to makes sure the tables do not
     * exist before creating them.
     * @param tableToCreate determines what table to create
     */
    public void createTables (int tableToCreate) {

        try
        {
            DatabaseMetaData meta = conn.getMetaData();
            result = meta.getTables(null, null, "%", null);
            while(this.result.next())
                switch (tableToCreate)
                {
                    case 0:
                        if (result.getString("TABLE_NAME").equals("LOT"))
                            return;
                    case 1:
                        if (result.getString("TABLE_NAME").equals("VIOLATION"))
                            return;
                    case 2:
                        if (result.getString("TABLE_NAME").equals("WALK_TIME"))
                            return;
                    default:
                        if (result.getString("TABLE_NAME").equals("MAP"))
                            return;
                }
            switch (tableToCreate)
            {
                case 0:
                    stat.execute("CREATE TABLE Lot (Lot_ID VARCHAR(3), Total INTEGER, Available INTEGER, " +
                            "Occupied INTEGER, Reserved INTEGER, Handicapped INTEGER, Commuter INTEGER, " +
                            "Resident INTEGER, Staff INTEGER, Visitor INTEGER, Motorcycle INTEGER, " +
                            "Status VARCHAR(10), Time TIMESTAMP)");
                    System.out.println("Lot table created");
                    break;
                case 1:
                    stat.execute("CREATE TABLE Violation (Lot_ID VARCHAR2(3), Summary VARCHAR(100), " +
                            "Violation_ID VARCHAR(10), Time TIMESTAMP");
                    System.out.println("Violation table created");
                    break;
                case 2:
                    stat.execute("CREATE TABLE Walk_Time (Lot_ID VARCHAR(3), Village_East INTEGER, CEPS INTEGER, " +
                            "HLES INTEGER, Bldg_58 INTEGER, Martin INTEGER, CFPA INTEGER, COB INTEGER, " +
                            "Bldg_10 INTEGER, Bldg_18 INTEGER, Library INTEGER, Commons INTEGER)");
                    System.out.println("Walking Times table created");
                    break;
                default:
                    stat.execute("CREATE TABLE Map (Lot_ID VARCHAR(3), Map_Location VARCHAR(50)");
                    System.out.println("Map table created");
            }
        }
        catch (SQLException s)
        {   System.out.println("sql exception in creating lot tables"); }

    }

    /**
     * Method used to add to the Lot table.
     * (SEND OVER SUPERVISOR-CREATED OBJECT)
     * (Call once per lot, use updateLot for updates)
     * @param p lot to add
     */
    public void addLot (ParkingLot p) {
        String query = "INSERT INTO Lot VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            conn.setAutoCommit(false);                                      // disable automatic SQL statements for now

            pStat = conn.prepareStatement(query);                  // prepare the statement
            pStat.setString(1, p.getLotID());
            pStat.setInt(2, p.getTotal());
            pStat.setInt(3, p.getAvailable());
            pStat.setInt(4, p.getOccupied());
            pStat.setInt(5, p.getReserved());
            pStat.setInt(6, p.getHandicapped());
            pStat.setInt(7, p.getCommuter());
            pStat.setInt(8, p.getResident());
            pStat.setInt(9, p.getStaff());
            pStat.setInt(10, p.getVisitor());
            pStat.setInt(11, p.getMotorcycle());
            if (p.isOpen())
                pStat.setString(12, "open");
            else
                pStat.setString(12, "close");

            pStat.setTimestamp(13, new Timestamp(p.getRecordDate().getTime()));

            pStat.executeUpdate();                                                      // update the statement
            conn.commit();                                                              // and send it to the table

            conn.setAutoCommit(true);                                               // turn on automatic SQL statements
            System.out.println("executed command");
        }
        catch (SQLException s)
        {
            System.out.println("sql exception in addUser");
        }
    }

    /**
     * Method used to create a ParkingLot object.
     * A lot is retrieved through lotID
     * and latest timestamp (date and time).
     * Will also check to see if lot exists
     * and return false if it doesn't.
     * @param lotID the name of the lot to find
     * @return whether or not the user can see a lot's info
     */
    public ParkingLot getLot (String lotID) {

        ParkingLot lotToReturn = new ParkingLot();
        String query = "SELECT * FROM Lot" +
                " WHERE Lot_ID = ?" +
                " AND Time IN (SELECT MAX(Time) FROM Lot)";
        // " ORDER BY Time DESC"
        try
        {
            conn.setAutoCommit(true);                                               // turn on automatic SQL statements

            pStat = conn.prepareStatement(query);
            pStat.setString(1, lotID);
            result = pStat.executeQuery();
            rsm = result.getMetaData();
            //int cols = rsm.getColumnCount();
            if (!result.next())
            {
                System.out.println("lot does not exist");
                return null;
            }
            while (result.next())
            {
                lotToReturn.setLotID(result.getString(1));
                lotToReturn.setTotal(result.getInt(2));
                lotToReturn.setAvailable(result.getInt(3));
                lotToReturn.setOccupied(result.getInt(4));
                lotToReturn.setReserved(result.getInt(5));
                lotToReturn.setHandicapped(result.getInt(6));
                lotToReturn.setCommuter(result.getInt(7));
                lotToReturn.setResident(result.getInt(8));
                lotToReturn.setStaff(result.getInt(9));
                lotToReturn.setVisitor(result.getInt(10));
                lotToReturn.setMotorcycle(result.getInt(11));
                if (result.getString(12).equals("OPEN") || result.getString(12).equals("open"))
                    lotToReturn.setOpen(true);
                else
                    lotToReturn.setOpen(false);
                lotToReturn.setRecordDate(new Date(result.getTimestamp(13).getTime()));
            }
            System.out.println("\ncompleted query\n");
        }
        catch (Exception e)
        {
            System.out.println("lot doesn't exist");
            e.printStackTrace();
            return null;
        }

        return lotToReturn;
    }

    /**
     * Method used to update a lot's available spaces.
     * Creates a lot object from latest info
     * Applies it back after adjusting available
     * & occupied spaces. Updates the Timestamp.
     * Adds it back to the table.
     * Return false if lot doesn't exist.
     * @param  lotID name of lot to find
     * @param numCars number of cars to enter
     * @return ParkingLot object updated.
     */
    public ParkingLot updateLotCars (String lotID, int numCars) {

        ParkingLot tempLot = getLot(lotID);

        if (tempLot == null)
        {
            System.out.println("Lot does not exist");
            return null;
        }
        else
            {
                tempLot.setOccupied(numCars);
                tempLot.setAvailable(tempLot.getTotal() - numCars);
                tempLot.setRecordDate(new Date());
                addLot(tempLot);
            }
        return tempLot;
    }

    /**
     * Method used to update a lot's space information.
     * Use Strings to classify spaces
     * - "resident", "staff", "motorcycle", ...
     * Updates latest row.
     * Return false if lot doesn't exist.
     * @param  lotID name of lot to find
     * @param category type of space
     * @return ParkingLot object updated.
     */
    public ParkingLot updateLotSpaces (String lotID, String category, int numSpaces) {

        String query = "UPDATE Lot SET " + category + " = ? " +
                "WHERE Lot_ID = ? " +
                "AND Time = (SELECT max(Time) FROM Lot WHERE Lot_ID = ?";

        ParkingLot tempLot = getLot(lotID);

        try
        {
            conn.setAutoCommit(false);                                      // disable automatic SQL statements for now

            pStat = conn.prepareStatement(query);                  // prepare the statement
            pStat.setInt(1, numSpaces);
            pStat.setString(2, lotID);
            pStat.setString(3, lotID);

            pStat.executeUpdate();                                                      // update the statement
            conn.commit();                                                              // and send it to the table

            conn.setAutoCommit(true);                                               // turn on automatic SQL statements
            System.out.println("executed command");
        }
        catch (SQLException s)
        {
            System.out.println("lot does not exist");
            return null;
        }
        return tempLot;
    }

    /**
     * Method used to update a lot's availability
     * A new row is made and added with new time.
     * Will also check to see if lot exists
     * and return false if it doesn't.
     * @param lotID name of lot to find
     * @param  isOpen a lot's status (open/true or close/false)
     * @return ParkingLot object updated
     */
    public ParkingLot updateLotStatus (String lotID, boolean isOpen) {

        ParkingLot tempLot = getLot(lotID);

        if (tempLot == null)
        {
            System.out.println("Lot does not exist");
            return null;
        }
        else
        {
            tempLot.setOpen(isOpen);
            tempLot.setRecordDate(new Date());
            addLot(tempLot);
        }

        return tempLot;
    }

    /**
     * Method used show to close the connection
     */
    public void closeConnection () {

        try
        { conn.close(); }
        catch (Exception e)
        { System.out.println ("no connection open"); }
    }
}

