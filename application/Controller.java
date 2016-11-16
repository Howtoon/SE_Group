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
      frame = new JFrame("");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(Color.WHITE);
      frame.setSize(250,250);
      frame.setLayout(new GridLayout(0, 1));
      frame.setVisible(true);
      GUIWelcome welcome = new GUIWelcome(frame, this);
   }
   
   public void displayGUILogin()//Called from GUIWelcome()
   {
      GUILogin login = new GUILogin(frame, this); 
   }
   
   public boolean verifyLogin(String userName, String password)
   {
      return true;//returns true/false based on valid username and password combo
   }
   
   public void displayGUIMainMenu()//Called from GUILogin first, other GUIs can come back to the MainMenu
   {
      //GUIMainMenu mainMenu = new GUIMainMenu(frame, user);
   }
}