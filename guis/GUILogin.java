package guis;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import application.*;

public class GUILogin extends JPanel
{
	
	private JLabel user;
	private JLabel pass;
	private JTextField username;
	private JPasswordField password;
	
	private Controller controller;
	
   public GUILogin(Controller controller)
   {
	   
	  this.controller = controller;
	  this.addFields();
	  this.addButtons();
      
   }

	public void addButtons()
	{

      JButton btnLogin = new JButton("Submit for Login");
      btnLogin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

            boolean verify = controller.verifyLogin(username.getText(), new String(password.getPassword()));
            if (verify)
            {
            	
               controller.displayGUIMainMenu();
               
            }     
		}
      });
      this.add(btnLogin);
	      
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
   