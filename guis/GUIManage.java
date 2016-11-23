package guis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import application.*;
import objects.User;
import database.*;

public class GUIManage extends JPanel
{
	private JLabel userLabel;
	private JTextField username;
	private Controller controller;
   private User user;
	
   
   public GUIManage(Controller controller)
   {
	   this.controller = controller;
	   this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	   this.addFields();
	   this.addButtons();
   }

	public void addButtons()
   {
	   JPanel btnPanel = new JPanel();
      btnPanel.setLayout(new FlowLayout());
      JButton btnLogin = new JButton("Search for User");
      btnLogin.addActionListener(new ActionListener()
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
            	JPanel userDisplayPanel = new JPanel();
               userDisplayPanel.setLayout(new FlowLayout());
               JLabel userName = new JLabel(user.getName());
               
               JLabel userPermissions = new JLabel(user.getPermissions().getpString());
            }
		   }
      });
	   btnPanel.add(btnLogin);
      JButton btnGoBack = new JButton("Go Back");
      btnGoBack.addActionListener(new ActionListener()
      {
 		   public void actionPerformed(ActionEvent e)
         {
 			   controller.displayGUIWelcome();
 		   }
      });
      btnPanel.add(btnGoBack);
      this.add(btnPanel);
	}
	
	public void addFields()
	{
      JPanel userPanel = new JPanel();
		userPanel.setLayout(new FlowLayout());
		userLabel = new JLabel("Enter User Name: ");
		username = new JTextField(20);
		userPanel.add(userLabel);
		userPanel.add(username);
	    
	   this.add(userPanel);

	}
}
