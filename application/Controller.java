package application;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridLayout;
import guis.*;


public class Controller
{
   private JFrame frame;
   private User user;
   
   public void start()
   {
      frame = new JFrame("Parking Application");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new GUIWelcomeNew());
      //frame.getContentPane().setBackground(Color.WHITE);
      frame.setSize(512,512);
      //frame.setLayout(new GridLayout(0, 1));
      frame.setVisible(true);
      //GUIWelcome welcome = new GUIWelcome(frame, this);
   }
   
   public void displayGUILogin()//Called from GUIWelcome()
   {
      GUILogin login = new GUILogin(frame, this); //passes frame and controller to the login GUI
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
      GUICreateUser createUser = new GUICreateUser(frame, this);  //calls GUICreateUser() to get details of new user
   }
   
   public void createUser(User newUser)  //Calls DBMgr() to create a new user in the database
   {
      user = newUser;         //assigns instance variable to public variable for referencing
      displayGUIMainMenu();   //calls GUIMainMenu()
   }
   
   public void displayGUIMainMenu()//Called from GUILogin first, other GUIs can come back to the MainMenu
   {
      GUIMainMenu mainMenu = new GUIMainMenu(frame, this, user);  //passes the frame, controller, and user object to the menu
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