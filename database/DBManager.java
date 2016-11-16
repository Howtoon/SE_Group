package database;

import guis.*;
//import guis.User;
//import guis.UserPermissions;
//import guis.ParkingLot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.SQLException;
/**
 * Created by julienpugh on 11/14/16.
 */
public class DBManager {

    /**
     * The main method takes in two String arguments: the db properties and the contacts text file
     * A mailing list is created and queried.
     * (TEST METHOD ONLY, DELETE LATER)
     */
    public static void main (String[] args) throws Exception {

        if (args.length == 0)
            System.out.println("Usage: java -classpath derby.jar;. MailingList database.properties");

        DBManager dbm = new DBManager();

        dbm.createTables();

        User sv = new User("supervisor", "123456");
        sv.setPermissions(UserPermissions.SUPERVISOR);
        dbm.addUser(sv);

        User userToReturn = dbm.showUser("123456");
        System.out.println("Here are the values for userToReturn:" + userToReturn.getName() +
                userToReturn.getUserID() + userToReturn.getPermissions());

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

    //private Scanner fileToRead;

    /**
     * Default constructor that reads the properties file and initializes access to the database
     * Also enables SQL statements to be issued.
     */
    public DBManager () throws Exception {

        SimpleDataSource.init("database.properties");
        conn = SimpleDataSource.getConnection();
        stat = conn.createStatement();
    }

    /**
     * Method used to execute commands.
     * (CREATE, DROP, UPDATE, DELETE, etc)
     * @param c statement to send
     */
    public void executeCommand(String c) {

        try
        {
            stat.execute(c);
            System.out.println("executed command");
        }
        catch (SQLException s)
        {   System.out.println("sql exception in addContacts"); }
    }

    /**
     * Method used to execute queries.
     * (SELECT FROM)
     * @param q query to send
     */
    public void executeQuery(String q) {

    }

    /**
     * Method used to check user password in User table
     * and return if the user exists.
     * @return whether or not the user can view the app.
     */
    public boolean validateUserPass () {

        return true;
    }

    /**
     * Method used to check lot ID in Parking Lot table
     * and returns whether that lot exists.
     * @return whether or not the lot information can be displayed.
     */
    public boolean validateLotID () {

        return true;
    }

    /**
     * Method used to create a Lot object
     * from information in the Parking Lot table
     */
    public ParkingLot createLot () {

        return null;
    }

    /**
     * Method used to create a User object
     * from information in the User table.
     * @return user
     */
    public User createUser () {

        return null;
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

    /*
     * Method used to read a file.
     * @param fileName name of the file
     * @return file to read
     */
    /*public void readFile (String fileName) {

        try { this.fileToRead.close(); }
        catch (Exception e)
        {
            System.out.println("no file to close");
        }

        try
        {
            this.fileToRead = new Scanner(new FileInputStream(fileName));
            this.fileToRead.useDelimiter("\n");
        }
        catch (FileNotFoundException f)
        {
            System.out.println("no file found");
        }
    }*/

    /*
     * Method used to write to a file.
     * @param fileName name of the file
     * @return file to read
     */
    //public void writeFile (String fileName) throws FileNotFoundException { }

    /**
     * Method used to create the User and Lot tables.
     * Always checks to makes sure the tables do not
     * exist before creating them.
     */
    public void createTables () {

        try
        {
            try (ResultSet res = this.conn.getMetaData().getTables(null, null, "User", null))
            {
                while (res.next())
                {
                    String table = res.getString("TABLE_NAME");
                    if (table != null && table.equals("User"))
                        return;
                }
            }
            stat.execute("CREATE TABLE User (User_Name VARCHAR(20)," +
                " User_Pass VARCHAR(20), Permissions VARCHAR(15))");
        }
        catch (SQLException s)
        {   System.out.println("sql exception in creating user table"); }

        try
        {
            try (ResultSet res = this.conn.getMetaData().getTables(null, null, "Lot", null))
            {
                while (res.next())
                {
                    String table = res.getString("TABLE_NAME");
                    if (table != null && table.equals("Lot"))
                        return;
                }
            }
            stat.execute("CREATE TABLE Lot (ID VARCHAR(3))");
        }
        catch (SQLException s)
        {   System.out.println("sql exception in creating lot table"); }
    }

    /**
     * Method used to add to the User table
     */
    public void addUser (User u) {

        String query = "INSERT INTO User VALUES (?, ?, ?)";
        try
        {
            conn.setAutoCommit(false);                                      // disable automatic SQL statements for now

            pStat = conn.prepareStatement(query);                                              // prepare the statement
            pStat.setString(1, u.getName());
            pStat.setString(2, u.getUserID());
            pStat.setString(3, u.getPermissions().toString());

            pStat.executeUpdate();                                                      // update the statement
            conn.commit();                                                              // and send it to the table

            conn.setAutoCommit(true);                                               // turn on automatic SQL statements
            System.out.println("executed command");
        }
        catch (SQLException s)
        {   System.out.println("sql exception in addUser"); }
    }

    /**
     * For now this method is mainly to test whether our user table is being updated.
     * @param uID user pass to search for user with
     * @return User object with information
     */
    public User showUser (String uID) {

        User userToReturn = null;
        String query = "SELECT * FROM User WHERE User_Pass = ?";
        try
        {
            pStat = conn.prepareStatement(query);
            pStat.setString(1, uID);
            result = pStat.executeQuery();                  // result from selecting all where username = uName
            rsm = result.getMetaData();
            int cols = rsm.getColumnCount();

            System.out.println("showing all info for user \n");
            while (result.next())
            {
                for (int i = 1; i <= cols; i++)
                {
                    System.out.printf(" %10.15s ", result.getString(i));
                    switch (i) {
                        case 1: userToReturn.setName(result.getString(i));
                            break;
                        case 2: userToReturn.setUserID(result.getString(i));
                            break;
                        case 3: userToReturn.getPermissions().setpString(result.getString(i));
                    }
                }
                System.out.print("\n");
            }
            System.out.println("\ncompleted query\n");
        }
        catch (Exception e)
        {   System.out.println("exception in showUser"); }

            return userToReturn;

    }
    /*public void addUser (String q) {

        try {
            stat.execute(q);
        }
        catch (SQLException s)
        {
            System.out.println("unable to add user");
        }
    }*/
}

