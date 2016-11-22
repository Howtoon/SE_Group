package database;

import objects.User;
import objects.UserPermissions;

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
public class UserDBManager {

    /**
     * The main method takes in two String arguments: the db properties and the contacts text file
     * A mailing list is created and queried.
     * (TEST METHOD ONLY, DELETE LATER)
     */
    /*public static void main (String[] args) throws Exception {

        if (args.length == 0)
            System.out.println("Usage: java -classpath database/derby.jar:. database/DBManager");

        UserDBManager dbm = new UserDBManager();

        //dbm.executeCommand("DROP TABLE Users");
        //dbm.executeCommand("DROP TABLE Lot");

        //dbm.createTables();


        User sv = new User("supervisor3", "12345678");
        sv.setPermissions(UserPermissions.SUPERVISOR);
        //dbm.addUser(sv);

        User userToReturn = dbm.showUser("12345678");
        System.out.println("Here are the values for userToReturn: " + userToReturn.getName() + " " +
                userToReturn.getUserID() + " " + userToReturn.getPermissions());

        dbm.closeConnection();
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
    public UserDBManager () {

        User s = new User("supervisor", "12345");
        s.setPermissions(UserPermissions.SUPERVISOR);
        try
        {
            SimpleDataSource.init("database/database.properties");
            openConnection();
            stat = conn.createStatement();
            createTables(0);
            createTables(1);
            addUser(s);
        }
        catch (Exception e)
        {
            System.out.println("exception in creating UserDBmanager()");
        }
    }

    /**
     * Method used show to open the connection
     */
    public void openConnection () {

        try
        {
            conn = SimpleDataSource.getConnection();
        }
        catch (Exception e)
        {
            System.out.println ("connection already open");
        }
    }

    /**
     * Method used to create the User tables.
     * 0 - User, other - Permit
     * Always checks to makes sure the tables do not
     * exist before creating them.
     */
    public void createTables (int tableToCreate) {

        try
        {
            conn.setAutoCommit(true);                                               // turn on automatic SQL statements

            DatabaseMetaData meta = conn.getMetaData();
            result = meta.getTables(null, null, "%", null);
            while(result.next())
            {
                System.out.println(result.getString("TABLE_NAME"));
                switch (tableToCreate)
                {
                    case 0:
                        //System.out.println("lets check to see if USERS exists");
                        if (result.getString("TABLE_NAME").equals("USERS"))
                        {
                           System.out.println("USERS exists");
                           return;
                        }
                        else continue;
                    default:
                        //System.out.println("lets check to see if PERMIT exists");
                        if (result.getString("TABLE_NAME").equals("PERMIT"))
                        {
                            System.out.println("PERMIT exists");
                            return;
                        }
                        else continue;
                }
            }
            switch (tableToCreate)
            {
                case 0:
                    stat.execute("CREATE TABLE Users (User_Name VARCHAR2(20), User_Pass VARCHAR2(20),"
                            + " Permit_ID VARCHAR2(10), Permissions VARCHAR2(15))");
                default:
                    stat.execute("CREATE TABLE Permit (Permit_ID VARCHAR2(10), " +
                            "Permit_TYPE VARCHAR2(20), Expiration DATE)");
            }
        }
        catch (SQLException s)
        {   System.out.println("sql exception in createTable"); }

    }

    /**
     * Method used to add to the User table.
     * Will also check to see if Username already exists
     * and return false if so.
     * @param u User to add
     * @return whether or not the user can create an account
     */
    public boolean addUser (User u) {

        String query = "INSERT INTO Users VALUES (?, ?, ?)";
        try
        {
            conn.setAutoCommit(false);                                      // disable automatic SQL statements for now

            pStat = conn.prepareStatement("SELECT 1 FROM Users WHERE User_Name = ?");
            pStat.setString(1, u.getName());

            boolean toAdd;
            toAdd = pStat.execute();
            conn.commit();

            if (toAdd)
            {
                System.out.println("Username exists");
                return false;
            }
            else
            {
                pStat = conn.prepareStatement(query);                  // prepare the statement
                pStat.setString(1, u.getName());
                pStat.setString(2, u.getUserID());
                pStat.setString(3, u.getPermissions().toString());
                // add permit info to permit table and permit id to both user and permit tables

                pStat.executeUpdate();                                                      // update the statement
                conn.commit();                                                              // and send it to the table

                conn.setAutoCommit(true);                                               // turn on automatic SQL statements
                System.out.println("executed command");
            }
        }
        catch (SQLException s)
        {
            System.out.println("sql exception in addUser");
        }
        return true;
    }

