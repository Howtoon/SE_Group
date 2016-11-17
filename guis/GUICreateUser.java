package guis;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	  this.addButtons();
	  this.addFields();
     
   }

   public void addButtons()
   {

	  JButton btnSubmit = new JButton("Submit");
	  btnSubmit.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
    	    if (!controller.verifyDuplicate(username.getText(), new String(password.getPassword())))
            {
               controller.displayGUIMainMenu(); //Only if user does not exist
            }
		 }
      });
      this.add(btnSubmit);
	 
      JButton btnGoBack = new JButton("Go Back");
      btnGoBack.addActionListener(new ActionListener() {
 		 public void actionPerformed(ActionEvent e) {
 			 controller.displayGUIWelcome();
 		 }
       });
      this.add(btnGoBack);
     
	}
	
	public void addFields()
	{
		
		user = new JLabel("User Name: ");
		username = new JTextField(20);
	    this.add(user);
	    this.add(username);
	     
	    pass = new JLabel("Password: ");
	    password = new JPasswordField(20);
	    password.setEchoChar('#');
	    this.add(pass);
	    this.add(password);
		
	}
}