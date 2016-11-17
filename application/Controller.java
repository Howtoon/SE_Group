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
}