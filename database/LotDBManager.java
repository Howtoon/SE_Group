package database;

import guis.*;
//import guis.ParkingLot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.SQLException;
/**
 * Created by julienpugh on 11/14/16.
 */
public class LotDBManager {

    /**
     * The main method takes in two String arguments: the db properties and the contacts text file
     * A mailing list is created and queried.
     * (TEST METHOD ONLY, DELETE LATER)
     */
    public static void main (String[] args) throws Exception {

        if (args.length == 0)
            System.out.println("Usage: java -classpath database/derby.jar:. database/DBManager");

        LotDBManager dbm = new LotDBManager();

        //dbm.executeCommand("DROP TABLE Users");
        //dbm.executeCommand("DROP TABLE Lot");

        //dbm.createTables();


        User sv = new User("supervisor3", "12345678");
        sv.setPermissions(UserPermissions.SUPERVISOR);
        //dbm.addUser(sv);

        //User userToReturn = dbm.showUser("12345678");


        dbm.closeConnection();
    }

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
    public LotDBManager () throws Exception {

        SimpleDataSource.init("database/database.properties");
        openConnection();
        stat = conn.createStatement();
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
    public void createTable (int tableToCreate) {

        try
        {
            DatabaseMetaData meta = conn.getMetaData();
            result = meta.getTables(null, null, "%", null);
            while(result.next())
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
                    stat.execute("CREATE TABLE Lot (Lot_ID VARCHAR2(3), Total INTEGER, Available INTEGER, " +
                            "Occupied INTEGER, Reserved INTEGER, Handicap INTEGER, Commuter INTEGER, " +
                            "Resident INTEGER, Staff INTEGER, Visitor INTEGER, Motorcycle INTEGER, " +
                            "Time TIMESTAMP, Status VARCHAR2(10))");
                case 1:
                    stat.execute("CREATE TABLE Violation (Lot_ID VARCHAR2(3), Summary VARCHAR2(100), " +
                            "Violation_ID VARCHAR2(10), Time TIMESTAMP");
                case 2:
                    stat.execute("CREATE TABLE Walk_Time (Lot_ID VARCHAR2(3), Village_East INTEGER, CEPS INTEGER, " +
                            "HLES INTEGER, Bldg_58 INTEGER, Martin INTEGER, CFPA INTEGER, COB INTEGER, " +
                            "Bldg_10 INTEGER, Bldg_18 INTEGER, Library INTEGER, Commons INTEGER)");
                default:
                    stat.execute("CREATE TABLE Map (Lot_ID VARCHAR2(3), Map_Location VARCHAR2(50)");
            }
        }
        catch (SQLException s)
        {   System.out.println("sql exception in creating lot tables"); }


    }

    /**
     * Method used to add to the Lot table.
     * @param p lot to add
     */
    public void addLot (ParkingLot p) {

    }

    /**
     * Method used to create a ParkingLot object.
     * Will also check to see if lot exists
     * and return false if it doesn't.
     * @param lotID the name of the lot to find
     * @return whether or not the user can see a lot's info
     */
    public ParkingLot createLot (String lotID) {

        ParkingLot lotToReturn = null;

        return lotToReturn;
    }

    /**
     * Method used to update a lot's statistics.
     * Will also check to see if a lot exists
     * and return false if it doesn't.
     * @param  lotID name of lot to find
     * @param numCars number of cars to enter
     * @return whether or not the user has updated a lot
     */
    public boolean updateLot (String lotID, int numCars) {

        return true;
    }

    /**
     * Method used to add update lot's availability
     * Will also check to see if lot exists
     * and return false if it doesn't.
     * @param lotID name of lot to find
     * @param  isOpen a lot's status (open/true or close/false)
     * @return whether or not the user has updated a lot
     */
    public boolean updateLotStatus (String lotID, boolean isOpen) {

        return true;
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

