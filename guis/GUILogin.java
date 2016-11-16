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

public class GUILogin
{
   public GUILogin(JFrame frame, Controller controller)
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
      
      JPanel panelSelect = new JPanel();
      panelSelect.setBackground(Color.ORANGE);
      JButton btnCreate = new JButton("Create a New Account");
      btnCreate.setActionCommand("cmdCreate");
      panelSelect.add(btnCreate);
      
      JButton btnLogin = new JButton("Submit for Login");
      btnLogin.setActionCommand("cmdLogin");
      panelSelect.add(btnLogin);
      
      frame.add(panelUserName);
      frame.add(panelPassword);
      frame.add(panelSelect);

      frame.setVisible(true);
   
      class ListenerClass implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getActionCommand().equals("cmdCreate"))
            {
               frame.remove(panelUserName);
               frame.remove(panelPassword);
               frame.remove(panelSelect);
               controller.displayGUICreateUser();
            }
            else if(e.getActionCommand().equals("cmdLogin"))
            {
               boolean verify = controller.verifyLogin(tfUserName.getText(), tfPassword.getText());
               if (verify)
               {
                  frame.remove(panelUserName);
                  frame.remove(panelPassword);
                  frame.remove(panelSelect);
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
   