package guis;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import application.*;

public class GUICreateUser
{
   public GUICreateUser(JFrame frame, Controller controller)
   {
      JPanel panelUserName = new JPanel();
      panelUserName.setBackground(Color.CYAN);
      JLabel userName = new JLabel("User Name: ");
      final JTextField tfUserName = new JTextField(20);
      panelUserName.add(userName);
      panelUserName.add(tfUserName);
      
      JPanel panelPassword = new JPanel();
      panelPassword.setBackground(Color.CYAN);
      JLabel password = new JLabel("Password: ");
      final JTextField tfPassword = new JTextField(20);
      panelPassword.add(password);
      panelPassword.add(tfPassword);
      
      JPanel panelOptions = new JPanel();
      JButton btnContinue = new JButton("Continue");
      btnContinue.setActionCommand("cmdContinue");
      panelOptions.add(btnContinue);
      
      JButton btnGoBack = new JButton("Go Back");
      btnGoBack.setActionCommand("cmdGoBack");
      panelOptions.add(btnGoBack);
      
      frame.add(panelUserName);
      frame.add(panelPassword);
      frame.add(panelOptions);

      frame.setVisible(true);
   
      class ListenerClass implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getActionCommand().equals("cmdContinue"))
            {
               if (!controller.verifyDuplicate(tfUserName.getText(), tfPassword.getText()))
               {
                  frame.remove(panelUserName);
                  frame.remove(panelPassword);
                  frame.remove(panelOptions);
                  controller.displayGUIMainMenu();//Only if user does not exist
               }
               else if (controller.verifyDuplicate(tfUserName.getText(), tfPassword.getText()))
               {
                  //throw alert message
               }
            }
            else if(e.getActionCommand().equals("cmdGoBack"))
            {
               frame.remove(panelUserName);
               frame.remove(panelPassword);
               frame.remove(panelOptions);
               controller.displayGUILogin();    
            }
         }
      }
      ActionListener listener = new ListenerClass();
      btnContinue.addActionListener(listener);
      btnGoBack.addActionListener(listener);
   }
}