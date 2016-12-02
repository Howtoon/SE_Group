package application;

import javax.swing.*;

import database.*;
import guis.*;
import objects.User;
import objects.ParkingLot;
//import java.io.BufferedReader;
//import java.io.FileReader;
/**
 * File Name: Controller.java
 * UWF Parking App
 *
 * This class handles the general functionality of the program.
 * Julien: One thing I would like to figure out on later iterations is how to
 * ensure the connections to the database are closed upon program termination.
 *
 * @author Nathan, Will
 * @version 1.0
 */
public class Controller
{
    /** window frame */
    private JFrame frame;

    /** temporary User object to modify */
    private User user;

    /** temporary ParkingLot object to modify */
    private ParkingLot lot;

    /** handles user-related database functionality */
    private UserDBManager userDBManager;

    /** handles parking-related database functionality */
    private LotDBManager lotDBManager;

    /**
     * Parameterized constructor which sets up the frame when first opened
     * @param frame
     */
    public Controller (JFrame frame)
    {
        this.frame = frame;
        this.displayGUIWelcome();
        frame.setVisible(true);
        userDBManager = new UserDBManager();
        lotDBManager = new LotDBManager();
    }

    /**
     * Changes the application's main panel to the welcome page
     */
    public void displayGUIWelcome ()
    {
        frame.setContentPane(new GUIWelcome(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
        frame.revalidate();	/* Updates the frame to display the new panel that was added */
    }

    /**
     * Changes the application's main panel to the login page
     */
    public void displayGUILogin ()      //Called from GUIWelcome()
    {
        userDBManager.openConnection();
        frame.setContentPane(new GUILogin(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
        frame.revalidate();	/* Updates the frame to display the new panel that was added */
    }

    /**
     * Verifies the the credentials given by the user against the user database to see if one exists or not
     * @param userName
     * @param password
     * @return true if the username and password combination was found and false otherwise
     */
    public boolean verifyLogin (String userName, String password)   //calls DBMgr() to verify username and password
    {                                                               //Called by CreateNewUser()
        if (userDBManager.validateUserInfo(userName, password))
        {
            user = userDBManager.getUser(userName);
            return true;//returns true/false based on valid username and password combo
        } else
            return false;
    }

    /**
     * Verifies if a username is already in use or not
     * @param userName
     * @param password
     * @return true if there is no user already in the system that has that username and false otherwise
     */
    public boolean verifyDuplicate (String userName, String password)   //DBMgr() to check for duplicate
    {
        user = new User(userName, password);
        if (userDBManager.addUser(user)) //if the user does not already exist, add new user to database
            return true;                 //returns true/false based on valid username and password combo
        //if user already exists, return true
        return false;//Just in case for testing
    }

    /**
     * Changes the application's main panel to the create user page
     */
    public void displayGUICreateUser ()
    {
        frame.setContentPane(new GUICreateUser(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
        frame.revalidate();	/* Updates the frame to display the new panel that was added */
    }

    /**
     * Creates a new user in the database
     * @param newUser
     */
    public void createUser (User newUser)  //Calls DBMgr() to create a new user in the database
    {
        user = newUser;         //assigns instance variable to public variable for referencing
        displayGUIMainMenu();   //calls GUIMainMenu()
    }

    /**
     * Changes the application's main panel to the main menu page
     */
    public void displayGUIMainMenu ()           //Called from GUILogin first, other GUIs can come back to the MainMenu
    {
        frame.setContentPane(new GUIMainMenu(this, user));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
        frame.revalidate();	/* Updates the frame to display the new panel that was added */
    }

    /**
     * Changes the application's main panel to the regulation page
     */
    public void displayGUIRegs ()
    {
        try //http://uwf.edu/media/university-of-west-florida/offices/trustees/regulations/UWF-REG-5-001-Parking-and-Registration-6.16.16.pdf
        {
            String URL = "http://uwf.edu/offices/business-auxiliary-services/parking-and-transportation/parking-regulations/";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
        }
        catch (Exception a)
        {
            JOptionPane.showMessageDialog(null, a.getMessage());
        }
    }

    /**
     * Changes the application's main panel to the parking lot statistics page
     */
    public void displayGUIStats ()
    {
        frame.setContentPane(new GUIStats(this, frame));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
        frame.revalidate();	/* Updates the frame to display the new panel that was added */
    }

    /**
     * Pop ups an error dialog box with the given error message
     * @param e the error message to be displayed
     */
    public void displayError (String e)
    {
        JOptionPane.showMessageDialog(frame, e);
    }

    /**
     * Changes the application's main panel to the walking times page
     */
    public void displayGUIWalk ()
    {
        frame.setContentPane(new GUIWalk(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
        frame.revalidate();	/* Updates the frame to display the new panel that was added */
    }

    /**
     * Changes the application's main panel to the maps page
     */
    public void displayGUIMaps ()
    {
        frame.setContentPane(new GUIMaps(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
        frame.revalidate();	/* Updates the frame to display the new panel that was added */
    }

    /**
     * Changes the application's main panel to the lot permissions page
     */
    public void displayGUIRestric ()
    {
        frame.setContentPane(new GUIRestric(this, frame));
        frame.revalidate();
    }

    /**
     * Updates the availability of the lot
     * @param lotID name of the lot
     * @param isOpen whether or not the lot is open or closed
     * @return updated ParkingLot object
     */
    public ParkingLot updateLotStatus (String lotID, boolean isOpen)
    {
        lot = lotDBManager.updateLotStatus(lotID, isOpen);
        return lot;
    }

    /**
     * Changes the application's main panel to the parking lot report page
     */
    public void displayGUIReport ()
    {
        frame.setContentPane(new GUIReport(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
        frame.revalidate();	/* Updates the frame to display the new panel that was added */
    }
    /*
   public void displayGUIViolate ()
   {
      GUIViolate violate = new GUIViolate();
   }
	 */

    /**
     * Changes the application's main panel to the user permissions management page
     */
    public void displayGUIManage ()
    {
        frame.setContentPane(new GUIManage(this, frame));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
        frame.revalidate();	/* Updates the frame to display the new panel that was added */
    }

    /**
     * Changes the application's main panel to the create parking lot page
     */
    public void displayGUIAddLot ()
    {
        frame.setContentPane(new GUIAddLot(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
        frame.revalidate();	/* Updates the frame to display the new panel that was added */
    }

    /**
     * Searches the database for a user based on a given user name and returns that user if found or null if not
     * @param userName the username of the user to be searched
     * @return the user object if the user is found or null if not
     */
    public User getUser (String userName)
    {
        return userDBManager.getUser(userName);
    }

    /**
     * Updates the permissions of a specific user
     * @param userName the username of the specific user that will have their permissions updated
     * @param status to promote or demote a user
     */
    public void updatePermissions (String userName, int status)
    {
        boolean toggle = userDBManager.updatePermissions(userName, status);
    }

    /**
     * Returns the parking lot object based on the lot id given or null if it does not exist in the database
     * @param lotID name of the lot
     * @return the parking lot if it exists in the database or null if it does not
     */
    public ParkingLot getLot (String lotID)
    {
        lot = lotDBManager.getLot(lotID);
        return lot;
    }

    /**
     * Updates the parking lot object based on the lot id, number of cars, and number of violations
     * @param lotID name of the lot
     * @param numCars total number of cars in that lot
     * @param violations total number of violations for that lot
     * @return the parking lot if it exists in the database or null if it does not
     */
    public ParkingLot updateLot (String lotID, int numCars, int violations)
    {
        ParkingLot tempLot = lotDBManager.updateLot(lotID, numCars, violations);
        return tempLot;
    }

    /**
     * Creates a parking lot and adds it to the database.
     * @param lotID name of the lot
     * @param total total number of spaces
     * @param reserved number of reserved spaces
     * @param handicap number of handicap spaces
     * @param commuter number of commuter spaces
     * @param resident number of resident spaces
     * @param staff number of faculty spaces
     * @param visitor number of visitor spaces
     * @param motorcycle number of motorcycle spaces
     * @param isOpen whether or not this lot is open or closed
     */
    public void createLot (String lotID, int total, int reserved, int handicap, int commuter, int resident,
                           int staff, int visitor, int motorcycle, boolean isOpen)
    {
        ParkingLot newLot = new ParkingLot(lotID, total, reserved, handicap, commuter,
                resident, staff, visitor, motorcycle, isOpen);
        lotDBManager.addLot(newLot);
    }

    /**
     * Updates the number of violations
     * @param lotID name of the lot
     * @param violations total number of violations for that lot
     * @return the parking lot if it exists in the database or null if it does not
     */
    public ParkingLot updateLotViolations (String lotID, int violations)
    {
        ParkingLot tempLot = lotDBManager.updateLotViolations(lotID, violations);
        return tempLot;
    }

    /**
     * Sets lot open.
     * @param open open
     */
    public void setOpen (boolean open)
    {
        lot.setOpen(open);
    }

    /**
     * Logs out the current user and displays the welcome page
     */
    public void logOut ()
    {
        user = null;
        displayGUIWelcome();
    }
}