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
   public GUILogin(Controller controller)
   {
      JLabel userName = new JLabel("User Name: ");
      final JTextField tfUserName = new JTextField(20);
      this.add(userName);
      this.add(tfUserName);
      
      JLabel password = new JLabel("Password: ");
      final JPasswordField tfPassword = new JPasswordField(20);
      tfPassword.setEchoChar('#');
      this.add(password);
      this.add(tfPassword);
      
      JButton btnCreate = new JButton("Create a New Account");
      btnCreate.setActionCommand("cmdCreate");
      this.add(btnCreate);
      
      JButton btnLogin = new JButton("Submit for Login");
      btnLogin.setActionCommand("cmdLogin");
      this.add(btnLogin);


     // frame.setVisible(true);
   
      class ListenerClass implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getActionCommand().equals("cmdCreate"))
            {
            	/*
               this.remove(panelUserName);
               this.remove(panelPassword);
               this.remove(panelSelect);*/
               controller.displayGUICreateUser();
            }
            else if(e.getActionCommand().equals("cmdLogin"))
            {
               boolean verify = controller.verifyLogin(tfUserName.getText(), new String(tfPassword.getPassword()));
               if (verify)
               {
            	   /*
                  this.remove(panelUserName);
                  this.remove(panelPassword);
                  this.remove(panelSelect);*/
                  controller.displayGUIMainMenu();
               }     
            }
         }
      }
      ActionListener listener = new ListenerClass();
      btnLogin.addActionListener(listener);
      btnCreate.addActionListener(listener);
   }
}
   