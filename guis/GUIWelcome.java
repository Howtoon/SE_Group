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

public class GUIWelcome{
   public GUIWelcome(JFrame frame){
      JPanel panelInput1 = new JPanel();
      panelInput1.setBackground(Color.CYAN);
      JLabel Comm = new JLabel("Commuter");
      
      final JTextField tfNum1 = new JTextField(5);
      panelInput1.add(Comm);
      panelInput1.add(tfNum1);
      JPanel panelInput2 = new JPanel();
      panelInput2.setBackground(Color.CYAN);             
      JLabel Staff = new JLabel("Staff");
      
      final JTextField tfNum2 = new JTextField(5);
      panelInput2.add(Staff);
      panelInput2.add(tfNum2);
      JPanel panelSelect = new JPanel();
      panelSelect.setBackground(Color.ORANGE);           
      
      JButton btnSub = new JButton("  -  ");
      btnSub.setActionCommand("cmdSub");
      panelSelect.add(btnSub);
      
      JButton btnAdd = new JButton("  +  ");
      btnAdd.setActionCommand("cmdAdd");
      panelSelect.add(btnAdd);

      
      JPanel panelCalc = new JPanel();
      panelCalc.setBackground(Color.ORANGE);             
      JButton btnCalc = new JButton("Calculate");
      btnCalc.setActionCommand("cmdCalc");
      panelCalc.add(btnCalc);
      btnCalc.setEnabled(false);
      
      JPanel panelResult = new JPanel();
      panelResult.setBackground(Color.WHITE);				
      JLabel labelResult = new JLabel("Result: ");
      JLabel labelMath = new JLabel("-----");
      panelResult.add(labelResult);
      panelResult.add(labelMath);
      
      JPanel panelName = new JPanel();
      panelName.setBackground(Color.YELLOW);				
      JLabel labelName = new JLabel("Written by William Tennis.                ");
      panelName.add(labelName);
      
      frame.add(panelInput1);
      frame.add(panelInput2);
      frame.add(panelSelect);
      frame.add(panelCalc);
      frame.add(panelResult);
      frame.add(panelName);
      frame.setVisible(true);
   
      class ListenerClass implements ActionListener{
         int a1;
         int a2;
         public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("cmdSub")){
               btnSub.setEnabled(false);
               btnAdd.setEnabled(true);
               btnCalc.setEnabled(true);
            }
            else if(e.getActionCommand().equals("cmdAdd")){
               btnAdd.setEnabled(false);
               btnSub.setEnabled(true);
               btnCalc.setEnabled(true);
            }
            
            else if (e.getActionCommand().equals("cmdCalc")){
               a1=Integer.parseInt(tfNum1.getText());
               a2=Integer.parseInt(tfNum2.getText());
               if (!btnAdd.isEnabled()){
                  labelMath.setText(a1+"+"+a2+"="+(a1+a2));
               }
               else if(!btnSub.isEnabled()){
                  labelMath.setText(a1+"-"+a2+"="+(a1-a2));
               }
            }
         }
      }
      ActionListener listener = new ListenerClass();
      btnAdd.addActionListener(listener);
      btnSub.addActionListener(listener);
      btnCalc.addActionListener(listener);
   }


}
   