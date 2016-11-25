package application;

import javax.swing.*;

import database.*;
import guis.*;
import objects.User;
import objects.ParkingLot;

public class Controller
{
	private JFrame frame;
	private User user;
	private UserDBManager userDBManager;
	private LotDBManager lotDBManager;

	/**
	 * Parameterized constructor which sets up the frame when first opened
	 * @param frame
	 */
	public Controller(JFrame frame)
	{

		this.frame = frame;
		this.displayGUIWelcome();
		frame.setVisible(true);
		userDBManager = new UserDBManager();
		lotDBManager = new LotDBManager();
		//userDBManager.closeConnection()

	}

	/**
	 * Changes the application's main panel to the welcome page
	 */
	public void displayGUIWelcome()
	{

		frame.setContentPane(new GUIWelcome(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
		frame.revalidate();	/* Updates the frame to display the new panel that was added */

	}

	/**
	 * Changes the application's main panel to the login page
	 */
	public void displayGUILogin()//Called from GUIWelcome()
	{

		frame.setContentPane(new GUILogin(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
		frame.revalidate();	/* Updates the frame to display the new panel that was added */

	}

	/**
	 * Verifies the the credentials given by the user against the user database to see if one exists or not
	 * @param userName
	 * @param password
	 * @return true if the username and password combination was found and false otherwise
	 */
	public boolean verifyLogin(String userName, String password)   //calls DBMgr() to verify username and password 
	{           //Called by CreateNewUser()
		//userDBManager.openConnection()

		if (userDBManager.validateUserInfo(userName, password))
		{
			user = userDBManager.getUser(userName);
			return true;//returns true/false based on valid username and password combo
		}
		else
		{
			return false;
		}

	}

	/**
	 * Verifies if a username is already in use or not
	 * @param userName
	 * @param password
	 * @return true if there is no user already in the system that has that username and false otherwise
	 */
	public boolean verifyDuplicate(String userName, String password)   //DBMgr() to check for duplicate
	{
		user = new User(userName, password);
		if (userDBManager.addUser(user)) //if the user does not already exist
		{
			//add new user to database
			return true;//returns true/false based on valid username and password combo
		}
		//if user already exists, return true
		return false;//Just in case for testing
	}

	/**
	 * Changes the application's main panel to the create user page
	 */
	public void displayGUICreateUser()
	{

		frame.setContentPane(new GUICreateUser(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
		frame.revalidate();	/* Updates the frame to display the new panel that was added */

	}

	/**
	 * Creates a new user in the database
	 * @param newUser
	 */
	public void createUser(User newUser)  //Calls DBMgr() to create a new user in the database
	{

		user = newUser;         //assigns instance variable to public variable for referencing
		displayGUIMainMenu();   //calls GUIMainMenu()

	}

	/**
	 * Changes the application's main panel to the main menu page
	 */
	public void displayGUIMainMenu()//Called from GUILogin first, other GUIs can come back to the MainMenu
	{

		frame.setContentPane(new GUIMainMenu(this, user));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
		frame.revalidate();	/* Updates the frame to display the new panel that was added */

	}

	/**
	 * Changes the application's main panel to the regulation page
	 */
	public void displayGUIRegs()
	{
		try   //http://uwf.edu/media/university-of-west-florida/offices/trustees/regulations/UWF-REG-5-001-Parking-and-Registration-6.16.16.pdf
		{
			String URL = "http://uwf.edu/offices/business-auxiliary-services/parking-and-transportation/parking-regulations/";  
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
		}
		catch (Exception a)
		{
			JOptionPane.showMessageDialog(null,a.getMessage());
		}
	}

	/**
	 * Changes the application's main panel to the parking lot statistics page
	 */
	public void displayGUIStats()
	{

		frame.setContentPane(new GUIStats(this, user));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
		frame.revalidate();	/* Updates the frame to display the new panel that was added */

	}

	/**
	 * Pop ups an error dialog box with the given error message
	 * @param e the error message to be displayed
	 */
	public void displayError(String e)
	{

		JOptionPane.showMessageDialog(frame, e);

	}

	/**
	 * Changes the application's main panel to the walking times page
	 */
	public void displayGUIWalk()
	{

		frame.setContentPane(new GUIWalk(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
		frame.revalidate();	/* Updates the frame to display the new panel that was added */

	}

	public void displayGUIMaps()
	{

		frame.setContentPane(new GUIMaps(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
		frame.revalidate();	/* Updates the frame to display the new panel that was added */

	}
   
   public void displayGUIRestric()
   {
      GUIRestric restric = new GUIRestric();
   }
   
   public ParkingLot updateLotStatus (String lotID, boolean isOpen)
   {
      ParkingLot lot = lotDBManager.updateLotStatus(lotID, isOpen);
      return lot;
   }
	
	/**
	 * Changes the application's main panel to the parking lot report page
	 */
	public void displayGUIReport()
	{
		
		frame.setContentPane(new GUIReport(this));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
		frame.revalidate();	/* Updates the frame to display the new panel that was added */
		
	}
	/*
   public void displayGUIViolate()
   {
      GUIViolate violate = new GUIViolate();
   }
	 */

	/**
	 * Changes the application's main panel to the permissions management page
	 */
	public void displayGUIManage()
	{

		frame.setContentPane(new GUIManage(this, frame));	/* Sets the new panel in the JFrame. It will also overwrite any previous panels */
		frame.revalidate();	/* Updates the frame to display the new panel that was added */

	}

	/**
	 * Searches the database for a user based on a given user name and returns that user if found or null if not
	 * @param userName the username of the user to be searched
	 * @return the user object if the user is found or null if not
	 */
	public User getUser(String userName)
	{

		return userDBManager.getUser(userName);

	}

	/**
	 * Updates the permissions of a specific user
	 * @param userName the username of the specific user that will have their permissions updated
	 * @param status
	 */
	public void updatePermissions(String userName, int status)
	{
		boolean toggle = userDBManager.updatePermissions(userName, status);
	}

	/**
	 * Returns the parking lot object based on the lot id given or null if it does not exist in the database
	 * @param lotID
	 * @return the parking lot if it exists in the database or null if it does not
	 */
	public ParkingLot getLot(String lotID)
	{
		return lotDBManager.getLot(lotID);
	}
   
   public ParkingLot updateLotCars (String lotID, int numCars)
   {
      ParkingLot tempLot = lotDBManager.updateLotCars(lotID, numCars);
      return tempLot;
   }
	
	/**
	 * Logs out the current user and displays the welcome page
	 */
	public void logOut()
	{
		user = null;
		displayGUIWelcome();
	}

}