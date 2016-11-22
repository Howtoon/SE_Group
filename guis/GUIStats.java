package guis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import application.*;
import objects.User;
import objects.UserPermissions;

public class GUIStats extends JPanel
{
   private Controller controller;

   public GUIStats(Controller controller, User user)
   {  
      this.controller = controller;
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));         
      this.setBackground(Color.WHITE);
      this.addButtons(user);
   }
   
   public void addButtons(User user)
   {
	   JButton btnPortal = new JButton("Parking Transaction Portal");
      btnPortal.setAlignmentX(this.CENTER_ALIGNMENT);
	   btnPortal.addActionListener(new ActionListener()
      {
	      public void actionPerformed(ActionEvent e)
         {
  			   
  		   }
	   });
      this.add(btnPortal);
      
      JButton btnGoBack = new JButton("Go Back To Main Menu");
      btnGoBack.setAlignmentX(this.CENTER_ALIGNMENT);
	   btnGoBack.addActionListener(new ActionListener()
      {
	      public void actionPerformed(ActionEvent e)
         {
  			   controller.displayGUIMainMenu();
  		   }
	   });
      this.add(btnGoBack);
   }
}