package guis;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import application.*;

public class GUICreateUser extends JPanel
{
	
	private JLabel user;
	private JLabel pass;
	private JTextField username;
	private JPasswordField password;
	
	private Controller controller;
	
   public GUICreateUser(Controller controller)
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
      JButton btnLogin = new JButton("Create New User");
      btnLogin.addActionListener(new ActionListener()
      {
		   public void actionPerformed(ActionEvent e)
         {

            boolean verify = controller.verifyDuplicate(username.getText(), new String(password.getPassword()));
            if (verify)
            {
               controller.displayGUIMainMenu();
            }
            else
            {
            	controller.displayCreateUserError();
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
		user = new JLabel("User Name: ");
		username = new JTextField(20);
		userPanel.add(user);
		userPanel.add(username);
	    
	   JPanel passPanel = new JPanel();
	   passPanel.setLayout(new FlowLayout());
	   pass = new JLabel("Password: ");
	   password = new JPasswordField(20);
      password.setEchoChar('#');
	   passPanel.add(pass);
	   passPanel.add(password);
	    
	   this.add(userPanel);
	   this.add(passPanel);
	}
}