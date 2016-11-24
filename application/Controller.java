package application;

import javax.swing.*;

import database.*;
import guis.*;
import objects.*;

public class Controller
{
	private JFrame frame;
	private User user;
	private UserDBManager userDBManager = new UserDBManager();

	public Controller(JFrame frame)
	{

		this.frame = frame;
		this.displayGUIWelcome();
		frame.setVisible(true);
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
		// GUIMainMenu mainMenu = new GUIMainMenu(this, user);  //passes the frame, controller, and user object to the menu
		frame.setContentPane(new GUIMainMenu(this, user));
		frame.revalidate();
	}

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

	public void displayGUIStats()
	{
		frame.setContentPane(new GUIStats(this, user));
		frame.revalidate();
	}

	public void displayError(String e)
	{
		JOptionPane.showMessageDialog(frame, e);
	}
	/*
   public void displayCreateUserError()
   {

	   JOptionPane.showMessageDialog(frame, "Username already exists.");

   }

   public void displayLoginError()
   {

	   JOptionPane.showMessageDialog(frame, "Incorrect Username/Password combination.");

   }
	 */
	public void displayGUIWalk()
	{

		frame.setContentPane(new GUIWalk(this));
		frame.revalidate();

	}
	public void displayGUIMaps()
	{
		frame.setContentPane(new GUIMaps(this));
		frame.revalidate();
	}
	/*
   public void displayGUIRestric()
   {
      GUIRestric restric = new GUIRestric();
   }
	 */
	/*
   public void displayGUIReport()
   {
      GUIReport report = new GUIReport();
   }
	 */
	/*
   public void displayGUIViolate()
   {
      GUIViolate violate = new GUIViolate();
   }
	 */

	public void displayGUIManage()
	{
		frame.setContentPane(new GUIManage(this, frame));
		frame.revalidate();
	}

	public User getUser(String userName)
	{
		return userDBManager.getUser(userName);
	}

	public void updatePermissions(String userName, int status)
	{
		boolean toggle = userDBManager.updatePermissions(userName, status);
	}

	public void logOut()
	{
		user = null;
		displayGUIWelcome();
	}
}