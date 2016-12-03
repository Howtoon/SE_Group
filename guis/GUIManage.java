package guis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import application.*;
import objects.User;
/**
 * File Name: GUIManage.java
 * UWF Parking App
 *
 * This class handles the gui for the User Permissions page.
 *
 * @author Nathan, Will
 * @version 1.0
 */
public class GUIManage extends JPanel
{
   /** the text field to enter the username */
   private JTextField username;

   /** the controller used to call this class */
   private Controller controller;

   /** contains the permissions to change */
   private User user;

   /** application frame */
   private JFrame frame;

   /** contains the displayed user's permissions */
   private JPanel userDisplayPanel;

   /** contains the button to toggle */
   private JPanel changeBtnPanel;

   /**
    * Constructor that prepares the gui
    * @param controller the program controller
    * @param frame application frame
    */
   public GUIManage (Controller controller, JFrame frame)
   {
      this.frame = frame;
      this.controller = controller;
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
      this.addFields();
      this.addButtons();
      this.userDisplayPanel = new JPanel();
      this.changeBtnPanel = new JPanel();
      this.setBorder(BorderFactory.createLineBorder(Color.black));
   }

   /** Adds the buttons */
   public void addButtons ()
   {
      JPanel btnPanel = new JPanel();
      btnPanel.setLayout(new FlowLayout());
      btnPanel.setBackground(Color.CYAN);
      JButton btnLogin = new JButton("Search for User");
      btnLogin.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               user = controller.getUser(username.getText());
            
               if (user == null)
               {
                  controller.displayError("User Does Not Exist.");
               }
               else
               {
                  userDisplayPanel.removeAll();
                  changeBtnPanel.removeAll();
                  frame.revalidate();
                  displayUser();
                  displayModBtn();
                  System.out.println("Display User");
               }
            }
         });
      btnPanel.add(btnLogin);
      JButton btnGoBack = new JButton("Go Back");
      btnGoBack.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               controller.displayGUIMainMenu();
            }
         });
      btnPanel.add(btnGoBack);
      btnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      this.add(btnPanel);
   }

   /** Adds the text fields */
   public void addFields ()
   {
      JPanel userPanel = new JPanel();
      userPanel.setLayout(new FlowLayout());
      JLabel userLabel = new JLabel("Enter User Name: ");
      username = new JTextField(20);
      userPanel.add(userLabel);
      userPanel.add(username);
      userPanel.setBackground(Color.CYAN);
      userPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      this.add(userPanel);
   }

   /** Displays the user's permissions */
   public void displayUser ()
   {
      JLabel userName = new JLabel(user.getName());
      JLabel userPermissions = new JLabel(user.getPermissions().getpString());
      userDisplayPanel.add(userName);
      userDisplayPanel.add(userPermissions);
      userDisplayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      this.add(userDisplayPanel);
      frame.revalidate();
   }

   /** Displays and implements the toggle button */
   public void displayModBtn ()
   {  
      JButton changePermission = new JButton("Toggle User Permission");
      changePermission.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               if (user.getPermissions().getpString() == "USER")
               {
                  controller.updatePermissions (user.getName(), 1);
               }
               else if (user.getPermissions().getpString() == "ADMIN")
               {
                  controller.updatePermissions (user.getName(), 0);
               }
               else if (user.getPermissions().getpString() == "SUPERVISOR")
               {
                  controller.displayError("You Cannot Change The Supervisor's Permissions!!!");
               }
               user = controller.getUser(username.getText());
               if (user == null)
               {
                  controller.displayError("User Does Not Exist.");
               }
               else
               {
                  userDisplayPanel.removeAll();
                  changeBtnPanel.removeAll();
                  frame.revalidate();
                  displayUser();
                  displayModBtn();
                  System.out.println("Display User");
               }
            }
         });
      changeBtnPanel.setBackground(Color.ORANGE);
      changeBtnPanel.add(changePermission);
      changeBtnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      this.add(changeBtnPanel);
      frame.revalidate();
   }
}
