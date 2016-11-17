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

public class GUIMainMenu extends JPanel
{
   public GUIMainMenu(Controller controller, User user)
   {  
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));         
      this.setBackground(Color.WHITE);
      this.addButtons(User user);
      
   
      JButton btnPortal = new JButton("Parking Transaction Portal");
      btnPortal.setActionCommand("cmdPortal");
      this.add(btnPortal);
                  
      JButton btnRegs = new JButton("View Parking Regulations");
      btnRegs.setActionCommand("cmdRegs");
      this.add(btnRegs);
      
             
      JButton btnStats = new JButton("View Parking Statistics");
      btnStats.setActionCommand("cmdStats");
      this.add(btnStats);
            
      JButton btnWalk = new JButton("View Walking Times");
      btnWalk.setActionCommand("cmdWalk");
      this.add(btnWalk);
             
      JButton btnMaps = new JButton("View Parking Lot Maps");
      btnMaps.setActionCommand("cmdMaps");
      this.add(btnMaps);
                
      JButton btnRestric = new JButton("View Parking Lot Restrictions");
      btnRestric.setActionCommand("cmdRestric");
      this.add(btnRestric);
           
      JButton btnReport = new JButton("Create Parking Lot Report");
      btnReport.setActionCommand("cmdReport");
      this.add(btnReport);
           
      JButton btnViolate = new JButton("Record Parking Violation");
      btnViolate.setActionCommand("cmdViolate");
      this.add(btnViolate);
            
      JButton btnManage = new JButton("Manage Administrators");
      btnManage.setActionCommand("cmdManage");
      this.add(btnManage);
   
      class ListenerClass implements ActionListener
      {

         public void actionPerformed(ActionEvent e)
         {
            if(e.getActionCommand().equals("cmdPortal"))
            {

            }
            else if(e.getActionCommand().equals("cmdRegs"))
            {

            }
            else if(e.getActionCommand().equals("cmdStats"))
            {

            }
            else if(e.getActionCommand().equals("cmdWalk"))
            {

            }
            else if(e.getActionCommand().equals("cmdMaps"))
            {

            }
            else if(e.getActionCommand().equals("cmdRestric"))
            {

            }
            else if(e.getActionCommand().equals("cmdReport"))
            {

            }
            else if(e.getActionCommand().equals("cmdViolate"))
            {

            }
            else if(e.getActionCommand().equals("cmdManage"))
            {

            }
         }
      }
      ActionListener listener = new ListenerClass();
      
      btnPortal.addActionListener(listener);
      btnRegs.addActionListener(listener);
   }
}