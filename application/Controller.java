package application;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridLayout;
import guis.*;


public class Controller
{
   private JFrame frame;
   private User user;
   
   public Controller(JFrame frame)
   {
	   
	  this.frame = frame;
	  this.displayGUIWelcome();
	  frame.setVisible(true);
	  
   }
   
   public void displayGUIWelcome()
   {

	   frame.setContentPane(new GUIWelcome(this));
	   frame.revalidate();
	   
   }

   public void displayGUILogin()//Called from GUIWelcome()
   {
	   
     // GUILogin login = new GUILogin(frame, this); //passes frame and controller to the login GUI
      frame.setContentPane(new GUILogin(this));
      frame.revalidate();
   
   }
   
   public boolean verifyLogin(String userName, String password)   //calls DBMgr() to verify username and password
   {
      user = new User();         //Placeholder for user object returned by DBMgr()
      return true;//returns true/false based on valid username and password combo
   }
   public boolean verifyDuplicate(String userName, String password)   //DBMgr() to check for duplicate
   {
      if (!false) //if the user does not already exist
      {
         user = new User();         //Placeholder for user object returned by DBMgr()
         //add new user to database
         return false;//returns true/false based on valid username and password combo
      }
      //if user already exists, return true
      return false;//Just in case for testing
   }
   public void displayGUICreateUser()  //called from GUILogin
   {
	   
      frame.setContentPane(new GUICreateUser(this));
      frame.revalidate();
   }
   
   public void createUser(User newUser)  //Calls DBMgr() to create a new user in the database
   {
      user = newUser;         //assigns instance variable to public variable for referencing
      displayGUIMainMenu();   //calls GUIMainMenu()
   }
   
   public void displayGUIMainMenu()//Called from GUILogin first, other GUIs can come back to the MainMenu
   {
     // GUIMainMenu mainMenu = new GUIMainMenu(this, user);  //passes the frame, controller, and user object to the menu
      frame.setContentPane(new GUIMainMenu(this, user));
      frame.revalidate();
   }
   /*
   public void displayGUIPortal()
   {
      GUIPortal portal = new GUIPortal();
   }
   */
   /*
   public void displayGUIRegs()
   {
      GUIRegs regs = new GUIRegs();
   }
   */
   /*
   public void displayGUIStats()
   {
      GUIStats stats = new GUIStats();
   }
   */
   /*
   public void displayGUIWalk()
   {
      GUIWalk walk = new GUIWalk();
   }
   */
   /*
   public void displayGUIMaps()
   {
      GUIMaps maps = new GUIMaps();
   }
   */
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
   /*
   public void displayGUIManage()
   {
      GUIManage manage = new GUIManage();
   }
   */
}