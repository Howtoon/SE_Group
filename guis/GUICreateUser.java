package guis;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import application.*;

public class GUICreateUser extends JPanel
{
   public GUICreateUser(JFrame frame, Controller controller)
   {
      JLabel userName = new JLabel("User Name: ");
      final JTextField tfUserName = new JTextField(20);
      this.add(userName);
      this.add(tfUserName);

      JLabel password = new JLabel("Password: ");
      final JPasswordField tfPassword = new JPasswordField(20);
      this.add(password);
      this.add(tfPassword);
      
      JButton btnContinue = new JButton("Continue");
      btnContinue.setActionCommand("cmdContinue");
      this.add(btnContinue);
      
      JButton btnGoBack = new JButton("Go Back");
      btnGoBack.setActionCommand("cmdGoBack");
      this.add(btnGoBack);
   
      class ListenerClass implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getActionCommand().equals("cmdContinue"))
            {
               if (!controller.verifyDuplicate(tfUserName.getText(), new String(tfPassword.getPassword())))
               {
                  controller.displayGUIMainMenu(); //Only if user does not exist
               }
               else if (controller.verifyDuplicate(tfUserName.getText(), new String(tfPassword.getPassword())))
               {
                  //throw alert message
               }
            }
            else if(e.getActionCommand().equals("cmdGoBack"))
            {

               controller.displayGUILogin();    
            }
         }
      }
      ActionListener listener = new ListenerClass();
      btnContinue.addActionListener(listener);
      btnGoBack.addActionListener(listener);
   }
}