    /**
     * Method used to create a User object
     * from information in the User table.
     * @param uName username
     * @return user
     */
    public User createUser (String uName) {

        User userToReturn = new User();
        String query = "SELECT * FROM Users WHERE User_Name = ?";
        try
        {
            conn.setAutoCommit(true);                                               // turn on automatic SQL statements

            pStat = conn.prepareStatement(query);
            pStat.setString(1, uName);
            result = pStat.executeQuery();                  // result from selecting all where username = uName
            rsm = result.getMetaData();
            int cols = rsm.getColumnCount();

            while (result.next())                          // should always be ONE User!
                for (int i = 1; i <= cols; i++)
                    switch (i)
                    {
                        case 1:
                            userToReturn.setName(result.getString(i));
                            break;
                        case 2:
                            userToReturn.setUserID(result.getString(i));
                            break;
                        case 3:
                            userToReturn.setPermissions(UserPermissions.valueOf(result.getString(i)));
                            break;
                    }

            System.out.println("\ncompleted query\n");
        }
        catch (Exception e)
        {
            System.out.println("User does not exist");
            return null;
        }
        return userToReturn;
    }

    /**
     * Method used to check if the user exists.
     * @return whether or not the user can view the app.
     */
    public boolean validateUserInfo (String uName, String uID) {

        try
        {
            conn.setAutoCommit(false);                                      // disable automatic SQL statements for now

            pStat = conn.prepareStatement("SELECT 1 FROM Users WHERE User_Name = ?");
            PreparedStatement passCheck =
                    conn.prepareStatement("SELECT 1 FROM Users WHERE User_Pass = ?");

            pStat.setString(1, uName);
            passCheck.setString(1, uID);

            boolean uNameExists = pStat.execute();
            boolean uIDExists = passCheck.execute();

            conn.commit();

            if (!uNameExists)
            {
                System.out.println("User does not exist");
                return false;
            }
            else if (!uIDExists)
            {
                System.out.println("Incorrect password");
                return false;
            }
            else
                conn.setAutoCommit(true);                                               // turn on automatic SQL statements
        }
        catch (SQLException s)
        {
            System.out.println("sql exception in validateUserInfo");
        }
        return true;
    }

    /**
     * Method used by Supervisor to promote or demote User to/from Admin
     * @param uName username of User to promote/demote
     * @param status whether or not to promote (1) or demote (0)
     * @return if successful or not
     */
    public boolean updatePermissions (String uName, int status) {

        String query = "UPDATE Users " +
                "SET Permissions = ? " +
                "WHERE User_Name = ?";
        try
        {
            conn.setAutoCommit(false);                                      // disable automatic SQL statements for now

            if (status == 1)
            {
                pStat = conn.prepareStatement(query);                                              // prepare the statement
                pStat.setString(1, "ADMIN");
                pStat.setString(2, uName);
            }
            else
            {
                pStat = conn.prepareStatement(query);                                              // prepare the statement
                pStat.setString(1, "USER");
                pStat.setString(2, uName);
            }

            pStat.executeUpdate();                                                      // update the statement
            conn.commit();                                                              // and send it to the table

            conn.setAutoCommit(true);                                               // turn on automatic SQL statements
            System.out.println("executed command");
        }
        catch (SQLException s)
        {
            System.out.println("sql exception in updatePermissions");
            return false;
        }

        return true;
    }

    /**
     * For now this method is mainly to test whether our user table is being updated.
     * @param uID user pass to search for user with
     * @return User object with information
     */
    public User showUser (String uID) {

        User userToReturn = new User();
        String query = "SELECT * FROM Users WHERE User_Pass = ?";
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
                        case 1:
                            userToReturn.setName(result.getString(i));
                            break;
                        case 2:
                            userToReturn.setUserID(result.getString(i));
                            break;
                        //case 3:
                            //userToReturn.setPermit(result.getString(i));
                        case 4:
                            userToReturn.setPermissions(UserPermissions.valueOf(result.getString(i)));
                            break;
                    }
                }
                System.out.print("\n");
            }
            System.out.println("\ncompleted query\n");
        }
        catch (Exception e)
        {
            System.out.println("exception in showUser");
        }

        return userToReturn;
    }

    /**
     * Method used show to close the connection
     */
    public void closeConnection () {

        try
        {
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println ("no connection open");
        }
    }
}

