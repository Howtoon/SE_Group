package guis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import application.*;

public class GUIMainMenu extends JPanel
{
   public GUIMainMenu(Controller controller, User user)
   {  
	   
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));         
      this.setBackground(Color.WHITE);
      this.addButtons(user);

   }
   
   public void addButtons(User user)
   {
	   
	  JButton btnPortal = new JButton("Parking Transaction Portal");
	  btnPortal.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	  });
      this.add(btnPortal);
                  
      JButton btnRegs = new JButton("View Parking Regulations");
      btnRegs.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			
  		}
  	  });
      this.add(btnRegs);
             
      JButton btnStats = new JButton("View Parking Statistics");
      btnStats.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
      });
      this.add(btnStats);
            
      JButton btnWalk = new JButton("View Walking Times");
      btnWalk.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			
  		}
      });
      this.add(btnWalk);
             
      JButton btnMaps = new JButton("View Parking Lot Maps");
      btnMaps.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
      });
      this.add(btnMaps);
                
      JButton btnRestric = new JButton("View Parking Lot Restrictions");
      btnRestric.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			
  		}
      });
      this.add(btnRestric);
           
      JButton btnReport = new JButton("Create Parking Lot Report");
      btnRestric.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
      });
      this.add(btnReport);
           
      JButton btnViolate = new JButton("Record Parking Violation");
      btnViolate.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			
  		}
      });
      this.add(btnViolate);
            
      JButton btnManage = new JButton("Manage Administrators");
      btnViolate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
      });
      this.add(btnManage);
	      
   }
}