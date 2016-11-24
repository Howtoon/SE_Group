package guis;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import application.*;
import objects.*;

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
		JButton btnPortal = new JButton("");
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