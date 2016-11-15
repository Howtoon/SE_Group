package application;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridLayout;
import guis.*;


public class Controller{
   private JFrame frame;
   
   public static void main(String args[]){
      Controller controller = new Controller();
      controller.start();
   }
   
   private void start()
   {
      frame = new JFrame("");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(Color.WHITE);
      frame.setSize(250,250);
      frame.setLayout(new GridLayout(0, 1));
      frame.setVisible(true);
      GUIWelcome welcome = new GUIWelcome(frame);
   }
   
   
}