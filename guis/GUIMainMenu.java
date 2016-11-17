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
      btnPortal.setAlignmentX(this.CENTER_ALIGNMENT);
	   btnPortal.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
			
		}
	   });
      this.add(btnPortal);
                  
      JButton btnRegs = new JButton("View Parking Regulations");
      btnRegs.setAlignmentX(this.CENTER_ALIGNMENT);
      btnRegs.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			
  		}
  	   });
      this.add(btnRegs);
             
      JButton btnStats = new JButton("View Parking Statistics");
      btnStats.setAlignmentX(this.CENTER_ALIGNMENT);
      btnStats.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
      });
      this.add(btnStats);
            
      JButton btnWalk = new JButton("View Walking Times");
      btnWalk.setAlignmentX(this.CENTER_ALIGNMENT);
      btnWalk.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			
  		}
      });
      this.add(btnWalk);
             
      JButton btnMaps = new JButton("View Parking Lot Maps");
      btnMaps.setAlignmentX(this.CENTER_ALIGNMENT);
      btnMaps.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
      });
      this.add(btnMaps);
                
      JButton btnRestric = new JButton("View Parking Lot Restrictions");
      btnRestric.setAlignmentX(this.CENTER_ALIGNMENT);
      btnRestric.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			
  		}
      });
      this.add(btnRestric);
      
      if (user.getPermissions().equals("SUPERVISOR") || user.getPermissions().equals("ADMIN"))
      {
         JButton btnReport = new JButton("Create Parking Lot Report");
         btnReport.setAlignmentX(this.CENTER_ALIGNMENT);
         btnReport.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			
		   }
         });
         this.add(btnReport);
           
         JButton btnViolate = new JButton("Record Parking Violation");
         btnViolate.setAlignmentX(this.CENTER_ALIGNMENT);
         btnViolate.addActionListener(new ActionListener() {
  		   public void actionPerformed(ActionEvent e) {
  			
  		   }
         });
         this.add(btnViolate);
      }
      if (user.getPermissions().equals("ADMIN"))
      {

         JButton btnManage = new JButton("Manage Administrators");
         btnManage.setAlignmentX(this.CENTER_ALIGNMENT);
         btnManage.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent e) {
			
   		}
         });
         this.add(btnManage);
      }
   }
}