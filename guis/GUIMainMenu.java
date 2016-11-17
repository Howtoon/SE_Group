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
   public GUIMainMenu(JFrame frame, Controller controller, User user)
   {   
      //JPanel panelPortal = new JPanel();
      //panelPortal.setBackground(Color.WHITE);           
      JButton btnPortal = new JButton("Parking Transaction Portal");
      btnPortal.setActionCommand("cmdPortal");
      this.add(btnPortal);
      
      //JPanel panelRegs = new JPanel();
      //panelRegs.setBackground(Color.WHITE);             
      JButton btnRegs = new JButton("View Parking Regulations");
      btnRegs.setActionCommand("cmdRegs");
      this.add(btnRegs);
      
      //JPanel panelStats = new JPanel();
      //panelStats.setBackground(Color.WHITE);             
      JButton btnStats = new JButton("View Parking Statistics");
      btnStats.setActionCommand("cmdStats");
      this.add(btnStats);
      
      //JPanel panelWalk = new JPanel();
      //panelWalk.setBackground(Color.WHITE);             
      JButton btnWalk = new JButton("View Walking Times");
      btnWalk.setActionCommand("cmdWalk");
      this.add(btnWalk);
      
      //JPanel panelMaps = new JPanel();
      //panelMaps.setBackground(Color.WHITE);             
      JButton btnMaps = new JButton("View Parking Lot Maps");
      btnMaps.setActionCommand("cmdMaps");
      this.add(btnMaps);
      
      //JPanel panelRestric = new JPanel();
      //panelRestric.setBackground(Color.WHITE);             
      JButton btnRestric = new JButton("View Parking Lot Restrictions");
      btnRestric.setActionCommand("cmdRestric");
      this.add(btnRestric);
      
      //JPanel panelReport = new JPanel();
      //panelReport.setBackground(Color.WHITE);             
      JButton btnReport = new JButton("Create Parking Lot Report");
      btnReport.setActionCommand("cmdReport");
      this.add(btnReport);
      
      //JPanel panelViolate = new JPanel();
      //panelViolate.setBackground(Color.WHITE);             
      JButton btnViolate = new JButton("Record Parking Violation");
      btnViolate.setActionCommand("cmdViolate");
      this.add(btnViolate);
      
      //JPanel panelManage = new JPanel();
      //panelManage.setBackground(Color.WHITE);             
      JButton btnManage = new JButton("Manage Administrators");
      btnManage.setActionCommand("cmdManage");
      this.add(btnManage);
      
      /*
      frame.add(panelPortal);
      frame.add(panelRegs);
      frame.add(panelStats);
      frame.add(panelWalk);
      frame.add(panelMaps);
      frame.add(panelRestric);

      frame.add(panelReport);
      frame.add(panelViolate);

      frame.add(panelManage);
      frame.setVisible(true);
      */
   
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