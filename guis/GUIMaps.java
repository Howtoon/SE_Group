package guis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import application.Controller;

public class GUIMaps extends JPanel
{
	
	Controller controller;
	
	public GUIMaps(Controller controller)
	{
		
	   this.controller = controller;
	   this.addButtons();
	   
   }
	
	private void addButtons()
	{
		
		JButton btnGoBack = new JButton("Go Back");
	      btnGoBack.addActionListener(new ActionListener()
	      {
	 		   public void actionPerformed(ActionEvent e)
	         {
	 			   controller.displayGUIMainMenu();
	 		   }
	      });
	      this.add(btnGoBack);
	      
	}

	
	public void paintComponent(Graphics g)
	{
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		Image image = toolkit.getImage("resources/parking_map.png");
		g.drawImage(image, 0, 0, this);
	}
}