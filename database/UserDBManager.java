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
 * Password encryption, among other things, will be implemented on the next iteration
 */
public class UserDBManager {

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
     * Creates the tables and supervisor, and adds the objects if they're not already there.
     */
    public UserDBManager ()
    {
        User s = new User("supervisor", "12345");
        s.setPermissions(UserPermissions.SUPERVISOR);

        try {
            SimpleDataSource.init("database/database.properties");
            this.openConnection();
            this.stat = this.conn.createStatement();
            this.createTables(0);
            //this.createTables(1);
            this.addUser(s);
            //dropTables();
        } catch (Exception e) {
            System.out.println("exception in creating user db manager");
            e.printStackTrace();
        }

    }

    /**
     * Method used show to open the connection
     */
    public void openConnection ()
    {
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
     * Method used drop the tables.
     */
    public void dropTables () {
        try
        {
            result.close();
            stat.execute("DROP TABLE Users");
            //stat.execute("DROP TABLE Permit");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Method used to create the User tables.
     * 0 - User, other - Permit
     * Always checks to makes sure the tables do not
     * exist before creating them.
     * @param tableToCreate the table specified for creation
     */
    public void createTables (int tableToCreate)
    {
        try
        {
            this.conn.setAutoCommit(true);
            DatabaseMetaData s = this.conn.getMetaData();
            this.result = s.getTables(null, null, "%", null);

            while(this.result.next())
            {
                switch(tableToCreate)
                {
                    case 0:
                        if(this.result.getString("TABLE_NAME").equals("USERS"))
                            return;
                        break;
                    default:
                        if(this.result.getString("TABLE_NAME").equals("PERMIT"))
                            return;
                }
            }
            switch(tableToCreate)
            {
                case 0:
                    this.stat.execute("CREATE TABLE Users (User_Name VARCHAR(20), " +
                            "User_Pass VARCHAR(20), Permit_ID VARCHAR(10), Permissions VARCHAR(15))");
                    break;
                default:
                    this.stat.execute("CREATE TABLE Permit (Permit_ID VARCHAR(10), " +
                            "Permit_TYPE VARCHAR(20), Expiration DATE)");
            }
        }
        catch (SQLException s)
        {
            //System.out.println("sql exception in creating users table");
            s.printStackTrace();
        }
    }

    /**
     * Method used to add to the User table.
     * Will also check to see if Username already exists
     * and return false if so.
     * @param u User to add
     * @return whether or not the user can create an account
     */
    public boolean addUser (User u)
    {
        String query = "INSERT INTO Users VALUES (?, ?, ?, ?)";
        try
        {
            //conn.setAutoCommit(false);
            //pStat = conn.prepareStatement("SELECT 1 FROM Users WHERE User_Name = ?");
            //pStat.setString(1, u.getName());
            //boolean toAdd;

            String query2 = String.format("SELECT * FROM Users WHERE User_Name = '%s'", u.getName());
            result = stat.executeQuery(query2);

            if (result.next())
            {
                System.out.println("Username exists");
                return false;
            }
            else
            {
                conn.setAutoCommit(false);
                pStat = conn.prepareStatement(query);                  // prepare the statement
                pStat.setString(1, u.getName());
                pStat.setString(2, u.getUserID());
                pStat.setString(3, u.getPermit().getId());
                pStat.setString(4, u.getPermissions().toString());

                pStat.executeUpdate();                                                      // update the statement
                conn.commit();                                                              // and send it to the table

                conn.setAutoCommit(true);                                           // turn on automatic SQL statements
                //System.out.println("executed command");
            }
        }
        catch (SQLException s)
        {
            System.out.println("sql exception in addUser");
            s.printStackTrace();
        }
        return true;
    }

    /**
     * Method used to create a User object
     * from information in the User table.
     * @param uName username
     * @return user
     */
    public User getUser (String uName)
    {
        User userToReturn = new User();
        try
        {
            int cols;
            String query = String.format("SELECT * FROM Users WHERE User_Name = '%s'", uName);

            conn.setAutoCommit(true);                                               // turn on automatic SQL statements

            result = stat.executeQuery(query);
            rsm = result.getMetaData();
            cols = rsm.getColumnCount();
            if (!result.next())
            {
                //System.out.println("User does not exist");
                return null;
            }
            result = stat.executeQuery(query);
            rsm = result.getMetaData();
            while (result.next())                          // should always be ONE User!
            {
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
                            userToReturn.getPermit().setId(result.getString(i));
                            break;
                        case 4:
                            userToReturn.setPermissions(UserPermissions.valueOf(result.getString(i)));
                            break;
                    }
            }
            //System.out.println("\ncompleted query\n");
        }
        catch (Exception e)
        {
            //System.out.println("User does not exist");
            return null;
        }
        return userToReturn;
    }

    /**
     * Method used to check if the user exists.
     * @param uName username
     * @param uID user password
     * @return whether or not the user can view the app.
     */
    public boolean validateUserInfo (String uName, String uID)
    {
        try
        {
            String query1 = String.format("SELECT 1 FROM Users WHERE User_Name = '%s'", uName);
            String query2 = String.format("SELECT 1 FROM Users WHERE User_Pass = '%s'", uID);

            result = stat.executeQuery(query1);
            if (!result.next())
            {
                //System.out.println("User does not exist");
                return false;
            }
            result = stat.executeQuery(query2);
            if (!result.next())
            {
                //System.out.println("Incorrect password");
                return false;
            }
            else
                conn.setAutoCommit(true);                                           // turn on automatic SQL statements
        }
        catch (SQLException s)
        {
            //System.out.println("sql exception in validate user info");
            s.printStackTrace();
        }
        //System.out.println("Logged in!");
        return true;
    }

    /**
     * Method used by Supervisor to promote or demote User to/from Admin
     * @param uName username of User to promote/demote
     * @param status whether or not to promote (1) or demote (0)
     * @return if successful or not
     */
    public boolean updatePermissions (String uName, int status)
    {
        String query = "UPDATE Users " +
                "SET Permissions = ? " +
                "WHERE User_Name = ?";
        try
        {
            conn.setAutoCommit(false);                                      // disable automatic SQL statements for now

            if (status == 1)
            {
                pStat = conn.prepareStatement(query);                                          // prepare the statement
                pStat.setString(1, "ADMIN");
                pStat.setString(2, uName);
            }
            else
            {
                pStat = conn.prepareStatement(query);                                          // prepare the statement
                pStat.setString(1, "USER");
                pStat.setString(2, uName);
            }

            pStat.executeUpdate();                                                      // update the statement
            conn.commit();                                                              // and send it to the table

            conn.setAutoCommit(true);                                               // turn on automatic SQL statements
            //System.out.println("executed command");
        }
        catch (SQLException s)
        {
            //System.out.println("sql exception in updatePermissions");
            return false;
        }
        return true;
    }

    /**
     * Method used show to close the connection
     */
    public void closeConnection ()
    {
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