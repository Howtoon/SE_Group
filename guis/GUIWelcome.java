package guis;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import application.*;

public class GUIWelcome extends JPanel
{
	
	
   public GUIWelcome(JFrame frame, Controller controller)
   {
	   
	   //this.addButtons();
	   /*
	      JPanel panelLogin = new JPanel();
	      panelLogin.setBackground(Color.WHITE);             
	      JButton btnLogin = new JButton("Continue to Login");
	      btnLogin.setActionCommand("cmdLogin");
	      panelLogin.add(btnLogin);
	      btnLogin.setEnabled(true);
	      frame.add(panelLogin);
	      frame.setVisible(true);
	
	      class ListenerClass implements ActionListener
	      {
	         public void actionPerformed(ActionEvent e)
	         {
	            if (e.getActionCommand().equals("cmdLogin"))
	            {
	               frame.remove(panelLogin);
	               controller.displayGUILogin();
	            }
	         }
	      }
	      ActionListener listener = new ListenerClass();
	      btnLogin.addActionListener(listener);
      */
   }
   
   
}